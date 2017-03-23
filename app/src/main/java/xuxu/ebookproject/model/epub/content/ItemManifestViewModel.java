package xuxu.ebookproject.model.epub.content;


import com.google.gson.annotations.SerializedName;

public class ItemManifestViewModel {
    @SerializedName("@id")
    public String Id;
    @SerializedName("@href")
    public String Href;
    @SerializedName("@media-type")
    public String MediaType;
}
