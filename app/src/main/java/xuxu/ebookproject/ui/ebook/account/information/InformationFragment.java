package xuxu.ebookproject.ui.ebook.account.information;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.AccountActivityComponent;
import xuxu.ebookproject.model.UserViewModel;
import xuxu.ebookproject.ui.base.fragment.BaseFragment;
import xuxu.ebookproject.ui.custom.CircleImageView;
import xuxu.ebookproject.ui.ebook.transfer.TransferActivity;

import static xuxu.ebookproject.config.Network.BASE_URL;

public class InformationFragment extends BaseFragment<InformationFragmentPresenter> implements InformationFragmentView {
    @BindView(R.id.thumbnail_user_circle_image_view)
    CircleImageView mThumbnailUserCircleImageView;

    @BindView(R.id.user_name_text_view)
    TextView mUserNameTextView;

    @BindView(R.id.total_transfer_text_view)
    TextView mTotalTransferTextView;

    @BindView(R.id.total_payment_text_view)
    TextView mTotalPaymentTextView;

    @BindView(R.id.books_buy_text_view)
    TextView mBooksBuyTextView;

    @BindView(R.id.transfer_button)
    Button mTransferButton;

    @BindView(R.id.change_password_button)
    Button mChangePasswordButton;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_information;
    }

    @Override
    protected void setUpComponent() {
        this.getComponent(AccountActivityComponent.class).inject(this);
    }

    @Override
    public void setUpEventButton() {
        mTransferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TransferActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void bindUserProfile(UserViewModel userProfile) {
        Glide.with(getContext()).load(BASE_URL + userProfile.UrlThumbnail + "w=128&h=128&mode=crop")
                .fitCenter()
                .placeholder(R.drawable.default_user_thumbnail)
                .into(mThumbnailUserCircleImageView);
        mUserNameTextView.setText(userProfile.UserName);
        mTotalTransferTextView.setText(getNumberFormat().format(userProfile.TotalTransfer));
        mTotalPaymentTextView.setText(getNumberFormat().format(userProfile.TotalPayment));
        mBooksBuyTextView.setText(String.valueOf(userProfile.Books));

    }


}
