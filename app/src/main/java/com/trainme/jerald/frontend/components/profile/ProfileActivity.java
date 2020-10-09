package com.trainme.jerald.frontend.components.profile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.showimage.PreviewActivity;
import com.trainme.jerald.frontend.dependencies.models.Profile;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProfileActivity extends BaseActivity implements ProfileContract.View, UploadCertificateContract.View {

    @Inject
    UploadCertificateController upController;

    @Inject
    ProfileController mController;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profile)
    CircleImageView profileImage;
    @OnClick(R.id.profile)
    void clickProfile() {
        if(dataProfile.getImage() != null && dataProfile.getImage().compareTo("")!=0){
            startActivity(PreviewActivity.createIntent(getApplicationContext(), dataProfile.getImage()));
        }
    }
    @BindView(R.id.edit)
    ImageView edit;

    @OnClick(R.id.edit)
    void editImage() {
        startActivity(UpdateProfileActivity.createIntent(getApplicationContext(),mController.getIdUser()));
    }

    @BindView(R.id.emailUser)
    TextView emailUser;
    @BindView(R.id.phoneNumber)
    TextView phoneNumber;
    @BindView(R.id.nameUser)
    TextView nameUser;

    @BindView(R.id.studentsConfig)
    LinearLayout studentsConfig;
    @BindView(R.id.coachConfig)
    LinearLayout coachConfig;

    @BindView(R.id.levelSkill)
    TextView levelSkill;
    @BindView(R.id.utr)
    TextView utr;

    @BindView(R.id.experience)
    TextView experience;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.show)
    TextView show;
    @OnClick(R.id.show)
    void clickDownload() {
        if(show.getText().toString().compareTo("-") != 0){
            startActivity(PreviewActivity.createIntent(getApplicationContext(), dataProfile.getCertificate()));
        }
    }
    @BindView(R.id.chooseCertificate)
    Button chooseCertificate;
    @OnClick(R.id.chooseCertificate)
    void chooseCertificate(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);
    }
    @BindView(R.id.btnUpload)
    Button btnUpload;
    @OnClick(R.id.btnUpload)
    void clickUpload(){
        File newfile = new File(getRealPathFromURI(filePath));
        //creating request body for file
        Log.e("File", newfile.getAbsolutePath()+"");
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath)), newfile);
        RequestBody idUserBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(dataProfile.getId()));

        upController.updateData(requestFile, idUserBody);
        showLoading();
    }

    @BindView(R.id.namaDocument)
    TextView namaDocument;

    Profile dataProfile;
    public static Intent createIntent(Context context) {
        return new Intent(context, ProfileActivity.class);
    }

    Uri filePath;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getDelegate().setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showLoading();
        upController.setView(this);
        mController.setView(this);
        mController.getData();
    }

    @Override
    public void getDataSuccess(Profile data) {
        dataProfile = data;
        hideLoading();
        setData(data);
    }

    private void setData(Profile data) {
        emailUser.setText(data.getEmail());
        nameUser.setText(data.getName());
        phoneNumber.setText(data.getPhoneNumber());
        if(data.getImage() != null && data.getImage().compareTo("")!=0){
            Glide.with(getApplicationContext()).load(data.getImage()).into(profileImage);
        }
        if (data.getRole() == 1) {
            levelSkill.setText("" +data.getLevel());
            utr.setText(""+ data.getUtr());
            coachConfig.setVisibility(View.GONE);
            studentsConfig.setVisibility(View.VISIBLE);
        } else {
            experience.setText(""+data.getExperience());
            price.setText(""+data.getPrice());
            studentsConfig.setVisibility(View.GONE);
            coachConfig.setVisibility(View.VISIBLE);
            if (data.getCertificate() != null ) {
                show.setText("Download Certificate");
            } else {
                show.setText("-");
            }
        }
    }

    @Override
    public void getDataFailed(String message) {
        hideLoading();
        onError(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI
            filePath = data.getData();
            file = new File(filePath.getPath());
            String namaImage = file.getName();
            namaDocument.setText(namaImage);
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void updateDataSuccess() {
        hideLoading();
        Toast.makeText(this, "Uploaded success", Toast.LENGTH_SHORT).show();
        startActivity(ProfileActivity.createIntent(getApplicationContext()));
        finish();
    }

    @Override
    public void updateDataFailed(String message) {
        onError("Something Wrong : "+message);
        hideLoading();
    }
}
