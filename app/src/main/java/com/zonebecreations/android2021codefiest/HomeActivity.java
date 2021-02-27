package com.zonebecreations.android2021codefiest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.zonebecreations.android2021codefiest.model.Job;

import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    TextView name, email;
    Button logOut, orderBtn;
    private LatLng customerLocation;
    private LatLng dropLocation;

    String distance;
    String durationTime;
    double price;


    FirebaseFirestore db;

    CollectionReference customerCollectionReference;
    CollectionReference jobCollectionReference;

    String stringName;
    String Stringemail;
    String authId;
    String mobile;
    String customerDocId;
    String riderAppStatus;

    DocumentReference currentJobRef;

    GoogleMap currentGoogleMap;

    public void setCurrentGoogleMap(GoogleMap currentGoogleMap) {
        this.currentGoogleMap = currentGoogleMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        logOut = findViewById(R.id.button2);
        orderBtn = findViewById(R.id.button3);

        db = FirebaseFirestore.getInstance();

        if (intent != null) {
            name.setText(intent.getStringExtra("name") + "");
            email.setText(intent.getStringExtra("email") + "");
            Stringemail = intent.getStringExtra("email") + "";
            mobile = intent.getStringExtra("mobile") + "";
            customerDocId = intent.getStringExtra("customerDocId") + "";
        }

        customerCollectionReference = db.collection("customer");
        jobCollectionReference = db.collection("job");

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent1 = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(intent1);
//                finish();

                AuthUI.getInstance()
                        .signOut(HomeActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent1 = new Intent(HomeActivity.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                        });
            }
        });


        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(customerLocation == null || dropLocation == null){
                    Toast.makeText(HomeActivity.this, "Select Your Destination", Toast.LENGTH_SHORT).show();
                    return;
                }

                orderBtn.setEnabled(false);
                orderBtn.setText("Processing....");

                Job job = new Job();
                job.setCustomerName(name.getText().toString());
                job.setDurationString(durationTime);
                job.setStartLocationLat(customerLocation.latitude);
                job.setStartLocationLan(customerLocation.longitude);
                job.setEndLocationLat(dropLocation.latitude);
                job.setEndLocationLan(dropLocation.longitude);
                job.setEstimatedPrice(price);
                job.setJobCreatedAt(new Date(System.currentTimeMillis()));
                job.setEmail(Stringemail);
                job.setMobile(mobile);
                job.setStatusTime(new Date(System.currentTimeMillis()));
                job.setStatus("Job Requested");
                job.setCustomerCurrentLan(customerLocation.longitude);
                job.setCustomerCurrentLat(customerLocation.latitude);


                db.collection("job").add(job).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(HomeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        orderBtn.setEnabled(true);
                        orderBtn.setText("Cancle");
                        jobEngine(documentReference);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomeActivity.this, "Error :" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        orderBtn.setEnabled(true);
                        orderBtn.setText("Place JOB");
                    }
                });

            }
        });
    }


    public void setCustomerLocation(LatLng customerLocation) {
        this.customerLocation = customerLocation;
    }

    public void setDropLocation(LatLng dropLocation) {
        this.dropLocation = dropLocation;
    }

    public void setTripData(String distance, double price){
        this.distance = distance;
        this.price = price;
    }

//    public DocumentReference getSelecetedjob() {
//        return jobCollectionReference.document(jobDocId);
//    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }


    public void jobEngine(DocumentReference currentJobRef){
        this.currentJobRef = currentJobRef;

        currentJobRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Job job = value.toObject(Job.class);

                if(job.getStatus().equals("Job Requested")){
                    System.out.println("requested");
                }else if(job.getStatus().equals("Rider Accepted")){
                    Toast.makeText(HomeActivity.this, "Rider Accepted " + job.getDriverCurrentLan() + " | " + job.getDriverCurrentLat() , Toast.LENGTH_SHORT).show();

                    if(currentGoogleMap != null){
                        currentGoogleMap.addMarker(new MarkerOptions().position(new LatLng(job.getDriverCurrentLat(), job.getDriverCurrentLan())).title("Driver").icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
                    }

                }

                if(job.getStatus().equals("Picked Up")){
                    Toast.makeText(HomeActivity.this, "Trip Started" , Toast.LENGTH_SHORT).show();
                }
                if(job.getStatus().equals("Drop Offed")){
                    Toast.makeText(HomeActivity.this, "Trip Ended" , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}