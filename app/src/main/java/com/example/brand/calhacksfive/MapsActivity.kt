package com.example.brand.calhacksfive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLngBounds



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        val BerkeleyBounds = LatLngBounds(
                LatLng(37.87, -122.2595), LatLng(37.8719, -122.25))

        mMap = googleMap
        Log.d("MapsActivity", "Reached onMapReady");
        // Add a marker in Sydney and move the camera
        val berkeley = LatLng(37.8716, -122.2727)
        val moffit = LatLng(37.8726, -122.2607)
        mMap.addMarker(MarkerOptions().position(berkeley).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(moffit).title("Moffit Library")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .snippet("test"))
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(LatLng(37.8719, -122.2585),15.5f,0.0f,80.0f)))
        mMap.setLatLngBoundsForCameraTarget(BerkeleyBounds)
        mMap.setMinZoomPreference(15.5f)
    }
}
