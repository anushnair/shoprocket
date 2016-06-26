package com.bottlerocket.androiddevtest.shoprocket.ui.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bottlerocket.androiddevtest.shoprocket.R;
import com.bottlerocket.androiddevtest.shoprocket.framework.models.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class StoreDetailFragment extends Fragment implements OnMapReadyCallback {

    final String TAG = getClass().getSimpleName();

    View               mView;
    TextView           txtStoreName;
    TextView           txtStoreAddress;
    ImageView          imgMakePhoneCall;
    Store              mStore;
    SupportMapFragment mapFragment;
    TextView txtStorePhoneNumber;


    public StoreDetailFragment() {
        // Required empty public constructor
    }

    public static StoreDetailFragment newInstance(final Store store) {

        StoreDetailFragment f    = new StoreDetailFragment();
        Bundle              args = new Bundle();
        args.putSerializable("STORE", store);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_store_detail, container, false);

        mStore = (Store) getArguments().getSerializable("STORE");

        Log.d(TAG, "onCreateView: " + mStore.getName());
        Log.d(TAG, "onCreateView: " + mStore.getAddress());

        txtStoreName = (TextView) mView.findViewById(R.id.txt_store_details_name);
        txtStoreAddress = (TextView) mView.findViewById(R.id.txt_store_details_address);
        imgMakePhoneCall = (ImageView) mView.findViewById(R.id.img_store_phone_call);
        txtStorePhoneNumber = (TextView) mView.findViewById(R.id.txt_store_details_phone);

        txtStoreName.setText(mStore.getName());
        txtStoreAddress.setText(mStore.getAddress() + ",\n" + mStore.getCity() + ",\n" + mStore.getState() + " - " + mStore.getZipCode());
        txtStorePhoneNumber.setText("Call "+mStore.getPhoneNumber());

        imgMakePhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePhoneCall(mStore);
            }
        });


        return mView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.maps);

        if(mapFragment==null){

            Log.d(TAG, "onActivityCreated: Map Fragment Did not load");
        }

        mapFragment.getMapAsync(this);

    }

    private void makePhoneCall(Store store) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            int iHasPhoneGroupPermissions = getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE);

            if (iHasPhoneGroupPermissions != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);
                return;
            }
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + store.getPhoneNumber()));
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        Log.d(TAG, "onMapReady: ");

        LatLng storeLocation = new LatLng(mStore.getLatitude(), mStore.getLongitude());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123);

                return;
            }
        }

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(storeLocation, 14));
        map.addMarker(new MarkerOptions()
                .title(mStore.getName())
                .position(storeLocation));


    }
}
