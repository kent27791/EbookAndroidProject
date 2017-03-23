package xuxu.ebookproject.model;

import java.util.ArrayList;

/**
 * Created by phanx on 19/10/2016.
 */

public class BookViewModel {
    public int Id;
    public String Title;
    public double Sale;
    public double SaleOff;
    public String Sumary;
    public String SumaryText;
    public String UrlThumbnail;
    public ArrayList<DropDownListViewModel> AuthorsViewModel;
}
