package xuxu.ebookproject.ui.ebook.book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.model.BookDetailsViewModel;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.service.CategoryService;
import xuxu.ebookproject.service.SearchService;
import xuxu.ebookproject.ui.base.activity.BaseActivityPresenter;


public class BookActivityPresenter extends BaseActivityPresenter<BookActivityView> {
    private BookService mBookService;

    public BookActivityPresenter(BookActivityView view, CategoryService categoryService, SearchService searchService, BookService bookService) {
        super(view, categoryService, searchService);
        this.mBookService = bookService;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpEventButton();
        getBook();
    }

    private void getBook(){
        mBookService.getBookAsync(mView.getBookId()).enqueue(new Callback<BookDetailsViewModel>() {
            @Override
            public void onResponse(Call<BookDetailsViewModel> call, Response<BookDetailsViewModel> response) {
                BookDetailsViewModel bookDetailsViewModel = response.body();
                mView.bindBookDetails(bookDetailsViewModel);
                getRelatedBooksByCategory(bookDetailsViewModel.CategoryViewModel.Value);
            }

            @Override
            public void onFailure(Call<BookDetailsViewModel> call, Throwable t) {

            }
        });
    }
    private void getRelatedBooksByCategory(int cateId){
        mBookService.getRelatedBooksByCategoryAsync(cateId).enqueue(new Callback<ArrayList<BookViewModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BookViewModel>> call, Response<ArrayList<BookViewModel>> response) {
                mView.setUpRelatedBookRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<BookViewModel>> call, Throwable t) {

            }
        });
    }
}
