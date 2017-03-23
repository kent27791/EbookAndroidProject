package xuxu.ebookproject.model.epub.content;


import com.google.gson.annotations.SerializedName;

public class CreatorViewModel {
    @SerializedName("@opf:role")
    public String Role;

    @SerializedName("#text")
    public String Text;
}
