package com.trainme.jerald.frontend.dependencies.component;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.components.MainActivity;
import com.trainme.jerald.frontend.components.addcoaching.AddCoachingActivity;
import com.trainme.jerald.frontend.components.addcoaching.AddCoachingController;
import com.trainme.jerald.frontend.components.addsparring.AddSparringActivity;
import com.trainme.jerald.frontend.components.addsparring.AddSparringController;
import com.trainme.jerald.frontend.components.approval.ApprovalSparringController;
import com.trainme.jerald.frontend.components.approval.ApprovalSparringFragment;
import com.trainme.jerald.frontend.components.approvalcoaching.ApprovalCoachingController;
import com.trainme.jerald.frontend.components.approvalcoaching.ApprovalCoachingFragment;
import com.trainme.jerald.frontend.components.eventphoto.EventPhotoController;
import com.trainme.jerald.frontend.components.events.DetailEventActivity;
import com.trainme.jerald.frontend.components.events.EventActivity;
import com.trainme.jerald.frontend.components.events.EventController;
import com.trainme.jerald.frontend.components.historycoaching.HistoryCoachingActivity;
import com.trainme.jerald.frontend.components.historycoaching.HistoryCoachingController;
import com.trainme.jerald.frontend.components.historysparring.HistorySparringActivity;
import com.trainme.jerald.frontend.components.historysparring.HistorySparringController;
import com.trainme.jerald.frontend.components.home.HomeController;
import com.trainme.jerald.frontend.components.informasi.InformasiUmumActivity;
import com.trainme.jerald.frontend.components.informasi.InformasiUmumController;
import com.trainme.jerald.frontend.components.informasi.KebijakanPrivasiActivity;
import com.trainme.jerald.frontend.components.informasi.KebijakanPrivasiController;
import com.trainme.jerald.frontend.components.myrequestcoachingstatus.MyRequestCoachingStatusActivity;
import com.trainme.jerald.frontend.components.myrequestcoachingstatus.MyRequestCoachingStatusController;
import com.trainme.jerald.frontend.components.myrequestsparringstatus.MyRequestSparringStatusController;
import com.trainme.jerald.frontend.components.myrequestsparringstatus.MyRequestStatusActivity;
import com.trainme.jerald.frontend.components.profile.ProfileActivity;
import com.trainme.jerald.frontend.components.profile.ProfileController;
import com.trainme.jerald.frontend.components.profile.UpdateProfileActivity;
import com.trainme.jerald.frontend.components.profile.UpdateProfileContract;
import com.trainme.jerald.frontend.components.profile.UpdateProfileController;
import com.trainme.jerald.frontend.components.profile.UploadCertificateController;
import com.trainme.jerald.frontend.components.ranking.RankingManActivity;
import com.trainme.jerald.frontend.components.ranking.RankingManController;
import com.trainme.jerald.frontend.components.ranking.RankingWomanActivity;
import com.trainme.jerald.frontend.components.ranking.RankingWomanController;
import com.trainme.jerald.frontend.components.requestercoaching.RequesterCoachingActivity;
import com.trainme.jerald.frontend.components.requestercoaching.RequesterCoachingController;
import com.trainme.jerald.frontend.components.requestersparring.RequesterSparringActivity;
import com.trainme.jerald.frontend.components.requestersparring.RequesterSparringController;
import com.trainme.jerald.frontend.components.requestjoin.RequestJoinController;
import com.trainme.jerald.frontend.components.requestjoin.RequestJoinFragment;
import com.trainme.jerald.frontend.components.requestjoincoach.RequestJoinCoachController;
import com.trainme.jerald.frontend.components.requestjoincoach.RequestJoinCoachFragment;
import com.trainme.jerald.frontend.components.signin.SigninActivity;
import com.trainme.jerald.frontend.components.signin.SigninController;
import com.trainme.jerald.frontend.components.signup.SignupActivity;
import com.trainme.jerald.frontend.components.signup.SignupController;
import com.trainme.jerald.frontend.components.sparring.SparringActivity;
import com.trainme.jerald.frontend.components.sparring.SparringController;
import com.trainme.jerald.frontend.components.splashscreen.SplashScreenActivity;
import com.trainme.jerald.frontend.components.splashscreen.SplashScreenController;
import com.trainme.jerald.frontend.components.stucoaching.StudentCoachingActivity;
import com.trainme.jerald.frontend.components.stucoaching.StudentCoachingController;
import com.trainme.jerald.frontend.dependencies.modules.AppModule;
import com.trainme.jerald.frontend.dependencies.modules.RealmModule;
import com.trainme.jerald.frontend.dependencies.modules.WebServiceModule;
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

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RealmModule.class, WebServiceModule.class})
public interface AppComponent {
    void inject(MainApplication app);

