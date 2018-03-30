package com.juniar.nostraassessment.contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.juniar.nostraassessment.R;

import java.util.ArrayList;

import static com.juniar.nostraassessment.contact.ContactFragment.EDIT_CONTACT;
import static com.juniar.nostraassessment.contact.EditContactActivity.ACTION;
import static com.juniar.nostraassessment.contact.EditContactActivity.CONTACT;
import static com.juniar.nostraassessment.contact.EditContactActivity.EDIT;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    ArrayList<ContactModel> listContact;
    Fragment fragment;

    public View view;

    public ContactAdapter(ArrayList<ContactModel> listContact, Fragment fragment) {
        this.listContact = listContact;
        this.fragment=fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContactModel contact = listContact.get(position);
        holder.contact = contact;
        holder.tvName.setText(contact.getName());
        holder.tvPhone.setText(contact.getPhone());
        holder.tvEmail.setText(contact.getEmail());
        holder.tvAddress.setText(contact.getAddress());
        holder.tvVersion.setText(String.valueOf(contact.getVersion()));
        Glide.with(fragment)
                .load(contact.getPicture())
                .into(holder.ivContact);
    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPhone, tvEmail, tvAddress, tvVersion;
        ImageView ivContact;
        ContactModel contact;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            tvName = view.findViewById(R.id.tv_name);
            tvPhone = view.findViewById(R.id.tv_phone);
            tvEmail = view.findViewById(R.id.tv_email);
            tvAddress = view.findViewById(R.id.tv_address);
            tvVersion = view.findViewById(R.id.tv_version);
            ivContact = view.findViewById(R.id.iv_contact);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), EditContactActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(CONTACT, new Gson().toJson(contact));
                    intent.putExtra(ACTION, EDIT);
                    fragment.startActivityForResult(intent, EDIT_CONTACT);
                }
            });
        }
    }
}
