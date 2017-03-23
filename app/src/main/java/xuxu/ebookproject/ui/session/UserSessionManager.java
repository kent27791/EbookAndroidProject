package xuxu.ebookproject.ui.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import xuxu.ebookproject.model.AuthViewModel;


public class UserSessionManager {

    Context mContext;

    SharedPreferences mSharedPreferences;

    SharedPreferences.Editor mEditor;

    Gson mGson;

    //AccountService mAccountService;

    int PRIVATE_MODE = 0;

    private static final String PREFER_KEY = "EBOOK_USER_SESSION";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String USER_LOGIN_OBJECT = "UserLoginObject";

    public UserSessionManager(Context context, Gson gson){
        this.mContext = context;
        this.mGson = gson;
        //this.mAccountService = accountService;
        mSharedPreferences = context.getSharedPreferences(PREFER_KEY, PRIVATE_MODE);
        mEditor = mSharedPreferences.edit();
    }

    public void createUserLoginSession(AuthViewModel authViewModel){
        mEditor.putBoolean(IS_USER_LOGIN, true);
        mEditor.putString(USER_LOGIN_OBJECT, mGson.toJson(authViewModel));
        mEditor.commit();
    }

    public AuthViewModel getUserLoginSession(){
        AuthViewModel authViewModel = mGson.fromJson(mSharedPreferences.getString(USER_LOGIN_OBJECT, null), AuthViewModel.class);
        /*if(authViewModel.Expires.before(new Date())){
            //call server to get refresh token or login again here...
            return null;
        } else {
            return authViewModel;
        }*/
        return authViewModel;
    }

    public void clearUserLoginSession(){
        mEditor.clear();
        mEditor.commit();
    }

    public boolean isUserLoggedIn(){
        return mSharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }
}
