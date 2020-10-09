package com.trainme.jerald.frontend.dependencies.webservices;

import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;
import com.trainme.jerald.frontend.dependencies.models.CoachingCreateModel;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;
import com.trainme.jerald.frontend.dependencies.models.SigninModel;
import com.trainme.jerald.frontend.dependencies.models.SignupModel;
import com.trainme.jerald.frontend.dependencies.models.SparringCreateModel;
import com.trainme.jerald.frontend.dependencies.response.ResponseAllRequesterSparring;
import com.trainme.jerald.frontend.dependencies.response.ResponseApprovalSparing;
import com.trainme.jerald.frontend.dependencies.response.ResponseAuth;
import com.trainme.jerald.frontend.dependencies.response.ResponseCreateCoaching;
import com.trainme.jerald.frontend.dependencies.response.ResponseCreateSparring;
import com.trainme.jerald.frontend.dependencies.response.ResponseGetAds;
import com.trainme.jerald.frontend.dependencies.response.ResponseGetEvent;
import com.trainme.jerald.frontend.dependencies.response.ResponseGetProfile;
import com.trainme.jerald.frontend.dependencies.response.ResponseInformasiUmum;
import com.trainme.jerald.frontend.dependencies.response.ResponseKebijakanPrivasi;
import com.trainme.jerald.frontend.dependencies.response.ResponseMyRequestSparring;
import com.trainme.jerald.frontend.dependencies.response.ResponseMySparring;
import com.trainme.jerald.frontend.dependencies.response.ResponsePhotoEvent;
import com.trainme.jerald.frontend.dependencies.response.ResponseRanking;
import com.trainme.jerald.frontend.dependencies.response.ResponseRequestJoin;
import com.trainme.jerald.frontend.dependencies.response.ResponseSparingInvitation;
import com.trainme.jerald.frontend.dependencies.response.ResponseUpdateProfile;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TrainmeAPI {
    @POST("users/signup")
    Call<ResponseAuth> registerUser(@Body SignupModel signupModel);

    @POST("users/login")
    Call<ResponseAuth> loginUser(@Body SigninModel signinModel);

    @POST("play/create/sparing")
    Call<ResponseCreateSparring> createSparing(@Body SparringCreateModel sparringCreateModel);

    //All sparring
    @GET("play/invitation/sparing")
    Call<ResponseSparingInvitation> getAllSparring();

    @POST("play/request/sparing")
    Call<ResponseRequestJoin> requestJoinSparing(@Body RequestJoinSparing requestJoinSparing);

    @GET("play/sparing/history/{user_id}")
    Call<ResponseMySparring> getMySparringHistory(@Path("user_id") int userId);

    @GET("play/listrequest/sparing/{play_id}")
    Call<ResponseAllRequesterSparring> getAllRequesterBySparring(@Path("play_id") int playId);

    @POST("play/approval/sparing")
    Call<ResponseApprovalSparing> approveRequestSparing(@Body ApprovalSparing approvalSparing);

    @GET("play/allstatus/request/{user_id}")
    Call<ResponseMyRequestSparring> getAllMyRequestSparringStatus(@Path("user_id") int userId);

    @POST("play/create/coaching")
    Call<ResponseCreateCoaching> createCoaching(@Body CoachingCreateModel coachingCreateModel);

    @GET("play/invitation/coaching")
    Call<ResponseSparingInvitation> getAllRequestCoaching();

    @POST("play/request/coaching")
    Call<ResponseRequestJoin> requestJoinCoaching(@Body RequestJoinSparing requestJoinSparing);

    @GET("play/coaching/history/{user_id}")
    Call<ResponseSparingInvitation> getHistoryCoaching(@Path("user_id") int id);

    @GET("play/allstatuscoaching/request/{user_id}")
    Call<ResponseMyRequestSparring> getAllMyRequestCoachingStatus(@Path("user_id") int userId);

    @GET("users/getprofile/{user_id}")
    Call<ResponseGetProfile> getProfile(@Path("user_id") int userId);

    @GET("play/listrequest/coaching/{play_id}")
    Call<ResponseAllRequesterSparring> getAllRequesterByCoaching(@Path("play_id") int playId);

    @GET("event/photo/{id}")
    Call<ResponsePhotoEvent> getPhotoEvent(@Path("id") int id);

    @POST("play/approval/sparing")
    Call<ResponseApprovalSparing> approveRequestCoaching(@Body ApprovalSparing approvalSparing);

    @GET("ads/get-all")
    Call<ResponseGetAds> getAds();

    @GET("informasi/getInformasiUmum")
    Call<ResponseInformasiUmum> getInformasiUmum();

    @GET("informasi/getKebijakanPrivasi")
    Call<ResponseKebijakanPrivasi> getKebijakanPrivasi();

    @GET("event/list")
    Call<ResponseGetEvent> getEvents();

    @GET("ranking/list-woman")
    Call<ResponseRanking> getWomanRanking();

    @GET("ranking/list-man")
    Call<ResponseRanking> getManRanking();

    @Multipart
    @POST("users/certificate")
    Call<ResponseUpdateProfile> uploadCertificate(@Part("certificate\"; filename=\"myfile.jpg\" ") RequestBody file,
                                              @Part("users_id") RequestBody userId);

    @Multipart
    @POST("users/update")
    Call<ResponseUpdateProfile> updateProfile(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                              @Part("user_id") RequestBody userId);
}
