package xuxu.ebookproject.model;

/**
 * Created by phanx on 10/12/2016.
 */

public class RegisterViewModel {
    public RegisterViewModel(String userName, String email, String password, String confirmPassword, String reCaptcha){
        this.UserName = userName;
        this.Email = email;
        this.Password = password;
        this.ConfirmPassword = confirmPassword;
        this.ReCaptcha = reCaptcha;
    }
    public String UserName;
    public String Email;
    public String Password;
    public String ConfirmPassword;
    public String ReCaptcha;
}
