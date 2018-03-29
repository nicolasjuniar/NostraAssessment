package com.juniar.nostraassessment.utils;

import com.juniar.nostraassessment.contact.GetListContactResponse;
import com.juniar.nostraassessment.contact.InsertContactRequest;
import com.juniar.nostraassessment.contact.InsertContactResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkApi {
    @GET("api/v1/person")
    Observable<GetListContactResponse> getListContact();

    @POST("api/v1/person")
    Observable<InsertContactResponse> insertContact(@Body InsertContactRequest request);


}
