package com.juniar.nostraassessment.contact;

import com.juniar.nostraassessment.utils.NetworkApi;
import com.juniar.nostraassessment.utils.NetworkUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EditContactPresenter {
    EditContactView callback;
    CompositeDisposable compositeDisposable;

    public EditContactPresenter(EditContactView callback) {
        this.callback = callback;
        compositeDisposable = new CompositeDisposable();
    }

    void insertContact(InsertContactRequest request) {
        compositeDisposable.add(NetworkUtils.createService(NetworkApi.class)
                .insertContact(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InsertContactResponse>() {
                    @Override
                    public void accept(InsertContactResponse insertContactResponse) throws Exception {
                        callback.onInsertedContact();
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
                        callback.onUpdatedContact();
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
                        callback.onDeletedContact();
                    }
                }));
    }

    void onDestroyPresenter() {
        compositeDisposable.clear();
    }
}
