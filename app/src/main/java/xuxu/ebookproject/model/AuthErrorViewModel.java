package xuxu.ebookproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phanx on 06/12/2016.
 */

public class AuthErrorViewModel {
    @SerializedName("error")
    public String Error;
    @SerializedName("error_description")
    public String ErrorDescription;
}
