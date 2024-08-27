package com.mohdgauri.talentbobweather;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mohdgauri.talentbobweather.ApiClient.Api_Call;
import com.mohdgauri.talentbobweather.ApiClient.RxApiClient;
import com.mohdgauri.talentbobweather.DataModels.PlaceWeather.ResponsePlaceWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivityTAG";
    private final int MY_PERMISSIONS_REQUEST = 111;
    private FusedLocationProviderClient fusedLocationClient;
    private final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();

    private TextView tvCityName, tvDescription, tvTemperature;
    private CardView progressBarCV;
    private ImageView ivRefresh, ivCloud, ivLocation, ivAdd;

    private String placeStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        ivAdd = findViewById(R.id.ivAdd);
        ivLocation = findViewById(R.id.ivLocation);
        ivCloud = findViewById(R.id.ivCloud);
        ivRefresh = findViewById(R.id.ivRefresh);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvDescription = findViewById(R.id.tvDescription);
        progressBarCV = findViewById(R.id.progressBarCV);
        tvCityName = findViewById(R.id.tvCityName);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if(isPermissionAllowed()) {
            requestCurrentLocation();
        }else{
            showCustomDialog(MainActivity.this);
        }

        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarCV.setVisibility(View.VISIBLE);
                if(placeStr.isEmpty()){
                    requestCurrentLocation();
                }else{
                    getWeatherDataWithPlace(placeStr);
                }

            }
        });
        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(MainActivity.this);
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(MainActivity.this);
            }
        });

    }


    private void setData(ResponsePlaceWeather model) {
        Log.d(TAG, "Temp: "+model.getCurrent().getCondition().getIcon());
        tvCityName.setText(model.getLocation().getName());
        tvDescription.setText(model.getCurrent().getCondition().getText());
        tvTemperature.setText(model.getCurrent().getTempC()+"°");
        Picasso.get()
                .load("https://"+model.getCurrent().getCondition().getIcon())
                .placeholder(R.drawable.w01d)
                .into(ivCloud);
        progressBarCV.setVisibility(View.GONE);
    }


    private boolean isPermissionAllowed() {
        ArrayList<String> arrPerm = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            arrPerm.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        return arrPerm.isEmpty();
    }

    private void askLocationPermission() {
        ArrayList<String> arrPerm = new ArrayList<>();
        arrPerm.add(Manifest.permission.ACCESS_FINE_LOCATION);
        String[] permissions = new String[arrPerm.size()];
        permissions = arrPerm.toArray(permissions);
        ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS_REQUEST);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        String permission = permissions[i];
                        if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permission)) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                // you now have permission
                                requestCurrentLocation();
                            }else{
                                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else{

                }
                break;
            }
        }
    }

    public void showCustomDialog(Context context) {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_alert_dialog, null);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        // Create the dialog
        AlertDialog alertDialog = builder.create();

        // Reference to UI elements in the custom layout
        EditText etPlaceName = view.findViewById(R.id.etPlaceName);
        Button btnPositive = view.findViewById(R.id.btnPositive);
        Button btnLocationPermission = view.findViewById(R.id.btnLocationPermission);


        // Set button click listeners
        btnPositive.setOnClickListener(v -> {
            // Handle positive button click
            placeStr = etPlaceName.getText().toString();

            if(placeStr.length() <= 2){
                Toast.makeText(context, "place name should be more than 2 character", Toast.LENGTH_SHORT).show();
            }else{
                getWeatherDataWithPlace(placeStr);
                alertDialog.dismiss();
            }


        });
        btnLocationPermission.setOnClickListener(v -> {
            // Handle negative button click
            if(isPermissionAllowed()){

                requestCurrentLocation();
            }else{
                askLocationPermission();
            }
            alertDialog.dismiss();
        });

        // Show the dialog
        alertDialog.show();
    }

    @SuppressLint("CheckResult")
    private void getWeatherDataWithPlace(String place) {
        Api_Call apiInterface = RxApiClient.getClient().create(Api_Call.class);

        // Handle the response
        // For example: update UI, store data, etc.
        apiInterface.GetPlaceWeather(
                        "4ec713a8368f4df0b33133054242608", // API key
                        place, // Location (latitude,longitude)
                        "no" // Air Quality Index (AQI)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, throwable -> {
                    // Handle error
                    throwable.printStackTrace();
                    progressBarCV.setVisibility(View.GONE);
                    Log.d("ResultAPI", "error: "+throwable.getMessage());
                });

    }

    @SuppressLint("CheckResult")
    private void getWeatherDataWithLocation(String location) {
        Api_Call apiInterface = RxApiClient.getClient().create(Api_Call.class);
        // Handle the response
        // For example: update UI, store data, etc.
        apiInterface.GetLocationWeather(
                        "4ec713a8368f4df0b33133054242608", // API key
                        location, // Location (latitude,longitude)
                        "no" // Air Quality Index (AQI)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, throwable -> {
                    // Handle error
                    throwable.printStackTrace();
                    Log.d("ResultAPI", "error: "+throwable.getMessage());
                    progressBarCV.setVisibility(View.GONE);
                });
    }


    private void requestCurrentLocation() {
        progressBarCV.setVisibility(View.VISIBLE);
        Log.d(TAG, "requestCurrentLocation()");
        // Request permission
        if (ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Main code
            Task<Location> currentLocationTask = fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY
                    , cancellationTokenSource.getToken());
            currentLocationTask.addOnCompleteListener((new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    String result = "";
                    if (task.isSuccessful()) {
                        Location location = task.getResult();
                        result = location.getLatitude() + "," + location.getLongitude();
                        getWeatherDataWithLocation(result);
                    } else {
                        // Task failed with an exception
                        Exception exception = task.getException();
                        result = "Exception thrown: " + exception;
                    }

                    Log.d(TAG, "getCurrentLocation() result: " + result);
                }
            }));
        } else {
            askLocationPermission();
        }
    }

}

