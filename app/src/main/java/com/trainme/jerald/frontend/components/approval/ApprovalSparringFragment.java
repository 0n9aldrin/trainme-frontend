package com.trainme.jerald.frontend.components.approval;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.requestjoin.RequestJoinContract;
import com.trainme.jerald.frontend.components.requestjoin.RequestJoinController;
import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;
import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;

import javax.inject.Inject;

public class ApprovalSparringFragment extends DialogFragment implements ApprovalSparringContract.View {

    @Inject
    ApprovalSparringController mController;

    EditText etReason;
    Button accept;
    Button reject;

    public static int ppId = 0;

    public ApprovalSparringFragment() {

    }

    public static ApprovalSparringFragment newInstance(int idPP) {
        ApprovalSparringFragment.ppId = idPP;
        ApprovalSparringFragment fragment = new ApprovalSparringFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.layout_approval, null);
        ((MainApplication) getActivity().getApplication())
                .getComponent()
                .inject(this);

        MaterialDialog builder = new MaterialDialog.Builder(getActivity())
                .title("Approval Request")
                .customView(v,false)
                .build();

        etReason = v.findViewById(R.id.etReason);
        accept = v.findViewById(R.id.accept);
        reject = v.findViewById(R.id.reject);
        builder.show();

        accept.setOnClickListener(view -> {
            ApprovalSparing model = new ApprovalSparing(ppId, "accept",
                    etReason.getText().toString());
            mController.setView(this);
            mController.approval(model);
        });

        reject.setOnClickListener(view -> {
            ApprovalSparing model = new ApprovalSparing(ppId, "reject",
                    etReason.getText().toString());
            mController.setView(this);
            mController.approval(model);
        });
        return builder;
    }

    @Override
    public void approvalSuccess(String message) {
        Toast.makeText(getActivity().getApplicationContext(), "Approval Success", Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void approvalFailed(String message) {
        Toast.makeText(getActivity().getApplicationContext(), "Something wrong "+message, Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
