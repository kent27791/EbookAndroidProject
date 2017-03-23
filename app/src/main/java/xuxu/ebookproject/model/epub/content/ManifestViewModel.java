package xuxu.ebookproject.model.epub.content;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ManifestViewModel {
    @SerializedName("item")
    public ArrayList<ItemManifestViewModel> Items;
}
