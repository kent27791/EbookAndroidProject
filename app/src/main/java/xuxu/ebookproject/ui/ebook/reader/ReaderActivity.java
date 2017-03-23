package xuxu.ebookproject.ui.ebook.reader;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import xuxu.ebookproject.R;
import xuxu.ebookproject.di.component.DaggerReaderActivityComponent;
import xuxu.ebookproject.di.module.reader.ReaderActivityModule;
import xuxu.ebookproject.model.epub.EPubSpine;
import xuxu.ebookproject.ui.adapter.SpineAdapter;
import xuxu.ebookproject.ui.application.EBookApplication;

public class ReaderActivity extends AppCompatActivity implements ReaderActivityView {

    @BindView(R.id.spine_view_pager)
    ViewPager mSpineViewPager;

    @Inject
    ReaderActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        ButterKnife.bind(this);
        setUpComponent();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    private void setUpComponent() {
        DaggerReaderActivityComponent.builder()
                .applicationComponent(((EBookApplication)getApplication()).getAppComponent())
                .readerActivityModule(new ReaderActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public String getFileEPub() {
        return getIntent().getStringExtra("FileEpub").replace(".epub", "/");
    }


    @Override
    public void setViewPager(ArrayList<EPubSpine> ePubSpines) {
        SpineAdapter spineAdapter = new SpineAdapter(getFileEPub(), ePubSpines, mPresenter.getReaderService());
        mSpineViewPager.setAdapter(spineAdapter);
    }


}
