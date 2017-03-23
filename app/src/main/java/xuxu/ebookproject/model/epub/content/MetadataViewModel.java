package xuxu.ebookproject.model.epub.content;


import com.google.gson.annotations.SerializedName;

public class MetadataViewModel {
    @SerializedName("dc:title")
    public String Title;

    @SerializedName("dc:creator")
    public CreatorViewModel Creator;

}
