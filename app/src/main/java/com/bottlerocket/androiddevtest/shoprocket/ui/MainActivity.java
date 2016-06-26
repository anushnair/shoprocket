package com.bottlerocket.androiddevtest.shoprocket.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bottlerocket.androiddevtest.shoprocket.R;
import com.bottlerocket.androiddevtest.shoprocket.framework.models.Store;
import com.bottlerocket.androiddevtest.shoprocket.ui.fragments.NearbyStoresFragment;
import com.bottlerocket.androiddevtest.shoprocket.ui.fragments.StoreDetailFragment;

public class MainActivity extends AppCompatActivity implements NearbyStoresFragment.OnStoreItemClicked {

    final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment            storeListFragment = new NearbyStoresFragment();
        FragmentTransaction transaction       = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, storeListFragment, "STORE_LIST");
        transaction.addToBackStack("STORE_LIST");
        transaction.commit();


    }

    @Override
    public void onStoreClicked(Store store) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new StoreDetailFragment().newInstance(store), "STORE_DETAIL");
        fragmentTransaction.addToBackStack("STORE_DETAIL");
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

}
