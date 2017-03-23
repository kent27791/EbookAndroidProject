package xuxu.ebookproject.ui.ebook.account.information;

import xuxu.ebookproject.model.UserViewModel;
import xuxu.ebookproject.ui.base.fragment.BaseFragmentView;

/**
 * Created by phanx on 19/12/2016.
 */
public interface InformationFragmentView extends BaseFragmentView {
    void bindUserProfile(UserViewModel userProfile);

    void setUpEventButton();
}
