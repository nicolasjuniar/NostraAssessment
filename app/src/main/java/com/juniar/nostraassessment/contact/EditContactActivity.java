package com.juniar.nostraassessment.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.juniar.nostraassessment.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function5;

public class EditContactActivity extends AppCompatActivity implements EditContactView {

    Toolbar toolbar;
    EditText etName, etAddress, etPhone, etEmail, etVersion;
    Button btnAdd, btnUpdate, btnDelete;
    ProgressBar pbLoading;
    EditContactPresenter presenter;
    Boolean add = false;
    ContactModel contact;

    public final static String CONTACT = "contact";
    public final static String ACTION = "action";
    public final static String ADD = "add";
    public final static String EDIT = "edit";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        toolbar = findViewById(R.id.toolbar);
        etName = findViewById(R.id.et_name);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        etVersion = findViewById(R.id.et_version);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        pbLoading = findViewById(R.id.pb_loading);
        presenter = new EditContactPresenter(this);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().getStringExtra(ACTION).equalsIgnoreCase(EDIT)) {
            contact = new Gson().fromJson(getIntent().getStringExtra(CONTACT), ContactModel.class);
            setFormContact(contact);
            btnAdd.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            etVersion.setVisibility(View.GONE);
            btnAdd.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            add = true;
        }

        Observable.combineLatest(
                RxTextView.textChanges(etName)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return charSequence.length() > 0;
                            }
                        }),
                RxTextView.textChanges(etAddress)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return charSequence.length() > 0;
                            }
                        }),
                RxTextView.textChanges(etPhone)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return Patterns.PHONE.matcher(charSequence.toString()).matches();
                            }
                        }),
                RxTextView.textChanges(etEmail)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return Patterns.EMAIL_ADDRESS.matcher(charSequence.toString()).matches();
                            }
                        }),
                RxTextView.textChanges(etVersion)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return charSequence.length() > 0 || add;
                            }
                        }),
                new Function5<Boolean, Boolean, Boolean, Boolean, Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Boolean name, Boolean address, Boolean phone, Boolean email, Boolean version) throws Exception {
                        if (phone || etPhone.getText().length() == 0) {
                            etPhone.setError(null);
                        } else {
                            etPhone.setError(getString(R.string.invalid_phone_text));
                        }

                        if (email || etEmail.getText().length() == 0) {
                            etEmail.setError(null);
                        } else {
                            etEmail.setError(getString(R.string.invalid_email_text));
                        }

                        return name && address && phone && email && version;
                    }
                }
        ).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                btnAdd.setEnabled(aBoolean);
                if (aBoolean) {
                    btnAdd.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    btnAdd.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                }

                btnUpdate.setEnabled(aBoolean);
                if (aBoolean) {
                    btnUpdate.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    btnUpdate.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLoading.setVisibility(View.VISIBLE);
                InsertContactRequest request = new InsertContactRequest(etName.getText().toString(), etAddress.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString());
                presenter.insertContact(request);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditContactActivity.this)
                        .setTitle(R.string.update_contact_title)
                        .setMessage(getString(R.string.update_contact_message, contact.getName()))
                        .setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pbLoading.setVisibility(View.VISIBLE);
                                UpdateContactRequest request = new UpdateContactRequest(etName.getText().toString(), etAddress.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString(), Integer.parseInt(etVersion.getText().toString()));
                                presenter.updateContact(contact.getId(), request);
                            }
                        }).setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditContactActivity.this)
                        .setTitle(R.string.delete_contact_title)
                        .setMessage(getString(R.string.delete_contact_message, contact.getName()))
                        .setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pbLoading.setVisibility(View.VISIBLE);
                                presenter.deleteContact(contact.getId());
                            }
                        }).setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    void setFormContact(ContactModel contact) {
        etName.setText(contact.getName());
        etAddress.setText(contact.getAddress());
        etPhone.setText(contact.getPhone());
        etEmail.setText(contact.getEmail());
        etVersion.setText(String.valueOf(contact.getVersion()));
    }

    @Override
    public void onDeletedContact() {
        pbLoading.setVisibility(View.GONE);
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void onInsertedContact() {
        pbLoading.setVisibility(View.GONE);
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void onUpdatedContact() {
        pbLoading.setVisibility(View.GONE);
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroyPresenter();
    }
}
