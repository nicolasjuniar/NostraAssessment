package com.juniar.nostraassessment.contact;

import com.juniar.nostraassessment.utils.NetworkApi;
import com.juniar.nostraassessment.utils.NetworkUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ContactPresenter {
    ContactView callback;
    CompositeDisposable compositeDisposable;

    public ContactPresenter(ContactView callback) {
        this.callback = callback;
        compositeDisposable = new CompositeDisposable();
    }

    void getListContact() {
        compositeDisposable.add(NetworkUtils.createService(NetworkApi.class)
                .getListContact()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetListContactResponse>() {
                    @Override
                    public void accept(GetListContactResponse getListContactResponse) throws Exception {
                        callback.setListContact(getListContactResponse.getResult());
                    }
                }));
    }

    void insertContact(InsertContactRequest request) {
        compositeDisposable.add(NetworkUtils.createService(NetworkApi.class)
                .insertContact(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InsertContactResponse>() {
                    @Override
                    public void accept(InsertContactResponse insertContactResponse) throws Exception {

                    }
                }));
    }

    void updateContact(String secureId, UpdateContactRequest request) {
        compositeDisposable.add(NetworkUtils.createService(NetworkApi.class)
                .updateContact(secureId, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateContactResponse>() {
                    @Override
                    public void accept(UpdateContactResponse updateContactResponse) throws Exception {

                    }
                }));
    }

    void deleteContact(String secureId) {
        compositeDisposable.add(NetworkUtils.createService(NetworkApi.class)
                .deleteContact(secureId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DeleteContactResponse>() {
                    @Override
                    public void accept(DeleteContactResponse deleteContactResponse) throws Exception {

                    }
                }));
    }

    void onDestroyPresenter() {
        compositeDisposable.clear();
    }
}
