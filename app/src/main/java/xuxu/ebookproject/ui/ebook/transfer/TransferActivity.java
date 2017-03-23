package xuxu.ebookproject.ui.ebook.transfer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import xuxu.ebookproject.R;
import xuxu.ebookproject.gson.GmtDateTypeAdapter;
import xuxu.ebookproject.ui.ebook.account.AccountActivity;
import xuxu.ebookproject.ui.session.UserSessionManager;

import static xuxu.ebookproject.config.Network.BASE_URL;

public class TransferActivity extends AppCompatActivity {
    @BindView(R.id.transfer_web_view)
    WebView mTransferWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);

        UserSessionManager userSessionManager = new UserSessionManager(this, new GsonBuilder().registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create());
        HashMap<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + userSessionManager.getUserLoginSession().AccessToken);
        mTransferWebView.getSettings().setJavaScriptEnabled(true);
        mTransferWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.equals(BASE_URL + "#!/thanh-toan-thanh-cong.html")){
                    final Context context = view.getContext();
                    new AlertDialog.Builder(context)
                            .setTitle("Thông báo")
                            .setMessage("Bạn đã nạp thành công 100.000đ vào tài khoản. Chọn đồng ý để xem thông tin tài khoản.")
                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(context, AccountActivity.class);
                                    context.startActivity(intent);
                                    ((AppCompatActivity)context).finish();
                                }
                            }).show();
                    view.stopLoading();
                    return false;
                }else{
                    view.loadUrl(url);
                    return true;
                }
            }
        });
        mTransferWebView.setWebChromeClient(new WebChromeClient());
        mTransferWebView.loadUrl(BASE_URL + "/transfer/transferrequest/?type=1", header);
    }

}
