package com.bottlerocket.androiddevtest.shoprocket.framework.models;

import com.bottlerocket.androiddevtest.shoprocket.framework.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Store implements Serializable {

    String sStoreId;
    String sName;

    String urlStoreLogo;
    String sAddress;
    String sCity;
    String sState;
    String sZipCode;
    String sPhoneNumber;

    double latitude;
    double longitude;

    public Store(JSONObject jsonStore) {

        try {

            this.setStoreId(jsonStore.getString(AppConstants.JSON_KEY_STORE_ID));
            this.setName(jsonStore.getString(AppConstants.JSON_KEY_STORE_NAME));
            this.setUrlStoreLogo(jsonStore.getString(AppConstants.JSON_KEY_STORE_LOGO_URL));

            this.setAddress(jsonStore.getString(AppConstants.JSON_KEY_STORE_ADDRESS));
            this.setCity(jsonStore.getString(AppConstants.JSON_KEY_STORE_CITY));
            this.setState(jsonStore.getString(AppConstants.JSON_KEY_STORE_STATE));
            this.setZipCode(jsonStore.getString(AppConstants.JSON_KEY_STORE_ZIP_CODE));
            this.setPhoneNumber(jsonStore.getString(AppConstants.JSON_KEY_STORE_PHONE));

            this.setLatitude(Double.parseDouble(jsonStore.getString(AppConstants.JSON_KEY_STORE_LATITUDE)));
            this.setLongitude(Double.parseDouble(jsonStore.getString(AppConstants.JSON_KEY_STORE_LONGITUDE)));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String getStoreId() {
        return sStoreId;
    }

    public void setStoreId(String sStoreId) {
        this.sStoreId = sStoreId;
    }

    public String getName() {
        return sName;
    }

    public void setName(String sName) {
        this.sName = sName;
    }

    public String getUrlStoreLogo() {
        return urlStoreLogo;
    }

    public void setUrlStoreLogo(String urlStoreLogo) {
        this.urlStoreLogo = urlStoreLogo;
    }

    public String getAddress() {
        return sAddress;
    }

    public void setAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getCity() {
        return sCity;
    }

    public void setCity(String sCity) {
        this.sCity = sCity;
    }

    public String getState() {
        return sState;
    }

    public void setState(String sState) {
        this.sState = sState;
    }

    public String getZipCode() {
        return sZipCode;
    }

    public void setZipCode(String sZipCode) {
        this.sZipCode = sZipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lattitude) {
        this.latitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return sPhoneNumber;
    }

    public void setPhoneNumber(String sPhoneNumber) {
        this.sPhoneNumber = sPhoneNumber;
    }
}
