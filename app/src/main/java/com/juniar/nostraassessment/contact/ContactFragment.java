package com.juniar.nostraassessment.contact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juniar.nostraassessment.R;

import java.util.ArrayList;

public class ContactFragment extends Fragment implements ContactView {

    ContactPresenter presenter;
    ContactAdapter adapter;
    ArrayList<ContactModel> listContact;
    RecyclerView rvContact;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        rvContact = view.findViewById(R.id.rv_contact);
        presenter = new ContactPresenter(this);
        listContact = new ArrayList<>();
        adapter = new ContactAdapter(listContact, getActivity());
        rvContact.setAdapter(adapter);
        rvContact.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.getListContact();

        return view;
    }

    @Override
    public void setListContact(ArrayList<ContactModel> listContact) {
        this.listContact.clear();
        this.listContact.addAll(listContact);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroyPresenter();
    }
}
