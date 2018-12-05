package net.syynclab.acaciahealth.retrofit;

import net.syynclab.acaciahealth.model.HealthProvider;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;

public interface IAPI {

    @GET("photos")
    Observable<List<HealthProvider>> loadData();
}
