package com.juniar.nostraassessment.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juniar.nostraassessment.R;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    ArrayList<ContactModel> listContact;
    Context context;

    public View view;
    ContactClick callback;

    public ContactAdapter(ArrayList<ContactModel> listContact, Context context, ContactClick callback) {
        this.listContact = listContact;
        this.context = context;
        this.callback = callback;
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
        Glide.with(context)
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

    interface ContactClick {
        void onContactClicked(ContactModel contact);
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
                    callback.onContactClicked(contact);
                }
            });
        }
    }
}
