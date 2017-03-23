package xuxu.ebookproject.model;

import java.util.Date;
import java.util.List;

/**
 * Created by phanx on 27/10/2016.
 */

public class BookDetailsViewModel extends BookViewModel {
    public String FileEpub;
    public boolean IsHot;
    public boolean IsNew;
    public String Keyword;
    public int Pages;
    public Date PublishDate;
    public int SKU;

    public List<DropDownListViewModel> TranslatorViewModel;
    public DropDownListViewModel CategoryViewModel;
    public DropDownListViewModel LanguageViewModel;
    public DropDownListViewModel PublisherViewModel;
    public DropDownListViewModel SupplierViewModel;
}
