package com.trainme.jerald.frontend.components.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.widget.Button;
import android.widget.TextView;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.base.BaseActivity;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UpdateProfileActivity extends BaseActivity implements UpdateProfileContract.View {

    private String TAG = "UpdateProfile";

    @Inject
    UpdateProfileController mController;

    @BindView(R.id.uploadImg)
    Button uploadImg;

    @BindView(R.id.imageName)
    TextView imageName;

    @OnClick(R.id.uploadImg)
    void clickChoose() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);
    }

    @BindView(R.id.update)
    Button update;

    @OnClick(R.id.update)
    void clickUpdate() {
        File newfile = new File(getRealPathFromURI(filePath));
        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath)), newfile);
        RequestBody idUserBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(idUser));

        mController.updateData(requestFile, idUserBody);
        showLoading();
    }

    Uri filePath;
    File file;
    private static int idUser = 0;

    public static Intent createIntent(Context context, int user) {
        idUser = user;
        return new Intent(context, UpdateProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        //checking the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }
        mController.setView(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI
            filePath = data.getData();
            file = new File(filePath.getPath());
            String namaImage = file.getName();
            imageName.setText(namaImage);
        }
    }

    @Override
    public void updateDataSuccess() {
        hideLoading();
        startActivity(ProfileActivity.createIntent(getApplicationContext()));
    }

    @Override
    public void updateDataFailed(String message) {
        hideLoading();
        onError(message);
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
}
