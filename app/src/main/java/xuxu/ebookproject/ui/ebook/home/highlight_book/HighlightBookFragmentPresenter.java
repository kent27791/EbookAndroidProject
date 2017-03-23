package xuxu.ebookproject.ui.ebook.home.highlight_book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.model.AuthorViewModel;
import xuxu.ebookproject.service.AuthorService;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentPresenter;

/**
 * Created by phanx on 26/11/2016.
 */
public class HighlightBookFragmentPresenter extends BaseFragmentPresenter<HighlightBookFragmentView> {
    protected AuthorService mAuthorService;
    public HighlightBookFragmentPresenter(HighlightBookFragmentView view, AuthorService authorService) {
        super(view);
        this.mAuthorService = authorService;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getHighlightAuthor();
    }

    private void getHighlightAuthor(){
        mAuthorService.getHighlightAuthorsAsync().enqueue(new Callback<List<AuthorViewModel>>() {
            @Override
            public void onResponse(Call<List<AuthorViewModel>> call, Response<List<AuthorViewModel>> response) {
                mView.setUpHighlightAuthorRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<AuthorViewModel>> call, Throwable t) {
                mView.getHighlightAuthorFailure();
            }
        });
    }
}
