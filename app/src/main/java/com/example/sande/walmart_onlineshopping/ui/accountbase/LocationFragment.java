package com.example.sande.walmart_onlineshopping.ui.accountbase;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sande.walmart_onlineshopping.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationFragment extends Fragment {

    double lat,lon;
    Location mLastLocation;
    TextView tv_location;
    Button btn_location;
    List<Address> addresses;
    Geocoder geocoder;
    FusedLocationProviderClient mFusedLocationProviderClient;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location,container,false);

        tv_location = view.findViewById(R.id.tv_location);
        btn_location = view.findViewById(R.id.btn_getLocation);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());


        getLocation();

        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_location.setText("Loading .... Plz wait");
                new MyTask().execute();

            }
        });



        return view;
    }

    private void getLocation() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();

            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mLastLocation = location;
                        lat = mLastLocation.getLatitude();
                        lon = mLastLocation.getLongitude();

                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
    }

    public class MyTask extends AsyncTask<Void, Void, List<Address>>
    {

        @Override
        protected List<Address> doInBackground(Void... voids) {

            try {
                geocoder.getFromLocation(lat,lon,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                addresses = geocoder.getFromLocation(lat, lon, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> aVoid) {
            super.onPostExecute(aVoid);
            Log.i("localoca", "onPostExecute: " + addresses.get(0).getCountryName());
            tv_location.setText(addresses.get(0).getFeatureName() + "\n" + addresses.get(0).getLocality() +"\n"+ addresses.get(0).getAdminArea()
                    + "\n" + addresses.get(0).getCountryName());
        }
    }
}
