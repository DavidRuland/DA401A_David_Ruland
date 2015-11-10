package se.mah.ac9511.assignment4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements LocationListener{
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LocationManager locationManager;
    private LocationRequest mLocationRequest;
    private LatLng landalettgatan=new LatLng(55.604322,13.089204);
    private LatLng gigGatan=new LatLng(55.601524,13.091877);
    private LatLng  kupegatan=new LatLng(55.602878,13.089719);
    private LatLng  lGatan=new LatLng(55.604366,13.091218);
    LatLng latLng;
    Location myLocation,location2,location3,location4;
    MediaPlayer mp;

    Criteria criteria;
    Circle circle,circle2,circle3,circle4;
    String provider;
    float[] distance=new float[4];

    boolean answear= Boolean.parseBoolean(null);
    double latitude;
    double longitude;
    boolean close=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();







    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void circle()
    {
        double radiusInMeters =25;
        int strokeColor = 0xffff0000; //red outline
        int shadeColor = 0x44ff0000; //opaque red fill

        circle = mMap.addCircle (new CircleOptions()
                .center(new LatLng(landalettgatan.latitude,landalettgatan.longitude))
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(1));
        circle2 = mMap.addCircle (new CircleOptions()
                .center(new LatLng(lGatan.latitude, lGatan.longitude))
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(1));
        circle3 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(kupegatan.latitude, kupegatan.longitude))
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(1));
        circle4 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(gigGatan.latitude, gigGatan.longitude))
                .radius(radiusInMeters)
                .fillColor(shadeColor)
                .strokeColor(strokeColor)
                .strokeWidth(1));


    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                addLines();
                myAnswearIsRight();
                myAnswearIsWrong();


            }
        }
    }



    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void myAnswearIsRight() {


        if(distance[0]<=circle.getRadius())
        {

            Toast.makeText(this, "Landålettgatan 20 reached", Toast.LENGTH_LONG).show();
            Question1();





        }
        if(distance[1]<=circle2.getRadius())
        {

            if (answear) {
                Toast.makeText(this,"Landågatan reached",Toast.LENGTH_LONG).show();
                Question2();

            }


        }
        if(distance[2]<=circle3.getRadius())
        {

            if (answear) {
                Toast.makeText(this,"Kupegatan reached",Toast.LENGTH_LONG).show();
                Question3();

            }

        }
        if(distance[3]<=circle4.getRadius()) {

            if (answear) {
                Toast.makeText(this,"Giggatan reached",Toast.LENGTH_LONG).show();
                Question4();

            }

        }

    }





    private void myAnswearIsWrong()
    {




        if (distance[0] <= circle.getRadius() || distance[1] <= circle2.getRadius() || distance[2] <= circle3.getRadius() || distance[3] <= circle4.getRadius() && answear == false)
        {
            Question1();

        }
    }












    private void setUpMap() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        setMyLocation();
        myMarker();
        AddMarkerAt1();
        AddMarkerAt2();
        AddMarkerAt3();
        AddMarkerAt4();
        Camera();
        circle();
        Log.d("onLocChanged", "Lng=" + longitude + ", Lat= " + latitude);




    }
    private void setMyLocation()
    {
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        myLocation = locationManager.getLastKnownLocation(provider);
        latitude = myLocation.getLatitude();
        longitude = myLocation.getLongitude();
        location2=new Location("landågatan");
        location2.setLatitude(55.604366);
        location2.setLongitude(13.091218);
        location3=new Location("kupegatan");
        location3.setLatitude(55.602878);
        location3.setLongitude(13.089719);
        location4=new Location("giggatan");
        location4.setLatitude(55.601524);
        location4.setLongitude(13.091877);
        distance[0]=myLocation.distanceTo(myLocation);
        distance[1]=myLocation.distanceTo(location2);
        distance[2]= myLocation.distanceTo(location3);
        distance[3]= myLocation.distanceTo(location4);
        latLng = new LatLng(latitude,longitude);


    }
    private void Camera()
    {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude,longitude))      // Sets the center of the map to location user
                .zoom(10)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    private void addLines() {

        mMap.addPolyline((new PolylineOptions())
                .add(landalettgatan, lGatan, kupegatan, gigGatan, landalettgatan).width(5).color(Color.BLUE)
                .geodesic(true));
        // move camera to zoom on map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
    }
   private void myMarker()
   {

       MarkerOptions options = new MarkerOptions().flat(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location_black_24dp))
            .position(latLng)
               .title("Latitude: " + latitude + "Longitude " + longitude);
       mMap.addMarker(options);
   }
    private void AddMarkerAt1 ()
    {
        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_place_black_24dp))
                .position(lGatan)
                .title("landågatan!");
        mMap.addMarker(options);

    }
    private void Question1()
    {

        mp= MediaPlayer.create(getApplicationContext(), R.raw.l2);
        mp.start();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Vad är Sveriges huvudstad?")
                .setCancelable(false)
                .setPositiveButton("Stockholm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = true;
                        Log.i("Question1", "Resultat: " + answear);

                        if (answear) {
                            dialog.cancel();
                            mp.release();



                        }


                    }
                })
                .setNeutralButton("Köpenhamn", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = false;
                        Log.i("Question1", "Resultat: " + answear);
                        if (answear==false) {
                            dialog.dismiss();
                            builder.create();
                            builder.show();



                        }


                    }
                })
                .setNegativeButton("Malmö", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = false;
                        Log.i("Question1", "Resultat: " + answear);
                        if (answear==false) {
                            dialog.dismiss();
                            builder.create();
                            builder.show();



                        }

                    }
                });
        AlertDialog alert = builder.create();
        Vibrator m = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
        m.vibrate(100);
        alert.show();






    }
    private void Question2()
    {



        mp= MediaPlayer.create(getApplicationContext(), R.raw.l2);
        mp.start();
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setMessage("Vad är Norges huvudstad?")
                .setCancelable(false)
                .setPositiveButton("Oslo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog2, int id) {
                        answear = true;
                        if (answear) {
                            dialog2.cancel();
                            mp.release();
                        }


                    }
                })
                .setNeutralButton("Malmö", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog2, int id) {
                        answear = false;
                        if (answear==false) {
                            dialog2.dismiss();
                            builder2.create();
                            builder2.show();
                        }


                    }
                })
                .setNegativeButton("Stockholm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog2, int id) {

                        answear = false;
                        if (answear==false) {
                            dialog2.dismiss();
                            builder2.create();
                            builder2.show();
                        }

                    }
                });


        AlertDialog alert2 = builder2.create();
        Vibrator m2 = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
        m2.vibrate(100);
        alert2.show();
        Log.i("Question2", "vibrate " + m2);




    }
    private void AddMarkerAt2 ()
    {
        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_place_black_24dp))
                .position(landalettgatan)
                .title("landålettgatan!");
        mMap.addMarker(options);
    }
    private void Question3()
    {

        mp= MediaPlayer.create(getApplicationContext(), R.raw.l2);
        mp.start();
        final AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setMessage("Vad är Danmarks huvudstad?")
                .setCancelable(false)
                .setPositiveButton("Köpenhamn", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = true;
                        Log.i("Question1", "Resultat: " + answear);
                        if (answear) {
                            dialog.cancel();
                            mp.release();
                        }


                    }
                })
                .setNeutralButton("Stockholm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = false;
                        Log.i("Question1", "Resultat: " + answear);
                        if (answear==false) {
                            dialog.dismiss();
                            builder3.create();
                            builder3.show();
                        }


                    }
                })
                .setNegativeButton("Malmö", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = false;
                        Log.i("Question1", "Resultat: " + answear);
                        if (answear==false) {
                            dialog.dismiss();
                            builder3.create();
                            builder3.show();
                        }

                    }
                });
        AlertDialog alert3 = builder3.create();
        Vibrator m = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
        m.vibrate(100);
        alert3.show();






    }
    private void AddMarkerAt3 ()
    {
        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_place_black_24dp))
                .position(gigGatan)
                .title("giggatan!");
        mMap.addMarker(options);
    }
    private void Question4()
    {

        mp= MediaPlayer.create(getApplicationContext(), R.raw.l2);
        mp.start();
        final AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        builder4.setMessage("Vad är Ryssland huvudstad?")
                .setCancelable(false)
                .setPositiveButton("Moskva", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = true;
                        Log.i("Question1", "Resultat: " + answear);
                        if (answear) {
                            mp.release();
                            dialog.cancel();
                        }


                    }
                })
                .setNeutralButton("Köpenhamn", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = false;
                        Log.i("Question1", "Resultat: " + answear);
                        if (answear==false) {
                            dialog.dismiss();
                            builder4.create();
                            builder4.show();
                        }


                    }
                })
                .setNegativeButton("Malmö", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        answear = false;
                        Log.i("Question1", "Resultat: " + answear);
                        close = true;
                        if (answear==false) {
                            dialog.dismiss();
                            builder4.create();
                            builder4.show();
                        }

                    }
                });
        AlertDialog alert = builder4.create();
        Vibrator m = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
        m.vibrate(100);
        alert.show();






    }
    private void AddMarkerAt4 ()
    {
        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_place_black_24dp))
                .position(kupegatan)
                .title("Kupegatan!");
        mMap.addMarker(options);
    }


    @Override
    public void onLocationChanged(Location location)
    {
        double thisLatitude = location.getLatitude();
        double thisLongitude = location.getLongitude();
        LatLng latLng = new LatLng(thisLatitude, thisLongitude);
        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_navigation_black_24dp))
                .title("I am here!");

        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
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


}



