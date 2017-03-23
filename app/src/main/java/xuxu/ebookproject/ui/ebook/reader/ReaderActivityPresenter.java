package xuxu.ebookproject.ui.ebook.reader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.model.epub.EPubSpine;
import xuxu.ebookproject.model.epub.content.ContentEPubViewModel;
import xuxu.ebookproject.model.epub.content.ItemManifestViewModel;
import xuxu.ebookproject.model.epub.content.ItemRefViewModel;
import xuxu.ebookproject.service.ReaderService;

public class ReaderActivityPresenter {
    protected ReaderActivityView mView;
    protected ReaderService mReaderService;
    public ReaderActivityPresenter(ReaderActivityView view, ReaderService readerService) {
        this.mView = view;
        this.mReaderService = readerService;
    }
    public void onStart() {
        getContentOpf();
        //getTocNxc();
    }
    public ReaderService getReaderService(){
        return mReaderService;
    }

    private void getContentOpf() {
        mReaderService.getContentOpfAsync(mView.getFileEPub() + "content.opf").enqueue(new Callback<ContentEPubViewModel>() {
            @Override
            public void onResponse(Call<ContentEPubViewModel> call, Response<ContentEPubViewModel> response) {
                ArrayList<EPubSpine> ePubSpines = new ArrayList<>();
                for (ItemRefViewModel itemRef : response.body().Package.Spine.ItemsRef) {
                    for (ItemManifestViewModel itemManifest : response.body().Package.Manifest.Items) {
                        if(itemRef.IdRef.equals(itemManifest.Id)){
                            ePubSpines.add(new EPubSpine(itemManifest.Id, itemManifest.Href, itemManifest.MediaType));
                        }
                    }
                }
                mView.setViewPager(ePubSpines);
            }

            @Override
            public void onFailure(Call<ContentEPubViewModel> call, Throwable t) {

            }
        });

    }
    private void getTocNxc(){
        /*mReaderService.getTocNxcAsync().enqueue(new Callback<TocEPubViewModel>() {
            @Override
            public void onResponse(Call<TocEPubViewModel> call, Response<TocEPubViewModel> response) {

            }

            @Override
            public void onFailure(Call<TocEPubViewModel> call, Throwable t) {

            }
        });*/
    }
}