    void inject(RequestJoinFragment fragment);

    void inject(RequestJoinCoachFragment fragment);

    void inject(ApprovalSparringFragment fragment);

    void inject(ApprovalCoachingFragment fragment);

    void inject(MainActivity activity);

    void inject(SignupActivity activity);

    void inject(SigninActivity activity);

    void inject(SparringActivity activity);

    void inject(DetailEventActivity activity);

    void inject(ProfileActivity activity);

    void inject(KebijakanPrivasiActivity activity);

    void inject(RankingManActivity activity);

    void inject(RankingWomanActivity activity);

    void inject(EventActivity activity);

    void inject(InformasiUmumActivity activity);

    void inject(MyRequestCoachingStatusActivity activity);

    void inject(AddSparringActivity activity);

    void inject(RequesterSparringActivity activity);

    void inject(RequesterCoachingActivity activity);

    void inject(AddCoachingActivity activity);

    void inject(StudentCoachingActivity activity);

    void inject(HistorySparringActivity activity);

    void inject(HistoryCoachingActivity activity);

    void inject(SplashScreenActivity activity);

    void inject(MyRequestStatusActivity activity);

    void inject(UpdateProfileActivity activity);

    void inject(HomeController controller);

    void inject(RequesterSparringController controller);

    void inject(RankingWomanController controller);

    void inject(RankingManController controller);

    void inject(RequesterCoachingController controller);

    void inject(ProfileController controller);

    void inject(ApprovalSparringController controller);

    void inject(ApprovalCoachingController controller);

    void inject(KebijakanPrivasiController controller);

    void inject(EventPhotoController controller);

    void inject(InformasiUmumController controller);

    void inject(AddCoachingController controller);

    void inject(EventController controller);

    void inject(MyRequestSparringStatusController controller);

    void inject(MyRequestCoachingStatusController controller);

    void inject(AddSparringController controller);

    void inject(HistorySparringController controller);

    void inject(UpdateProfileController controller);

    void inject(HistoryCoachingController controller);

    void inject(SplashScreenController controller);

    void inject(SignupController controller);

    void inject(SigninController controller);

    void inject(SparringController controller);

    void inject(StudentCoachingController controller);

    void inject(UploadCertificateController controller);

    void inject(RequestJoinController controller);

    void inject(RequestJoinCoachController controller);

    void inject(SignupService service);

    void inject(ApprovalSparringService service);

    void inject(ApprovalCoachingService service);

    void inject(UpdateProfileService service);

    void inject(SparringService service);

    void inject(HistorySparringService service);

    void inject(RequesterSparringService service);

    void inject(RankingManService service);

    void inject(RankingWomanService service);

    void inject(EventPhotoService service);

    void inject(ProfileService service);

    void inject(RequesterCoachingService service);

    void inject(EventService service);

    void inject(KebijakanPrivasiService service);

    void inject(InformasiUmumService service);

    void inject(AddSparringService service);

    void inject(HistoryCoachingService service);

    void inject(StudentCoachingService service);

    void inject(MyRequestSparringStatusService service);

    void inject(MyRequestCoachingStatusService service);

    void inject(AddCoachingService service);

    void inject(SigninService service);

    void inject(SliderServices services);

    void inject(RequestJoinService service);

    void inject(RequestJoinCoachService service);

    void inject(UploadCertificateService service);
}
