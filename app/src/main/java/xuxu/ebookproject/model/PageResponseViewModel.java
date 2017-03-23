package xuxu.ebookproject.model;

import java.util.ArrayList;

/**
 * Created by phanx on 22/11/2016.
 */

public class PageResponseViewModel<T>  {
    public int CurrentPage;
    public int PageSize;
    public int TotalCount;
    public int TotalPages;
    public ArrayList<T> Entities;
}
