package xuxu.ebookproject.ui.ebook.account.library;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.model.PageResponseViewModel;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentPresenter;

public class LibraryFragmentPresenter extends BaseFragmentPresenter<LibraryFragmentView> {
    private AccountService mAccountService;

    public LibraryFragmentPresenter(LibraryFragmentView view, AccountService accountService) {
        super(view);
        this.mAccountService = accountService;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpLibraryBookRecyclerView();
        getLibraryBook(1, 5, "Title", "asc");
    }

    private void getLibraryBook(int page, int pageSize, String sortBy, String sortDir){
        mAccountService.getLibraryBookAsync(page, pageSize, sortBy, sortDir).enqueue(new Callback<PageResponseViewModel<BookViewModel>>() {
            @Override
            public void onResponse(Call<PageResponseViewModel<BookViewModel>> call, Response<PageResponseViewModel<BookViewModel>> response) {
                mView.setUpAdapterLibraryBookRecyclerView(response.body().Entities);
            }

            @Override
            public void onFailure(Call<PageResponseViewModel<BookViewModel>> call, Throwable t) {

            }
        });
    }

}
