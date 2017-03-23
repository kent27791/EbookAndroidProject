package xuxu.ebookproject.model.epub.toc;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NavPointViewModel {
    @SerializedName("navLabel")
    public NavLabelViewModel NavLabel;

    @SerializedName("content")
    public ContentViewModel Content;

    @SerializedName("navPoint")
    public ArrayList<NavPointViewModel> ChildNavPoints;

}
