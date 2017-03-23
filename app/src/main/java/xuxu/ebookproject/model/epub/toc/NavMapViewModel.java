package xuxu.ebookproject.model.epub.toc;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NavMapViewModel {
    @SerializedName("navPoint")
    public ArrayList<NavPointViewModel> NavPoints;
}
