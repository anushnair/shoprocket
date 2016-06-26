package com.bottlerocket.androiddevtest.shoprocket.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bottlerocket.androiddevtest.shoprocket.R;
import com.bottlerocket.androiddevtest.shoprocket.framework.Global;
import com.bottlerocket.androiddevtest.shoprocket.framework.OnItemClickListener;
import com.bottlerocket.androiddevtest.shoprocket.framework.models.Store;
import com.bottlerocket.androiddevtest.shoprocket.ui.adapters.StoreAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnStoreItemClicked} interface
 * to handle interaction events.
 */
public class NearbyStoresFragment extends Fragment {

    final String TAG = getClass().getSimpleName();

    Global application = Global.getInstance();

    View                       mView;
    RecyclerView               mRecyclerViewStores;
    StoreAdapter               mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    private OnStoreItemClicked mListener;

    public NearbyStoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_nearby_stores, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mRecyclerViewStores = (RecyclerView) mView.findViewById(R.id.recycler_view_stores);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new StoreAdapter(getActivity(), new OnItemClickListener() {
            @Override
            public void onItemClicked(Store store) {
                onStoreSelected(store);
            }
        });

        mRecyclerViewStores.setLayoutManager(mLayoutManager);
        mRecyclerViewStores.setAdapter(mAdapter);

    }

    public void onStoreSelected(Store store) {
        if (mListener != null) {
            mListener.onStoreClicked(store);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStoreItemClicked) {
            mListener = (OnStoreItemClicked) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStoreItemClicked");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnStoreItemClicked {
        void onStoreClicked(final Store store);
    }
}
