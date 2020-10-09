package com.trainme.jerald.frontend;

import android.app.Application;
import android.graphics.Typeface;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.TypiconsModule;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.component.DaggerAppComponent;
import com.trainme.jerald.frontend.dependencies.modules.AppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {
    private AppComponent mComponent;
    private final static int SCHEMA_VERSION = 1;
    private Typeface robotoRegularFont, robotoBoldFont;
    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfiguration();
        initIconConfiguration();

        robotoRegularFont = Typeface.createFromAsset(getAssets(), "fonts/robotoregular.ttf");
        robotoBoldFont = Typeface.createFromAsset(getAssets(), "fonts/robotobold.ttf");

        mComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public Typeface getRobotoRegularFont() {
        return robotoRegularFont;
    }

    public Typeface getRobotoBoldFont() {
        return robotoBoldFont;
    }


    private void initIconConfiguration() {
        Iconify
                .with(new FontAwesomeModule())
                .with(new TypiconsModule())
                .with(new IoniconsModule());
    }

    private void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public AppComponent getComponent() {
        return mComponent;
    }

}
