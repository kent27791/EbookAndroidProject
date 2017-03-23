package xuxu.ebookproject.ui.ebook.account.sign_in;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import xuxu.ebookproject.model.AuthErrorViewModel;
import xuxu.ebookproject.model.AuthViewModel;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentPresenter;

public class SignInFragmentPresenter extends BaseFragmentPresenter<SignInFragmentView> {
    protected AccountService mAccountService;

    protected Gson mGson;

    public SignInFragmentPresenter(SignInFragmentView view, AccountService accountService, Gson gson) {
        super(view);
        this.mAccountService = accountService;
        this.mGson = gson;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpEventButton();
    }

    public void SignIn(){
        final String userName = mView.getUseName();
        if(userName.isEmpty()){
            mView.setErrorUserName();
            return;
        }
        final String password = mView.getPassword();
        if(password.isEmpty()){
            mView.setErrorPassword();
            return;
        }

         mAccountService.getTokenAsync("password", userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delaySubscription(2, TimeUnit.SECONDS)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.setUpSignInProgressDialog();
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.dismissSignInProgressDialog();
                    }
                })
                .subscribe(new Subscriber<AuthViewModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpException httpException = (HttpException) e;
                        if(httpException.code() == 400){
                            AuthErrorViewModel authErrorViewModel = mGson.fromJson(httpException.response().errorBody().charStream(), AuthErrorViewModel.class);
                            mView.setUpSignInError(authErrorViewModel);
                        }
                    }

                    @Override
                    public void onNext(AuthViewModel authViewModel) {
                        mView.setUpSignInSuccess(authViewModel);
                    }
                });


        /*new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        mAccountService.getTokenAsync("password", userName, password).enqueue(new Callback<AuthViewModel>() {
                            @Override
                            public void onResponse(Call<AuthViewModel> call, Response<AuthViewModel> response) {
                                mView.dismissSignInProgressDialog();
                                if(response.code() == 200){
                                    mView.setUpSignInSuccess(mGson.toJson(response.body()));
                                } else {
                                    try {
                                        AuthErrorViewModel authErrorViewModel = mGson.fromJson(response.errorBody().string(), AuthErrorViewModel.class);
                                        mView.setUpSignInError(authErrorViewModel);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<AuthViewModel> call, Throwable t) {
                                mView.dismissSignInProgressDialog();
                            }
                        });
                    }
                }, 5000);*/
    }

}
