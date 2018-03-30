package com.juniar.nostraassessment.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.juniar.nostraassessment.R;

import java.util.ArrayList;

import static com.juniar.nostraassessment.contact.EditContactActivity.ACTION;
import static com.juniar.nostraassessment.contact.EditContactActivity.ADD;
import static com.juniar.nostraassessment.contact.EditContactActivity.CONTACT;
import static com.juniar.nostraassessment.contact.EditContactActivity.EDIT;

public class ContactFragment extends Fragment implements ContactView, ContactAdapter.ContactClick {

    ContactPresenter presenter;
    ContactAdapter adapter;
    ArrayList<ContactModel> listContact;
    RecyclerView rvContact;
    SwipeRefreshLayout swipeLayout;
    ProgressBar pbLoading;
    FloatingActionButton fabAdd;

    public final static int EDIT_CONTACT = 1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        rvContact = view.findViewById(R.id.rv_contact);
        swipeLayout = view.findViewById(R.id.swipe_layout);
        pbLoading = view.findViewById(R.id.pb_loading);
        fabAdd = view.findViewById(R.id.fab_add);
        presenter = new ContactPresenter(this);
        listContact = new ArrayList<>();
        adapter = new ContactAdapter(listContact, getActivity(), this);
        rvContact.setAdapter(adapter);
        rvContact.setLayoutManager(new LinearLayoutManager(getActivity()));

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditContactActivity.class);
                intent.putExtra(ACTION, ADD);
                startActivityForResult(intent, EDIT_CONTACT);
            }
        });

        presenter.getListContact();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getListContact();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_CONTACT) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.getListContact();
            }
        }
    }

    @Override
    public void setListContact(ArrayList<ContactModel> listContact) {
        swipeLayout.setRefreshing(false);
        pbLoading.setVisibility(View.GONE);
        this.listContact.clear();
        this.listContact.addAll(listContact);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroyPresenter();
    }

    @Override
    public void onContactClicked(ContactModel contact) {
        Intent intent = new Intent(getActivity(), EditContactActivity.class);
        intent.putExtra(CONTACT, new Gson().toJson(contact));
        intent.putExtra(ACTION, EDIT);
        startActivityForResult(intent, EDIT_CONTACT);
    }
}
