package org.maktab.scheduledlogs.remote;

import org.maktab.scheduledlogs.model.Logs;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RandomNameService {

    //flickr is this way exceptionally. please please do not do this for other api.
    @GET(".")
    Call<List<Logs>> name();

    //example: spotify api
    /*@GET("albums")
    Call<List<Album>> listAlbums();

    @GET("albums/{id}")
    Call<Album> getAlbum(@Path("id") String id);*/
}
