package com.bottlerocket.androiddevtest.shoprocket.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bottlerocket.androiddevtest.shoprocket.R;
import com.bottlerocket.androiddevtest.shoprocket.framework.BackendRequestQueue;
import com.bottlerocket.androiddevtest.shoprocket.framework.Global;
import com.bottlerocket.androiddevtest.shoprocket.framework.OnItemClickListener;
import com.bottlerocket.androiddevtest.shoprocket.framework.models.Store;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    Global global = Global.getInstance();

    Context             mContext;
    OnItemClickListener mListener;
    String              sStoreAddress;
    Store               currentStore;

    public StoreAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        currentStore = global.getStoreList().get(position);

        holder.txtStoreName.setText(currentStore.getName());

        sStoreAddress = currentStore.getAddress() + ",\n" + currentStore.getCity() + ", " + currentStore.getState() + " - " + currentStore.getZipCode();
        holder.txtStoreAddress.setText(sStoreAddress);

        holder.imgStoreLogo.setImageUrl(currentStore.getUrlStoreLogo(), BackendRequestQueue.getInstance(mContext).getImageLoader());

        holder.attachListener(currentStore, mListener);



    }


    @Override
    public int getItemCount() {
        return global.getStoreList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView         txtStoreName;
        TextView         txtStoreAddress;
        NetworkImageView imgStoreLogo;

        public ViewHolder(View itemView) {

            super(itemView);

            this.txtStoreName = (TextView) itemView.findViewById(R.id.txt_store_name);
            this.txtStoreAddress = (TextView) itemView.findViewById(R.id.txt_store_address);
            this.imgStoreLogo = (NetworkImageView) itemView.findViewById(R.id.img_store_logo);
        }


        public void attachListener(final Store store, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClicked(store);
                }
            });

        }

    }

}
