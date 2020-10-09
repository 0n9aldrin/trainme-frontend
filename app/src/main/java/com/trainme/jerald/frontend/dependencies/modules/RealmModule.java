package com.trainme.jerald.frontend.dependencies.modules;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class RealmModule {
    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}

