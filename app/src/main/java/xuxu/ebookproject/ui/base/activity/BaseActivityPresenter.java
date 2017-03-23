package xuxu.ebookproject.ui.base.activity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xuxu.ebookproject.model.CategoryViewModel;
import xuxu.ebookproject.model.PageResponseViewModel;
import xuxu.ebookproject.model.SearchViewModel;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;

public class BaseActivityPresenter<V extends BaseActivityView> {
    protected V mView;
    protected CategoryService mCategoryService;
    protected SearchService mSearchService;
    public BaseActivityPresenter(V view, CategoryService categoryService, SearchService searchService){
        this.mView = view;
        this.mCategoryService = categoryService;
        this.mSearchService = searchService;
    }

    protected void onStart() {
        mView.setUpActionBarDrawer();
        getCategory();
    }

    protected void onStop(){

    }

    public void getDataSearch(String term){
        mSearchService.searchAsync(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delaySubscription(1, TimeUnit.SECONDS)
                .subscribe(new Subscriber<ArrayList<SearchViewModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<SearchViewModel> searchViewModels) {
                        mView.setUpProviderSearch(searchViewModels);
                    }
                });
    }

    protected void getCategory(){
        mCategoryService.getCategoryAsync().enqueue(new Callback<PageResponseViewModel<CategoryViewModel>>() {
            @Override
            public void onResponse(Call<PageResponseViewModel<CategoryViewModel>> call, Response<PageResponseViewModel<CategoryViewModel>> response) {
                mView.setUpMenuCategory(buildMenuCategory(response.body().Entities));
            }

            @Override
            public void onFailure(Call<PageResponseViewModel<CategoryViewModel>> call, Throwable t) {

            }
        });
    }

    protected ArrayList<CategoryViewModel> buildMenuCategory(ArrayList<CategoryViewModel> categories){
        ArrayList<CategoryViewModel> menuCategory = new ArrayList<>();

        menuCategory.add(new CategoryViewModel(-1, "Trang chủ"));

        menuCategory.add(new CategoryViewModel(-2, "Danh mục", categories));

        CategoryViewModel rankCategory = new CategoryViewModel(-3, "Xếp hạng");
        rankCategory.Childrens.add(new CategoryViewModel(-4, "Mới nhất"));
        rankCategory.Childrens.add(new CategoryViewModel(-5, "Hot nhất"));
        menuCategory.add(rankCategory);

        menuCategory.add(new CategoryViewModel(-6, "Liên hệ & trợ giúp"));

        return menuCategory;
    }
}
