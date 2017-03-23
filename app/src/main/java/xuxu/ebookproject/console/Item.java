package xuxu.ebookproject.console;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phanx on 22/12/2016.
 */

public class Item {
    @SerializedName("@href")
    public String Href;
    @SerializedName("@id")
    public String Id;
    @SerializedName("@media-type")
    public String MediaType;
}
