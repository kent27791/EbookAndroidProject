package xuxu.ebookproject.ui.ebook.account.sign_in;


import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.AccountActivityComponent;
import xuxu.ebookproject.model.AuthErrorViewModel;
import xuxu.ebookproject.model.AuthViewModel;
import xuxu.ebookproject.ui.base.activity.BaseActivity;
import xuxu.ebookproject.ui.base.fragment.BaseFragment;


public class SignInFragment extends BaseFragment<SignInFragmentPresenter> implements SignInFragmentView {

    @BindView(R.id.sign_in_button)
    Button mSignInButton;

    @BindView(R.id.email_edit_text)
    EditText mEmailEditText;

    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void setUpComponent() {
        this.getComponent(AccountActivityComponent.class).inject(this);
    }

    @Override
    public void setUpSignInSuccess(AuthViewModel authViewModel) {
        Activity activity = getActivity();
        Toast.makeText(getContext(), "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
        ((BaseActivity)getActivity()).getUserSessionManager().createUserLoginSession(authViewModel);
        activity.finish();
        startActivity(activity.getIntent());
    }

    @Override
    public void setUpSignInError(AuthErrorViewModel authErrorViewModel) {
        Toast.makeText(getContext(), authErrorViewModel.ErrorDescription, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUpEventButton() {
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.SignIn();
            }
        });
    }

    @Override
    public String getUseName() {
        return mEmailEditText.getText().toString();
    }

    @Override
    public void setErrorUserName() {
        mEmailEditText.setError("Email không được bỏ trống.");
    }

    @Override
    public String getPassword() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public void setErrorPassword() {
        mPasswordEditText.setError("Password không được bỏ trống.");
    }

    @Override
    public void setUpSignInProgressDialog() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Sign In");
        mProgressDialog.setMessage("Authenticating...");
        mProgressDialog.show();
    }

    @Override
    public void dismissSignInProgressDialog() {
        mProgressDialog.dismiss();
    }

}
