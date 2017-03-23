package xuxu.ebookproject.service;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xuxu.ebookproject.model.epub.content.ContentEPubViewModel;
import xuxu.ebookproject.model.epub.toc.TocEPubViewModel;

import static xuxu.ebookproject.config.Network.BASE_URL;

public interface ReaderService {
    @GET(BASE_URL + "api/reader/render/?type=json")
    Call<ContentEPubViewModel> getContentOpfAsync(@Query("url") String url);

    @GET(BASE_URL + "api/reader/render")
    Call<TocEPubViewModel> getTocNxcAsync(@Query("url") String url,  @Query("type") String type);

    @GET(BASE_URL + "api/reader/render")
    Call<ResponseBody> getEpubXml(@Query("url") String url);
}
