package xuxu.ebookproject.ui.ebook.account.sign_up;

import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import xuxu.ebookproject.model.RegisterViewModel;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentPresenter;

/**
 * Created by phanx on 29/11/2016.
 */
public class SignUpFragmentPresenter extends BaseFragmentPresenter<SignUpFragmentView>{
    protected AccountService mAccountService;

    public SignUpFragmentPresenter(SignUpFragmentView view, AccountService accountService) {
        super(view);
        this.mAccountService = accountService;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpReCaptcha();
        mView.setUpEventButton();
    }

    public void SignUp(){

        String userName = mView.getUserName();
        if(userName.isEmpty()){
            mView.setErrorUserName();
            return;
        }
        String email = mView.getEmail();
        if(email.isEmpty()){
            mView.setErrorEmail();
            return;
        }
        String password = mView.getPassword();
        if(password.isEmpty()){
            mView.setErrorPassword();
            return;
        }
        String rePassword = mView.getRePassword();
        if(rePassword.isEmpty()){
            mView.setErrorEmptyRePassword();
            return;
        }
        if(!password.equals(rePassword)){
            mView.setErrorNotMathPassword();
            return;
        }
        String reCaptchaCode = mView.getReCaptchaCode();
        if(reCaptchaCode == null || reCaptchaCode.isEmpty()){
            mView.setErrorEmptyReCaptchaCode();
            return;
        }
        RegisterViewModel registerViewModel = new RegisterViewModel(userName, email, password, rePassword, reCaptchaCode);

        mAccountService.signUpAsync(registerViewModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delaySubscription(2, TimeUnit.SECONDS)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.setUpSignUpProgressDialog();
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.dismissSignUpProgressDialog();
                    }
                })
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mView.setUpSuccessSignUp();
                    }
                });
    }
}
