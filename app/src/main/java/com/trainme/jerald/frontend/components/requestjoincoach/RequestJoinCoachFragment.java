package com.trainme.jerald.frontend.components.requestjoincoach;

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
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;

import javax.inject.Inject;

public class RequestJoinCoachFragment extends DialogFragment implements RequestJoinCoachContract.View {

    @Inject
    RequestJoinCoachController mController;

    EditText etNotes;
    Button add;

    public static int sparringId = 0;
    public static int userId = 0;

    public RequestJoinCoachFragment() {

    }

    public static RequestJoinCoachFragment newInstance(int idSparr, int idUser) {
        RequestJoinCoachFragment.sparringId = idSparr;
        RequestJoinCoachFragment.userId = idUser;
        RequestJoinCoachFragment fragment = new RequestJoinCoachFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.layout_request_join, null);
        ((MainApplication) getActivity().getApplication())
                .getComponent()
                .inject(this);

        MaterialDialog builder = new MaterialDialog.Builder(getActivity())
                .title("Request Join")
                .customView(v,false)
                .build();

        etNotes = v.findViewById(R.id.etNotes);
        add = v.findViewById(R.id.add);
        builder.show();

        add.setOnClickListener(view -> {
            RequestJoinSparing model = new RequestJoinSparing(etNotes.getText().toString(), sparringId,
                    userId);
            mController.setView(this);
            mController.requestJoinCoach(model);
        });
        return builder;
    }

    @Override
    public void requestJoinCoachSuccess(String message) {
        Toast.makeText(getActivity().getApplicationContext(), "Request Send", Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void requestJoinCoachFailed(String message) {
        Toast.makeText(getActivity().getApplicationContext(), "Something wrong "+message, Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
