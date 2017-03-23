package xuxu.ebookproject.ui.ebook.category;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.model.PageResponseViewModel;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.base.activity.BaseActivityPresenter;

public class CategoryActivityPresenter extends BaseActivityPresenter<CategoryActivityView> {
    protected BookService mBookService;

    public CategoryActivityPresenter(CategoryActivityView view, CategoryService categoryService, SearchService searchService, BookService bookService) {
        super(view, categoryService, searchService);
        this.mBookService = bookService;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpBooksByCategoryRecyclerView();
        getBooksByCategory(mView.getCategoryId(), 1, 18, "Title", "asc");
        mView.setUpBookSortAlertDialog();
    }

    public void getBooksByCategory(int cateId, int page, int pageSize, String sortBy, String sortDir){
        mBookService.getBooksByCategoryAsync(cateId, page, pageSize, sortBy, sortDir).enqueue(new Callback<PageResponseViewModel<BookViewModel>>() {
            @Override
            public void onResponse(Call<PageResponseViewModel<BookViewModel>> call, Response<PageResponseViewModel<BookViewModel>> response) {
                mView.setUpAdapterBooksByCategoryRecyclerView(response.body().Entities);
            }

            @Override
            public void onFailure(Call<PageResponseViewModel<BookViewModel>> call, Throwable t) {

            }
        });
    }
}
