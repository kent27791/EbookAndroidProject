package xuxu.ebookproject.ui.base.fragment;

/**
 * Created by phanx on 26/11/2016.
 */
public class BaseFragmentPresenter<V extends BaseFragmentView> {
    protected V mView;

    public BaseFragmentPresenter(V view){
        this.mView = view;
    }

    protected void onStart() {

    }

    protected void onStop(){

    }
}

