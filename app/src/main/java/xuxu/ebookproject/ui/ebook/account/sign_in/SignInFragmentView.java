package xuxu.ebookproject.ui.ebook.account.sign_in;

import xuxu.ebookproject.model.AuthErrorViewModel;
import xuxu.ebookproject.model.AuthViewModel;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentView;

/**
 * Created by phanx on 29/11/2016.
 */
public interface SignInFragmentView extends BaseFragmentView {
    void setUpSignInSuccess(AuthViewModel authViewModel);

    void setUpSignInError(AuthErrorViewModel authErrorViewModel);

    void setUpEventButton();

    String getUseName();

    void setErrorUserName();

    String getPassword();

    void setErrorPassword();

    void setUpSignInProgressDialog();

    void dismissSignInProgressDialog();

}
