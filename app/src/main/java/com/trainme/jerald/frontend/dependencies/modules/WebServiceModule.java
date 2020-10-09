package com.trainme.jerald.frontend.dependencies.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trainme.jerald.frontend.BuildConfig;
import com.trainme.jerald.frontend.dependencies.webservices.TrainmeAPI;

import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServiceModule {
    @Singleton
    @Provides
    @Named("wsRetrofit")
    public Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }


    @Singleton
    @Provides
    TrainmeAPI provideService(@Named("wsRetrofit")Retrofit retrofit) {
        return retrofit.create(TrainmeAPI.class);
    }
}
