package xuxu.ebookproject.service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import xuxu.ebookproject.model.AuthViewModel;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.model.PageResponseViewModel;
import xuxu.ebookproject.model.RegisterViewModel;
import xuxu.ebookproject.model.UserViewModel;

import static xuxu.ebookproject.config.Network.BASE_URL;

public interface AccountService {
   /* @FormUrlEncoded
    @POST(BASE_URL + "/token")
    Call<AuthViewModel> getTokenAsync(@Field("grant_type") String grantType,@Field("username") String userName,@Field("password") String password);*/

    @FormUrlEncoded
    @POST(BASE_URL + "/token")
    Observable<AuthViewModel> getTokenAsync(@Field("grant_type") String grantType, @Field("username") String userName, @Field("password") String password);

    @POST(BASE_URL + "api/user/register")
    Observable<ResponseBody> signUpAsync(@Body RegisterViewModel registerViewModel);

    @GET(BASE_URL + "api/book/LibraryBooks")
    Call<PageResponseViewModel<BookViewModel>> getLibraryBookAsync(@Query("page") int page, @Query("pageSize") int pageSize, @Query("sortBy") String sortBy, @Query("sortDir") String sortDir);

    @GET(BASE_URL + "api/user/UserProfile")
    Call<UserViewModel> getUserProfileAsync();
}
