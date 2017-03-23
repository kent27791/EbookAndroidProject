package xuxu.ebookproject.console;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class EPub {
    @SerializedName("item")
    public ArrayList<Item> Items;
    @SerializedName("itemref")
    public ArrayList<ItemRef> ItemsRef;

    //@SerializedName("itemGson")
    //public ArrayList<ItemGson> ItemsGson;

    //public EPub(ArrayList<ItemGson> itemsGson){
    //   this.ItemsGson = itemsGson;
    //}
}
