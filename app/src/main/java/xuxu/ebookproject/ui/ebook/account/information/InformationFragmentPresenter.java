package xuxu.ebookproject.ui.ebook.account.information;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.model.UserViewModel;
import xuxu.ebookproject.service.AccountService;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentPresenter;

public class InformationFragmentPresenter extends BaseFragmentPresenter<InformationFragmentView> {
    private AccountService mAccountService;
    public InformationFragmentPresenter(InformationFragmentView view, AccountService accountService) {
        super(view);
        this.mAccountService = accountService;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mView.setUpEventButton();
        getUserProfile();
    }

    private void getUserProfile(){
        mAccountService.getUserProfileAsync().enqueue(new Callback<UserViewModel>() {
            @Override
            public void onResponse(Call<UserViewModel> call, Response<UserViewModel> response) {
                mView.bindUserProfile(response.body());
            }

            @Override
            public void onFailure(Call<UserViewModel> call, Throwable t) {

            }
        });
    }
}
