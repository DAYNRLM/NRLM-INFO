package com.example.nrlminfo.ui.home.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nrlminfo.databinding.ContactItemBinding;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private Context context;
    private List<ContactsResponseBean.ContactsInfo> contactsInfoList;

    public ContactsAdapter(Context context,List<ContactsResponseBean.ContactsInfo> contactsInfoList){
        this.contactsInfoList=contactsInfoList;
        this.context=context;
    }


    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactItemBinding rootView = ContactItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ContactsViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        String state=contactsInfoList.get(position).getState_name();
        String distt=contactsInfoList.get(position).getDistrict_name();
        String block=contactsInfoList.get(position).getBlock_name();
        holder.contactItemBinding.stateTV.setText(state);

        if (distt.equalsIgnoreCase(""))
            holder.contactItemBinding.districtTV.setVisibility(View.GONE);
        else holder.contactItemBinding.districtTV.setText(distt);

        if (block.equalsIgnoreCase(""))
            holder.contactItemBinding.blockTV.setVisibility(View.GONE);
        else  holder.contactItemBinding.blockTV.setText(block);

        holder.contactItemBinding.nameTV.setText(contactsInfoList.get(position).getEmployee_name());
        holder.contactItemBinding.designationTV.setText(contactsInfoList.get(position).getDesignation());
        holder.contactItemBinding.mobileTV.setText(contactsInfoList.get(position).getMobile());
        holder.contactItemBinding.emailTV.setText(contactsInfoList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contactsInfoList.size();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder{

        ContactItemBinding contactItemBinding;

        public ContactsViewHolder(@NonNull ContactItemBinding itemView) {
            super(itemView.getRoot());
            contactItemBinding=itemView;
        }
    }
}
