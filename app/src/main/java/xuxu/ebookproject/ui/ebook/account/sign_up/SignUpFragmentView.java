package xuxu.ebookproject.ui.ebook.account.sign_up;

import xuxu.ebookproject.ui.base.fragment.BaseFragmentView;

/**
 * Created by phanx on 29/11/2016.
 */
public interface SignUpFragmentView extends BaseFragmentView {
    String getUserName();

    void setErrorUserName();

    String getEmail();

    void setErrorEmail();

    String getPassword();

    void setErrorPassword();

    String getRePassword();

    void setErrorEmptyRePassword();

    void setErrorNotMathPassword();

    void setUpEventButton();

    void setUpReCaptcha();

    String getReCaptchaCode();

    void setErrorEmptyReCaptchaCode();

    void setUpSignUpProgressDialog();

    void dismissSignUpProgressDialog();

    void setUpSuccessSignUp();
}
