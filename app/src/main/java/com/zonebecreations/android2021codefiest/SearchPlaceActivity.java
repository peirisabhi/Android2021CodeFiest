package com.zonebecreations.android2021codefiest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SearchPlaceActivity extends AppCompatActivity {

    String apiKey = "";
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    TextView placeSearchTv;

    AutocompleteSupportFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);

//        placeSearchTv = findViewById(R.id.textView3);

        autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);


        if(!Places.isInitialized()){
            // Initialize the SDK
            Places.initialize(getApplicationContext(), apiKey);

        }

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);




        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NotNull Place place) {
                // TODO: Get info about the selected place.
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                System.out.println("Place: ----------------- " + place.getName() + ", " + place.getId()  + " --- " + place.getLatLng());
                Toast.makeText(SearchPlaceActivity.this, place.getName() + " " + place.getLatLng(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onError(@NotNull Status status) {
                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
                System.out.println("An error occurred: " + status);
            }
        });


    }
}