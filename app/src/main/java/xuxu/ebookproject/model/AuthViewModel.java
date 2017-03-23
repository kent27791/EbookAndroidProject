package xuxu.ebookproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by phanx on 06/12/2016.
 */

public class AuthViewModel {
    @SerializedName(".expires")
    public Date Expires;
    @SerializedName(".issued")
    public Date Issued;
    @SerializedName("access_token")
    public String AccessToken;
    @SerializedName("as:client_id")
    public String ClientId;
    @SerializedName("email")
    public String Email;
    @SerializedName("expires_in")
    public int ExpiresIn;
    @SerializedName("sasLocalAccount")
    public boolean HasLocalAccount;
    @SerializedName("token_type")
    public String TokenType;
    @SerializedName("urlThumbnail")
    public String UrlThumbnail;
    @SerializedName("userName")
    public String UserName;
}
