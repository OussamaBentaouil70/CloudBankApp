package com.example.tp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.Manifest;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
        import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NewMapActivity extends AppCompatActivity{
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;
    private SearchView searchView;
    private static final int CALL_PERMISSION_REQUEST_CODE = 1;
    String phoneNumber = "0623456789";
    private List<Agency> mAgencies;
    private ArrayList<Marker> markers = new ArrayList<>();
    ArrayList<GeoPoint> agencyLocations = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_new_map);

        map = (MapView) findViewById(R.id.NewMap);
        searchView = findViewById(R.id.searchView);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        requestPermissionsIfNecessary(new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        IMapController mapController = map.getController();
        mapController.setZoom(8);
        GeoPoint startPoint = new GeoPoint(31.6295, -7.9811);
        mapController.setCenter(startPoint);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text changes if needed
                return false;
            }
        });
        mAgencies = new ArrayList<>();

        mAgencies.add(new Agency("Agency 1", "123 Main St", "John Doe", "555-1234", 37.7749, -122.4194));
        mAgencies.add(new Agency("Agency 2", "456 Oak St", "Jane Smith", "555-5678", 37.7749, -122.4099));
        mAgencies.add(new Agency("Agency 3", "789 Elm St", "Bob Johnson", "555-9876", 37.7749, -122.3994));
        mAgencies.add(new Agency("Agency 4", "321 Maple Ave", "Sarah Wilson", "555-5432", 37.7749, -122.3899));
        mAgencies.add(new Agency("Agency 5", "555 Pine St", "David Brown", "555-2468", 37.7749, -122.3794));
        mAgencies.add(new Agency("Agency 6", "789 Cedar Rd", "Emily Thompson", "555-1357", 37.7749, -122.3699));
        mAgencies.add(new Agency("Agency 7", "987 Birch Ln", "Michael Miller", "555-8642", 37.7749, -122.3594));
        mAgencies.add(new Agency("Agency 8", "654 Spruce Dr", "Michelle Wilson", "555-9753", 37.7749, -122.3499));
        mAgencies.add(new Agency("Agency 9", "321 Willow Rd", "Daniel Johnson", "555-6481", 37.7749, -122.3394));
        mAgencies.add(new Agency("Agency 10", "555 Oak St", "Amy Brown", "555-8196", 37.7749, -122.3299));
        mAgencies.add(new Agency("Agency 11", "123 Elm Ave", "Mark Smith", "555-7531", 37.7749, -122.3194));
        mAgencies.add(new Agency("Agency 12", "456 Maple Ln", "Laura Johnson", "555-9624", 37.7749, -122.3099));
        mAgencies.add(new Agency("Agency 13", "789 Pine Dr", "Ryan Miller", "555-4269", 37.7749, -122.2994));
        mAgencies.add(new Agency("Agency 14", "987 Cedar Rd", "Sophia Wilson", "555-5378", 37.7749, -122.2899));
        mAgencies.add(new Agency("Agency 15", "654 Birch St", "Thomas Brown", "555-7946", 37.7749, -122.2794));
        mAgencies.add(new Agency("Agency 16", "321 Spruce Ave", "Olivia Smith", "555-4682", 37.7749, -122.2699));
        mAgencies.add(new Agency("Agency 17", "555 Willow Ln", "Jacob Johnson", "555-8271", 37.7749, -122.2594));
        mAgencies.add(new Agency("Agency 18", "123 Oak Dr", "Ava Miller", "555-6179", 37.7749, -122.2499));
        mAgencies.add(new Agency("Agency 19", "456 Elm Rd", "Ethan Wilson", "555-7924", 37.7749, -122.2394));
        mAgencies.add(new Agency("Agency 20", "154 Marrakech Morocco", "Bentaouil Oussama", "555-7924", 37.7749, -122.2394));
        mAgencies.add(new Agency("Agency 21", "987 Pine Ave", "Liam Brown", "555-9283", 37.7749, -122.2299));
        mAgencies.add(new Agency("Agency 22", "654 Cedar Ln", "Mia Smith", "555-4372", 37.7749, -122.2194));
        mAgencies.add(new Agency("Agency 23", "321 Birch Dr", "Noah Johnson", "555-7621", 37.7749, -122.2099));
        mAgencies.add(new Agency("Agency 24", "555 Spruce St", "Charlotte Miller", "555-2197", 37.7749, -122.1994));
        mAgencies.add(new Agency("Agency 25", "123 Willow Rd", "Logan Wilson", "555-7416", 37.7749, -122.1899));
        mAgencies.add(new Agency("Agency 26", "456 Oak St", "Amelia Brown", "555-9643", 37.7749, -122.1794));
        mAgencies.add(new Agency("Agency 27", "789 Elm Ave", "Henry Smith", "555-3261", 37.7749, -122.1699));
        mAgencies.add(new Agency("Agency 28", "987 Maple Ln", "Evelyn Johnson", "555-5719", 37.7749, -122.1594));
        mAgencies.add(new Agency("Agency 29", "654 Pine Dr", "Sebastian Miller", "555-8148", 37.7749, -122.1499));
        mAgencies.add(new Agency("Agency 30", "321 Cedar Rd", "Avery Wilson", "555-4977", 37.7749, -122.1394));


        for (Agency agency : mAgencies) {
            Marker marker = new Marker(map);
            marker.setPosition(new GeoPoint(agency.getLatitude(), agency.getLongitude()));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setTitle(agency.getName());
            map.getOverlays().add(marker);
        }



        agencyLocations.add(new GeoPoint(33.5731, -7.5898)); // Casablanca
        agencyLocations.add(new GeoPoint(34.0209, -6.8416)); // Rabat
        agencyLocations.add(new GeoPoint(31.6295, -7.9811)); // Marrakech
        agencyLocations.add(new GeoPoint(35.7818, -5.8136)); // Tangier
        agencyLocations.add(new GeoPoint(32.3332, -9.2372)); // Agadir
        agencyLocations.add(new GeoPoint(30.4210, -9.5833)); // Essaouira
        agencyLocations.add(new GeoPoint(34.0224, -5.0088)); // Fes
        agencyLocations.add(new GeoPoint(35.1781, -3.8767)); // Nador
        agencyLocations.add(new GeoPoint(29.6915, -9.7335)); // Ouarzazate
        agencyLocations.add(new GeoPoint(30.3919, -9.5469)); // Taghazout
        agencyLocations.add(new GeoPoint(31.5085, -9.7595)); // Taroudant
        agencyLocations.add(new GeoPoint(30.4754, -8.8762)); // Mirleft
        agencyLocations.add(new GeoPoint(33.2550, -8.5078)); // El Jadida
        agencyLocations.add(new GeoPoint(34.0541, -4.9950)); // Ifrane
        agencyLocations.add(new GeoPoint(35.1690, -5.2643)); // Tetouan
        agencyLocations.add(new GeoPoint(32.3184, -9.2372)); // Inezgane
        agencyLocations.add(new GeoPoint(30.9317, -6.8770)); // Zagora
        agencyLocations.add(new GeoPoint(31.1566, -4.2125)); // Midelt
        agencyLocations.add(new GeoPoint(34.6874, -1.9113)); // Al Hoceima
        agencyLocations.add(new GeoPoint(31.6377, -8.0131)); // Oukaïmeden
        agencyLocations.add(new GeoPoint(33.8333, -5.5833)); // Meknes
        agencyLocations.add(new GeoPoint(33.2333, -7.5833)); // Mohammedia
        agencyLocations.add(new GeoPoint(32.2979, -9.2372)); // Tiznit
        agencyLocations.add(new GeoPoint(29.6950, -9.7288)); // Aït Benhaddou
        agencyLocations.add(new GeoPoint(31.7216, -6.9104)); // Azemmour
        agencyLocations.add(new GeoPoint(35.2663, -3.9378)); // Al Hoceima
        agencyLocations.add(new GeoPoint(32.8949, -6.9094)); // Khouribga
        agencyLocations.add(new GeoPoint(33.2986, -6.9193)); // Settat
        agencyLocations.add(new GeoPoint(33.4617, -6.2113)); // Benguerir
        agencyLocations.add(new GeoPoint(32.5329, -6.6873)); // Berrechid



        ArrayList<OverlayItem> overlayItems = new ArrayList<>();


        int agencyCount = agencyLocations.size();
        int agencySize = mAgencies.size();
        for (int i = 0; i < agencyCount; i++) {
            GeoPoint location = agencyLocations.get(i);


                Marker marker = new Marker(map);
                marker.setPosition(location);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            if (i < agencySize) {
                Agency agency = mAgencies.get(i);
                String title = agency.getName();
                String snippet = "Address: " + agency.getAddress() + "\nResponsible: " + agency.getResponsible() + "\nPhone: " + agency.getPhone();
                marker.setTitle(title);
                marker.setSnippet(snippet);
            }


            map.getOverlays().add(marker);



        }


        map.invalidate();




        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return true;
            }
        });


        Button callButton = findViewById(R.id.button2);
        Button smsButton = findViewById(R.id.button3);
        Button emailButton = findViewById(R.id.button1);




        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(NewMapActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0618115141"));
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(NewMapActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
                }
            }
        });


        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipient = "recipient@example.com";
                String subject = "Email Subject";
                String body = "Email Body";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);

                try {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "No email app found.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    private void performSearch(String query) {

        map.getOverlays().clear();
        int agencyCount = agencyLocations.size();
        int agencySize = mAgencies.size();

        for (int i = 0; i < agencyCount; i++) {
            GeoPoint location = agencyLocations.get(i);

            Marker marker = new Marker(map);
            marker.setPosition(location);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            if (i < agencySize) {
                Agency agency = mAgencies.get(i);
                String title = agency.getName();
                String snippet = "Address: " + agency.getAddress() + "\nResponsible: " + agency.getResponsible() + "\nPhone: " + agency.getPhone();
                marker.setTitle(title);
                marker.setSnippet(snippet);

                if (query.isEmpty() || title.toLowerCase().contains(query.toLowerCase())) {

                    map.getOverlays().add(marker);
                }
            }
        }


        map.invalidate();
    }




    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }


    public void callCallCenter(View view) {
        String phoneNumber = "1234567890"; // Replace with the desired phone number
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void sendSMS(View view) {
        String phoneNumber = "1234567890"; // Replace with the desired phone number
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        startActivity(intent);
    }

    public void sendEmail(View view) {
        String recipientEmail = "example@example.com"; // Replace with the desired recipient email
        String subject = "Your subject";
        String message = "Your message body";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }


}