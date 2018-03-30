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

    void onDestroyPresenter() {
        compositeDisposable.clear();
    }
}
