package com.trainme.jerald.frontend.dependencies.modules;

import android.content.Context;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.components.addcoaching.AddCoachingController;
import com.trainme.jerald.frontend.components.addsparring.AddSparringController;
import com.trainme.jerald.frontend.components.approval.ApprovalSparringController;
import com.trainme.jerald.frontend.components.approvalcoaching.ApprovalCoachingController;
import com.trainme.jerald.frontend.components.eventphoto.EventPhotoController;
import com.trainme.jerald.frontend.components.events.EventController;
import com.trainme.jerald.frontend.components.historycoaching.HistoryCoachingController;
import com.trainme.jerald.frontend.components.historysparring.HistorySparringController;
import com.trainme.jerald.frontend.components.home.HomeController;
import com.trainme.jerald.frontend.components.informasi.InformasiUmumController;
import com.trainme.jerald.frontend.components.informasi.KebijakanPrivasiController;
import com.trainme.jerald.frontend.components.myrequestcoachingstatus.MyRequestCoachingStatusController;
import com.trainme.jerald.frontend.components.myrequestsparringstatus.MyRequestSparringStatusController;
import com.trainme.jerald.frontend.components.profile.ProfileController;
import com.trainme.jerald.frontend.components.profile.UpdateProfileController;
import com.trainme.jerald.frontend.components.profile.UploadCertificateController;
import com.trainme.jerald.frontend.components.ranking.RankingManController;
import com.trainme.jerald.frontend.components.ranking.RankingWomanController;
import com.trainme.jerald.frontend.components.requestercoaching.RequesterCoachingController;
import com.trainme.jerald.frontend.components.requestersparring.RequesterSparringController;
import com.trainme.jerald.frontend.components.requestjoin.RequestJoinController;
import com.trainme.jerald.frontend.components.requestjoincoach.RequestJoinCoachController;
import com.trainme.jerald.frontend.components.signin.SigninController;
import com.trainme.jerald.frontend.components.signup.SignupController;
import com.trainme.jerald.frontend.components.sparring.SparringController;
import com.trainme.jerald.frontend.components.splashscreen.SplashScreenController;
import com.trainme.jerald.frontend.components.stucoaching.StudentCoachingController;
import com.trainme.jerald.frontend.dependencies.webservices.AddCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.AddSparringService;
import com.trainme.jerald.frontend.dependencies.webservices.ApprovalCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.ApprovalSparringService;
import com.trainme.jerald.frontend.dependencies.webservices.EventPhotoService;
import com.trainme.jerald.frontend.dependencies.webservices.EventService;
import com.trainme.jerald.frontend.dependencies.webservices.HistoryCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.HistorySparringService;
import com.trainme.jerald.frontend.dependencies.webservices.InformasiUmumService;
import com.trainme.jerald.frontend.dependencies.webservices.KebijakanPrivasiService;
import com.trainme.jerald.frontend.dependencies.webservices.MyRequestCoachingStatusService;
import com.trainme.jerald.frontend.dependencies.webservices.MyRequestSparringStatusService;
import com.trainme.jerald.frontend.dependencies.webservices.ProfileService;
import com.trainme.jerald.frontend.dependencies.webservices.RankingManService;
import com.trainme.jerald.frontend.dependencies.webservices.RankingWomanService;
import com.trainme.jerald.frontend.dependencies.webservices.RequestJoinCoachService;
import com.trainme.jerald.frontend.dependencies.webservices.RequestJoinService;
import com.trainme.jerald.frontend.dependencies.webservices.RequesterCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.RequesterSparringService;
import com.trainme.jerald.frontend.dependencies.webservices.SigninService;
import com.trainme.jerald.frontend.dependencies.webservices.SignupService;
import com.trainme.jerald.frontend.dependencies.webservices.SliderServices;
import com.trainme.jerald.frontend.dependencies.webservices.SparringService;
import com.trainme.jerald.frontend.dependencies.webservices.StudentCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.UpdateProfileService;
import com.trainme.jerald.frontend.dependencies.webservices.UploadCertificateService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MainApplication app;

    public AppModule(MainApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    AddCoachingController addCoachingController() {
        return new AddCoachingController(app.getComponent());
    }

    @Provides
    @Singleton
    AddCoachingService addCoachingService() {
        return new AddCoachingService(app.getComponent());
    }

    @Provides
    @Singleton
    UploadCertificateController uploadCertificateController() {
        return new UploadCertificateController(app.getComponent());
    }

    @Provides
    @Singleton
    UploadCertificateService uploadCertificateService() {
        return new UploadCertificateService(app.getComponent());
    }

    @Provides
    @Singleton
    AddSparringController addSparringController() {
        return new AddSparringController(app.getComponent());
    }

    @Provides
    @Singleton
    EventPhotoService eventPhotoService() {
        return new EventPhotoService(app.getComponent());
    }

    @Provides
    @Singleton
    EventPhotoController eventPhotoController() {
        return new EventPhotoController(app.getComponent());
    }

    @Provides
    @Singleton
    RankingManService rankingManService() {
        return new RankingManService(app.getComponent());
    }

    @Provides
    @Singleton
    RankingManController rankingManController() {
        return new RankingManController(app.getComponent());
    }

    @Provides
    @Singleton
    RankingWomanService rankingWomanService() {
        return new RankingWomanService(app.getComponent());
    }

    @Provides
    @Singleton
    RankingWomanController rankingWomanController() {
        return new RankingWomanController(app.getComponent());
    }

    @Provides
    @Singleton
    AddSparringService addSparringService() {
        return new AddSparringService(app.getComponent());
    }

    @Provides
    @Singleton
    StudentCoachingController studentCoachingController() {
        return new StudentCoachingController(app.getComponent());
    }

    @Provides
    @Singleton
    StudentCoachingService studentCoachingService() {
        return new StudentCoachingService(app.getComponent());
    }

    @Provides
    @Singleton
    HistoryCoachingController historyCoachingController() {
        return new HistoryCoachingController(app.getComponent());
    }

    @Provides
    @Singleton
    HistoryCoachingService historyCoachingService() {
        return new HistoryCoachingService(app.getComponent());
    }

    @Provides
    @Singleton
    HistorySparringController historySparringController() {
        return new HistorySparringController(app.getComponent());
    }

    @Provides
    @Singleton
    HistorySparringService historySparringService() {
        return new HistorySparringService(app.getComponent());
    }

    @Provides
    @Singleton
    SignupController signupController() {
        return new SignupController(app.getComponent());
    }

    @Provides
    @Singleton
    SignupService signupService() {
        return new SignupService(app.getComponent());
    }

    @Provides
    @Singleton
    SigninController signinController() {
        return new SigninController(app.getComponent());
    }

    @Provides
    @Singleton
    SigninService signinService() {
        return new SigninService(app.getComponent());
    }

    @Provides
    @Singleton
    SparringController sparringController() {
        return new SparringController(app.getComponent());
    }

    @Provides
    @Singleton
    SparringService sparringService() {
        return new SparringService(app.getComponent());
    }

    @Provides
    @Singleton
    RequestJoinController requestJoinController() {
        return new RequestJoinController(app.getComponent());
    }

    @Provides
    @Singleton
    RequestJoinService requestJoinService() {
        return new RequestJoinService(app.getComponent());
    }

    @Provides
    @Singleton
    RequestJoinCoachController requestJoinCoachController() {
        return new RequestJoinCoachController(app.getComponent());
    }

    @Provides
    @Singleton
    InformasiUmumService informasiUmumService() {
        return new InformasiUmumService(app.getComponent());
    }

    @Provides
    @Singleton
    InformasiUmumController informasiUmumController() {
        return new InformasiUmumController(app.getComponent());
    }

    @Provides
    @Singleton
    UpdateProfileService updateProfileService() {
        return new UpdateProfileService(app.getComponent());
    }

    @Provides
    @Singleton
    UpdateProfileController updateProfileController() {
        return new UpdateProfileController(app.getComponent());
    }

    @Provides
    @Singleton
    EventService eventService() {
        return new EventService(app.getComponent());
    }

    @Provides
    @Singleton
    EventController eventController() {
        return new EventController(app.getComponent());
    }

    @Provides
    @Singleton
    KebijakanPrivasiService kebijakanPrivasiService() {
        return new KebijakanPrivasiService(app.getComponent());
    }

    @Provides
    @Singleton
    ProfileController profileController() {
        return new ProfileController(app.getComponent());
    }

    @Provides
    @Singleton
    ProfileService profileService() {
        return new ProfileService(app.getComponent());
    }

    @Provides
    @Singleton
    KebijakanPrivasiController kebijakanPrivasiController() {
        return new KebijakanPrivasiController(app.getComponent());
    }

    @Provides
    @Singleton
    RequestJoinCoachService requestJoinCoachService() {
        return new RequestJoinCoachService(app.getComponent());
    }

    @Provides
    @Singleton
    ApprovalSparringController approvalSparringController() {
        return new ApprovalSparringController(app.getComponent());
    }

    @Provides
    @Singleton
    ApprovalSparringService approvalSparringService() {
        return new ApprovalSparringService(app.getComponent());
    }

    @Provides
    @Singleton
    ApprovalCoachingController approvalCoachingController() {
        return new ApprovalCoachingController(app.getComponent());
    }

    @Provides
    @Singleton
    ApprovalCoachingService approvalCoachingService() {
        return new ApprovalCoachingService(app.getComponent());
    }

    @Provides
    @Singleton
    RequesterSparringController requesterSparringController() {
        return new RequesterSparringController(app.getComponent());
    }

    @Provides
    @Singleton
    RequesterSparringService requesterSparringService() {
        return new RequesterSparringService(app.getComponent());
    }

    @Provides
    @Singleton
    RequesterCoachingController requesterCoachingController() {
        return new RequesterCoachingController(app.getComponent());
    }

    @Provides
    @Singleton
    RequesterCoachingService requesterCoachingService() {
        return new RequesterCoachingService(app.getComponent());
    }

    @Provides
    @Singleton
    MyRequestSparringStatusController myRequestSparringStatusController() {
        return new MyRequestSparringStatusController(app.getComponent());
    }

    @Provides
    @Singleton
    MyRequestSparringStatusService myRequestSparringStatusService() {
        return new MyRequestSparringStatusService(app.getComponent());
    }

    @Provides
    @Singleton
    MyRequestCoachingStatusController myRequestCoachingStatusController() {
        return new MyRequestCoachingStatusController(app.getComponent());
    }

    @Provides
    @Singleton
    MyRequestCoachingStatusService myRequestCoachingStatusService() {
        return new MyRequestCoachingStatusService(app.getComponent());
    }

    @Provides
    @Singleton
    HomeController homeController() {
        return new HomeController(app.getComponent());
    }

    @Provides
    @Singleton
    SplashScreenController splashScreenController() {
        return new SplashScreenController(app.getComponent());
    }

    @Provides
    @Singleton
    SliderServices sliderServices() {
        return new SliderServices(app.getComponent());
    }
}
