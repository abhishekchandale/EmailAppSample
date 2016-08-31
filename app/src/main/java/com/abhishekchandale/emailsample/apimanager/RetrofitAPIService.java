package com.abhishekchandale.emailsample.apimanager;

import com.abhishekchandale.emailsample.model.MessageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Barun on 12-02-2016.
 */
public interface RetrofitAPIService {


    @GET("/api/message/")
    public Call<List<MessageModel>> getMessages();

    @DELETE("/api/message/{id}")
    public Call<Void> deleteMessage(@Path("id") long id);
}
