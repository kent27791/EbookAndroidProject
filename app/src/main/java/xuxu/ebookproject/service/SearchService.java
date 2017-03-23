package xuxu.ebookproject.service;


import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import xuxu.ebookproject.model.SearchViewModel;

import static xuxu.ebookproject.config.Network.BASE_URL;

public interface SearchService {

    @GET(BASE_URL + "api/book/search/")
    Observable<ArrayList<SearchViewModel>> searchAsync(@Query("term") String term);
}
