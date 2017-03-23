package xuxu.ebookproject.model.epub.content;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpineViewModel {
    @SerializedName("itemref")
    public ArrayList<ItemRefViewModel> ItemsRef;
}
