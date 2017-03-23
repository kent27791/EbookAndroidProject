package xuxu.ebookproject.ui.ebook.account.sign_up;


import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.AccountActivityComponent;
import xuxu.ebookproject.ui.base.fragment.BaseFragment;
import xuxu.ebookproject.ui.ebook.account.AccountActivity;

import static xuxu.ebookproject.config.Network.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment<SignUpFragmentPresenter> implements SignUpFragmentView {
    @BindView(R.id.user_name_edit_text)
    EditText mUserNameEditText;

    @BindView(R.id.email_edit_text)
    EditText mEmailEditText;

    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;

    @BindView(R.id.re_password_edit_text)
    EditText mRePasswordEditText;

    @BindView(R.id.re_captcha_web_view)
    WebView mReCaptchaWebView;

    private String mReCaptchaCode = null;

    @BindView(R.id.sign_up_button)
    Button mSignUpButton;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    protected void setUpComponent() {
        this.getComponent(AccountActivityComponent.class).inject(this);
    }

    @Override
    public String getUserName() {
        return mUserNameEditText.getText().toString();
    }

    @Override
    public void setErrorUserName() {
        mUserNameEditText.setError("Username không được để trống.");
    }

    @Override
    public String getEmail() {
        return mEmailEditText.getText().toString();
    }

    @Override
    public void setErrorEmail() {
        mEmailEditText.setError("Email không được để trống.");
    }

    @Override
    public String getPassword() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public void setErrorPassword() {
        mPasswordEditText.setError("Password không được để trống.");
    }

    @Override
    public String getRePassword() {
        return mRePasswordEditText.getText().toString();
    }

    @Override
    public void setErrorEmptyRePassword() {
        mRePasswordEditText.setError("Re-Password không được để trống.");
    }

    @Override
    public void setErrorNotMathPassword() {
        mRePasswordEditText.setError("Re-Password không trùng khớp.");
    }

    @Override
    public void setUpReCaptcha() {
        mReCaptchaWebView.getSettings().setJavaScriptEnabled(true);
        mReCaptchaWebView.addJavascriptInterface(new ReCaptchaInterface(), "Android");
        mReCaptchaWebView.loadUrl(BASE_URL + "Ebook/Templates/Recaptcha");
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String getReCaptchaCode() {
        try {
            mReCaptchaWebView.loadUrl("javascript:getReCaptchaResponse();");
            Thread.sleep(500);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mReCaptchaCode;
    }

    @Override
    public void setErrorEmptyReCaptchaCode() {
        Toast.makeText(getActivity(), "Hãy thực hiện bước xác nhận.", Toast.LENGTH_SHORT).show();
    }

    public class ReCaptchaInterface{

        @JavascriptInterface
        public void getReCaptchaResponse(String response){
            mReCaptchaCode = response;
        }
    }

    @Override
    public void setUpSignUpProgressDialog() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setTitle("Sign Up");
        mProgressDialog.setMessage("Registering...");
        mProgressDialog.show();
    }

    @Override
    public void dismissSignUpProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void setUpSuccessSignUp() {
        Toast.makeText(getContext(), "Đăng ký tài khoản thành công.", Toast.LENGTH_SHORT).show();
        resetForm();
        ((AccountActivity)getActivity()).getAccountViewPager().setCurrentItem(0);
    }

    @Override
    public void setUpEventButton() {
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.SignUp();
            }
        });
    }

    public void resetForm(){
        mUserNameEditText.setText(null);
        mEmailEditText.setText(null);
        mPasswordEditText.setText(null);
        mRePasswordEditText.setText(null);
        mReCaptchaCode = null;
        mReCaptchaWebView.loadUrl("javascript:resetReCaptCha();");
    }


}
