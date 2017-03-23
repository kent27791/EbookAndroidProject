package xuxu.ebookproject.ui.adapter;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xuxu.ebookproject.R;
import xuxu.ebookproject.model.epub.EPubSpine;
import xuxu.ebookproject.service.ReaderService;
import xuxu.ebookproject.ui.ebook.account.AccountActivity;

import static xuxu.ebookproject.config.Network.BASE_URL;

public class SpineAdapter extends PagerAdapter {
    private ArrayList<EPubSpine> mEPubSpines;
    private ReaderService mReaderService;
    private String mFileEpub;
    public SpineAdapter(String fileEpub, ArrayList<EPubSpine> ePubSpines, ReaderService readerService){
        this.mEPubSpines = ePubSpines;
        this.mReaderService = readerService;
        this.mFileEpub = fileEpub;
    }
    @Override
    public int getCount() {
        return mEPubSpines.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_reader, container, false);
        viewGroup.setBackgroundColor(Color.WHITE);
        final EPubSpine ePubSpine = mEPubSpines.get(position);
        final WebView webView = (WebView) viewGroup.findViewById(R.id.reader_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://192.168.2.149/api/reader/render?url=/upload/epubs/thoi-tho-au-6/titlepage.xhtml");
        container.addView(viewGroup);
        mReaderService.getEpubXml(mFileEpub + ePubSpine.Href).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200){
                    try {
                        webView.loadDataWithBaseURL(BASE_URL + mFileEpub + ePubSpine.Href,
                                response.body().string().replace("<body", "<body style=\" margin: 0 auto; font-size: 15px; padding: 0 20px; \""),
                                "application/xhtml+xml", "utf-8", null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if(response.code() == 401){
                    new AlertDialog.Builder(container.getContext())
                            .setTitle("Authentication")
                            .setTitle("Vui lòng đăng nhập để được đọc trang kế tiếp...")
                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(container.getContext(), AccountActivity.class);
                                    container.getContext().startActivity(intent);
                                }
                            })
                            .setPositiveButton("Bỏ qua", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }else if(response.code() == 403){
                    new AlertDialog.Builder(container.getContext())
                            .setTitle("Book Buy")
                            .setTitle("Quyển sách bạn chưa mua, bạn phải mua để được đọc tiếp...")
                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return viewGroup;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
