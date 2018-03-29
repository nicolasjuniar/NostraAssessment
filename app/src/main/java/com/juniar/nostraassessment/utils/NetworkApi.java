package com.juniar.nostraassessment.utils;

import com.juniar.nostraassessment.contact.DeleteContactResponse;
import com.juniar.nostraassessment.contact.GetListContactResponse;
import com.juniar.nostraassessment.contact.InsertContactRequest;
import com.juniar.nostraassessment.contact.InsertContactResponse;
import com.juniar.nostraassessment.contact.UpdateContactRequest;
import com.juniar.nostraassessment.contact.UpdateContactResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkApi {
    @GET("api/v1/person")
    Observable<GetListContactResponse> getListContact();

    @POST("api/v1/person")
    Observable<InsertContactResponse> insertContact(@Body InsertContactRequest request);

    @PUT("api/v1/person/{secure_id}")
    Observable<UpdateContactResponse> updateContact(@Path("secure_id") String secureId,
                                                    @Body UpdateContactRequest request);

    @DELETE("api/v1/person/{secure_id}")
    Observable<DeleteContactResponse> deleteContact(@Path("secure_id") String secureId);
}
