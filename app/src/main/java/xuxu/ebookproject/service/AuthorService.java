package xuxu.ebookproject.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xuxu.ebookproject.model.AuthorViewModel;

import static xuxu.ebookproject.config.Network.BASE_URL;


public interface AuthorService {
    @GET(BASE_URL + "api/Author/HighlightAuthors?take=10")
    Call<List<AuthorViewModel>> getHighlightAuthorsAsync();
}
