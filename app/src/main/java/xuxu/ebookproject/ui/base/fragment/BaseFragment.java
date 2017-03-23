package xuxu.ebookproject.ui.base.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;

import javax.inject.Inject;

import butterknife.ButterKnife;
import xuxu.ebookproject.di.component.ApplicationComponent;
import xuxu.ebookproject.ui.application.EBookApplication;
import xuxu.ebookproject.ui.base.activity.BaseActivity;
import xuxu.ebookproject.ui.base.activity.HasComponent;

/**
 * Created by phanx on 26/11/2016.
 */

public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment implements BaseFragmentView {
    @Inject
    protected P mPresenter;

    protected ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpComponent();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((EBookApplication)getActivity().getApplication()).getAppComponent();
    }

    protected NumberFormat getNumberFormat(){
        return ((BaseActivity)getActivity()).mNumberFormat;
    }

    protected abstract int getFragmentLayout();
    protected abstract void setUpComponent();
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>)getActivity()).getComponent());
    }

}
