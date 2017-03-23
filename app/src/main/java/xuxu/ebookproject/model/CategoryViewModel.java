package xuxu.ebookproject.model;

import java.util.ArrayList;

/**
 * Created by phanx on 04/11/2016.
 */

public class CategoryViewModel {
    public CategoryViewModel(){

    }
    public CategoryViewModel(int id, String name){
        this(id, name, new ArrayList<CategoryViewModel>());
    }
    public CategoryViewModel(int id, String name, ArrayList<CategoryViewModel> childrens){
        this.Id = id;
        this.Name = name;
        this.Childrens = childrens;
    }
    public int Id;
    public String Name;
    public ArrayList<CategoryViewModel> Childrens;
}
