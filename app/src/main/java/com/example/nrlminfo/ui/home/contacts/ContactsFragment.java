package com.example.nrlminfo.ui.home.contacts;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nrlminfo.databinding.FragmentContactsBinding;
import com.example.nrlminfo.networkclient.JsonApiClient;
import com.example.nrlminfo.repo.HomeRepository;
import com.example.nrlminfo.ui.BaseFragment;
import com.example.nrlminfo.ui.home.HomeViewModel;
import com.example.nrlminfo.ui.home.HomeViewModelFactory;
import com.example.nrlminfo.ui.home.feedback.FeedbackFragment;
import com.example.nrlminfo.utils.AppConstant;
import com.example.nrlminfo.utils.AppExecutor;
import com.example.nrlminfo.utils.AppUtils;
import com.example.nrlminfo.utils.CustomProgressDialog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class ContactsFragment extends BaseFragment<HomeViewModel, FragmentContactsBinding, HomeRepository, HomeViewModelFactory> {

    FusedLocationProviderClient locationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    private Location currentLocation;
    private int LOCATION_PERMISSION = 100;
    private static final int UPDATE_INTERVAL = 5000 * 12 * 10; // 5 seconds

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;


    @Override
    public Class<HomeViewModel> getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public FragmentContactsBinding getFragmentBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentContactsBinding.inflate(inflater, container, false);
    }

    @Override
    public HomeRepository getFragmentRepository() {
        return HomeRepository.getInstance(AppExecutor.getExecutorServiceInstance());
    }

    @Override
    public Context getCurrentContext() {
        return getContext();
    }

    @Override
    public HomeViewModelFactory getFactory() {
        return new HomeViewModelFactory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocationRequests();
    }

    @Override
    public void onFragmentReady() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
                if (locationAvailability.isLocationAvailable()) {
                    Log.i(TAG, "Location is available");

                } else {
                    Log.i(TAG, "Location is unavailable");
                }
            }

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.i(TAG, "Location result is available");
            }
        };
        startGettingLocation();


    }

    private void startGettingLocation() {
        CustomProgressDialog customProgressDialog=   CustomProgressDialog.newInstance(getContext());
        customProgressDialog.showProgress("Loading...",false);
        if (ActivityCompat.checkSelfPermission(getCurrentContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getCurrentContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, getActivity().getMainLooper());
            locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    currentLocation = location;
                    AppUtils.getInstance().showLog("currentLocation" + currentLocation.getLatitude() + "" + currentLocation.getLongitude(), ContactsFragment.class);
                    binding.location.setText("TAMIL NADU");
                    binding.address.setText("DINDIGUL");

                    try {
                        Gson gson = new Gson();
                        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .connectTimeout(100, TimeUnit.SECONDS)
                                .readTimeout(100, TimeUnit.SECONDS)
                                .addInterceptor(httpLoggingInterceptor).build();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(AppConstant.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .client(okHttpClient)
                                .build();
                        JsonApiClient jsonApiClient = retrofit.create(JsonApiClient.class);

                        ContactsRequestBean contactsRequestBean = new ContactsRequestBean();
                        contactsRequestBean.setState_name("Tamil Nadu");
                        contactsRequestBean.setDistrict_name("Dindigul");

                        Call<List<Example>> exampleCall = jsonApiClient.test();

                 /*       exampleCall.enqueue(new Callback<List<Example>>() {
                            @Override
                            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                                if (response.isSuccessful()){
                                    AppUtils.getInstance().showLog("ContactsResponse"+response.body().toString(),ContactsFragment.class);
                                  String responseTest="";
                                   for (Example example:response.body()){
                                       responseTest+=example.getTitle()+"\n";
                                       responseTest+=example.getUserId()+"\n";
                                       responseTest+=example.getBody()+"\n";
                                       responseTest+=example.getId()+"\n";
                                   }
                                    binding.location.setText(responseTest);
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Example>> call, Throwable t) {
                                AppUtils.getInstance().showLog("ContactsResponseExp"+t.toString(),ContactsFragment.class);
                                binding.location.setText(t.toString());
                            }
                        });*/

                        Call<ContactsResponseBean> jsonApiClientConTacts = jsonApiClient.getConTacts(contactsRequestBean);

                        jsonApiClientConTacts.enqueue(new Callback<ContactsResponseBean>() {
                            @Override
                            public void onResponse(Call<ContactsResponseBean> call, Response<ContactsResponseBean> response) {
                                customProgressDialog.hideProgress();

                                if (response.isSuccessful()) {
                                    AppUtils.getInstance().showLog("ContactsResponse" + response.body().toString(), ContactsFragment.class);

                                    ContactsResponseBean contactsResponseBean = response.body();
                                    int contactsSize = contactsResponseBean.getData().size();
                                    AppUtils.getInstance().showLog("ContactsListSize" + contactsSize, ContactsFragment.class);
                                    List<ContactsResponseBean.ContactsInfo> contactsInfoList = contactsResponseBean.getData();
                                    if (contactsInfoList != null && contactsSize > 0) {
                                        ContactsAdapter contactsAdapter = new ContactsAdapter(getContext(), contactsInfoList);
                                        recyclerView = binding.contactsRV;
                                        mLayoutManager = new LinearLayoutManager(ContactsFragment.this.getActivity());
                                        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
                                        recyclerView.setLayoutManager(mLayoutManager);
                                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                                        recyclerView.setAdapter(contactsAdapter);

                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ContactsResponseBean> call, Throwable t) {
                                AppUtils.getInstance().showLog("ContactsResponseExp" + t.toString(), ContactsFragment.class);
                                customProgressDialog.hideProgress();
                                binding.erroCV.setVisibility(View.VISIBLE);
                                binding.erroTV.setText(t.getMessage().toUpperCase());
                            }
                        });

                        final Geocoder geocoder = new Geocoder(getContext());
                        List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                        if (addresses != null && !addresses.isEmpty()) {
                            Address address = addresses.get(0);
                            // Use the address as needed
                            AppUtils.getInstance().showLog("address" + address, ContactsFragment.class);
                            String message = address.getCountryName()
                                    + address.getAdminArea() + address.getLocality();
                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();

                            binding.location.setText("Location=" + currentLocation.getLatitude() + " , " + currentLocation.getLongitude());
                            binding.address.setText("Address Line=" + address.getAddressLine(0));


                        } else {
                            // Display appropriate message when Geocoder services are not available
                        }
                    } catch (IOException e) {
                        // handle exception
                    }

                }
            });

            locationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i(TAG, "Exception while getting the location: " + e.getMessage());
                }
            });


        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(getContext(), "Permission needed", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_PERMISSION);
            }
        }
    }

    private void stopLocationRequests() {
        locationProviderClient.removeLocationUpdates(locationCallback);
    }
}
