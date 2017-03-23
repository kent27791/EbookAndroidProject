package xuxu.ebookproject.service;

import retrofit2.Call;
import retrofit2.http.GET;
import xuxu.ebookproject.model.CategoryViewModel;
import xuxu.ebookproject.model.PageResponseViewModel;

import static xuxu.ebookproject.config.Network.BASE_URL;

public interface CategoryService {
    @GET(BASE_URL + "api/category/GetRecursiveTypeViewModel/")
    Call<PageResponseViewModel<CategoryViewModel>> getCategoryAsync();
}
