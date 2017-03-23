package xuxu.ebookproject.service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xuxu.ebookproject.model.BookDetailsViewModel;
import xuxu.ebookproject.model.BookViewModel;
import xuxu.ebookproject.model.PageResponseViewModel;

import static xuxu.ebookproject.config.Network.BASE_URL;


public interface BookService {
    @GET(BASE_URL + "api/book/NewBooks/?skip=0&take=12")
    Call<ArrayList<BookViewModel>> getNewsBookAsync();

    @GET(BASE_URL + "api/book/GetSingleTypeViewModel/{bookId}")
    Call<BookDetailsViewModel> getBookAsync(@Path("bookId") int bookId);

    @GET(BASE_URL + "api/book/RelatedBooksByCategory/{cateId}")
    Call<ArrayList<BookViewModel>> getRelatedBooksByCategoryAsync(@Path("cateId") int cateId);

    @GET(BASE_URL + "api/book/BooksByCategory")
    Call<PageResponseViewModel<BookViewModel>> getBooksByCategoryAsync(@Query("cateId") int cateId, @Query("page") int page, @Query("pageSize") int pageSize, @Query("sortBy") String sortBy, @Query("sortDir") String sortDir);
}
