package com.eltech.elhealth.Tools;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.Formatter;

import androidx.core.app.ActivityCompat;


import com.eltech.elhealth.R;

import java.io.IOException;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DataClass implements LocationListener {

    static Build.VERSION version;
    String city;
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "no mac address found";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    /** public static String getFirebaseToken(){
     String tkn = FirebaseInstanceId.getInstance().getToken();
     return tkn;
     }
     **/

    public static String getVendor(){
        return Build.MANUFACTURER;
    }

    public static String getOS(){
        return "ANDROID";
    }

    public static String getVersion(){
        return version.RELEASE;
    }

    public static String getModel(){
        return Build.MODEL;
    }

    public static String getBrand () { return Build.BRAND; }

    public static String getSecurityPatch(){return  version.SECURITY_PATCH; }
    //hostname 2
    public static String getIp(WifiInfo wifiInfo){
        int ip = wifiInfo.getIpAddress(); //3
        String ipAddress = Formatter.formatIpAddress(ip);
        return ipAddress;
    }

    public static String getHostName(Context context){
        String hostName;
        if(Settings.Secure.getString(context.getContentResolver(), "bluetooth_name") != null){
            hostName = Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
        }
        else{
            hostName = "default hostname";
        }
        Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
        return hostName;
    }

    public static String getSsid(WifiInfo wifiInfo){
//        wifiInfo.getSSID();
//        wifiInfo.getSSID();
        String ssid = wifiInfo.getSSID();
        return ssid;
    }

    //    public String locate(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
//        } else {
//            findLocation(activity);
//        }
//        return city;
//    }
    // method to request for location and calling findLocation method to get Location
    public String locate(final Activity activity) {
        // checking if Android version is above Android Marshmallow && checking if location is already enable or not                  //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // if this is true then custom UI can be shown and if yes button is clicked then default box will be showed    //
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle(activity.getString(R.string.location_request));
                    alertBuilder.setMessage(activity.getString(R.string.location_needed));
                    alertBuilder.setPositiveButton(activity.getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
                        }
                    });
                    alertBuilder.setNegativeButton(activity.getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
                }
            } else {
                findLocation(activity);
            }
        }
        return city;
    }

    public void findLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                if (locationManager != null) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0, this);
                }
            }
        }
        Location location = (Location) locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location == null){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0, this);
        }

        try {
            if(location == null){
                System.out.println("Data class: city is: " + city);
                city = null;
            }
            else {
                city = getLocation(context, location.getLatitude(), location.getLongitude());
                location.reset();
                System.out.println("Data class: city is: " + city);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(context, context.getString(R.string.location_not_found), Toast.LENGTH_SHORT).show();

        }
    }

    public  String getLocation (Context context, double lat, double lon){
        String cityName = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try{
            addresses = geocoder.getFromLocation(lat,lon, 10);
            if (addresses.size()>0){
                for (Address adr : addresses){
                    if (adr.getLocality() != null && adr.getLocality().length() > 0){
                        cityName = adr.getLocality() +", " + adr.getCountryName();
                        break;
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        city = cityName;
        return cityName;
    }

    public static String getTimeStamp (){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        String currentDate = simpleDateFormat.format(calendar.getTime());
        return currentDate;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public static void print (String string){
        System.out.println(string);
    }

}
