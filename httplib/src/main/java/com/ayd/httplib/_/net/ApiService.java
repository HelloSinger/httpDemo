package com.ayd.httplib._.net;



import com.ayd.httplib._.model.BookModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 测试数据接口
     *
     * @param q
     * @param tag
     * @param start
     * @param end
     * @return
     */
    @GET("book/search")
    Observable<BookModel> getBooks(@Query("q") String q,
                                   @Query("tag") String tag,
                                   @Query("start") String start,
                                   @Query("end") String end);





}
