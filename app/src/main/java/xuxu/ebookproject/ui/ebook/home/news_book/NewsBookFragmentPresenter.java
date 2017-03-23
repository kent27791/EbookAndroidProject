package xuxu.ebookproject.ui.ebook.home.news_book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.service.BookService;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentPresenter;

/**
 * Created by phanx on 27/11/2016.
 */
public class NewsBookFragmentPresenter extends BaseFragmentPresenter<NewsBookFragmentView> {
    private BookService mBookService;
    public NewsBookFragmentPresenter(NewsBookFragmentView view, BookService bookService) {
        super(view);
        this.mBookService = bookService;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getNewsBook();
    }

    private void getNewsBook(){
        mBookService.getNewsBookAsync().enqueue(new Callback<ArrayList<BookViewModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BookViewModel>> call, Response<ArrayList<BookViewModel>> response) {
                mView.setUpNewsBookRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<BookViewModel>> call, Throwable t) {

            }
        });
    }
}
