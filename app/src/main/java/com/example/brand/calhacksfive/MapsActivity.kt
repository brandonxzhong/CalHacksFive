package com.example.brand.calhacksfive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import android.widget.CheckBox
import android.widget.CompoundButton.OnCheckedChangeListener
import android.app.Activity




class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val printer = findViewById(R.id.radioPopular) as CheckBox
        printer.setOnCheckedChangeListener{
            buttonView, isChecked ->
            if (isChecked) {
                print(isChecked)
            }
        }

        val vending = findViewById(R.id.radioPopular) as CheckBox
        printer.setOnCheckedChangeListener{
            buttonView, isChecked ->
            if (isChecked) {
                print(isChecked)
            }
        }

        val restroom = findViewById(R.id.radioPopular) as CheckBox
        printer.setOnCheckedChangeListener{
            buttonView, isChecked ->
            if (isChecked) {
                print(isChecked)
            }
        }

        val microwave = findViewById(R.id.radioPopular) as CheckBox
        printer.setOnCheckedChangeListener{
            buttonView, isChecked ->
            if (isChecked) {
                print(isChecked)
            }
        }


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
    // TODO Color code the points, and attach them to a OnMarkerClickListener
    override fun onMapReady(googleMap: GoogleMap) {

        val BerkeleyBounds = LatLngBounds(
                LatLng(37.87, -122.2595), LatLng(37.8719, -122.25))

        mMap = googleMap
        Log.d("MapsActivity", "Reached onMapReady");
        // Add a marker in Sydney and move the camera
        val berkeley = LatLng(37.8716, -122.2727)
        val moffit = LatLng(37.8726, -122.2607)

        val string1 = "Printers"
        val string2 = "Microwaves"
        val string3 = "Vending Machines"
        val string4 = "Bathrooms"
        val descrip4 = "Located on the second floor, usually clean and quiet"
        val descrip41 = "Costs 15 cents per print"
        val descrip3 = "Out of Goldfish :("
        val descrip2 = "This microwave is currently broken"
        val descrip1 = "Very dirty, out of tp"
        val coord41 = Coordinate(37.8722, -122.2596, descrip41)
        val coord42 = Coordinate(37.8705, -122.2606, descrip41)
        val coord43 = Coordinate(37.8726, -122.2607)
        val coord31 = Coordinate(37.8745, -122.2573, descrip3)
        val coord11 = Coordinate(37.8726, -122.2607, descrip1)
        val coord44 = Coordinate(37.8757, -122.2593, descrip41)
        val coord32 = Coordinate(37.8697, -122.2514, descrip3)
        val coord33 = Coordinate(37.8749, -122.2553)
        val coord12 = Coordinate(37.8722, -122.2589, descrip4)
        val coord45 = Coordinate(37.8694, -122.2623, descrip41)
        val coord21 = Coordinate(37.8715, -122.2687, descrip2)
        val color1 = BitmapDescriptorFactory.HUE_ORANGE
        val color2 = BitmapDescriptorFactory.HUE_GREEN
        val color3 = BitmapDescriptorFactory.HUE_MAGENTA
        val color4 = BitmapDescriptorFactory.HUE_YELLOW

        var dataprocessor = DataProcessor()

        dataprocessor.setColor(string1, color1)
        dataprocessor.setColor(string2, color2)
        dataprocessor.setColor(string3, color3)
        dataprocessor.setColor(string4, color4)

        var listofstrings = mutableListOf(string1, string2, string3, string4)
        var listofprinters : List<Coordinate> = mutableListOf(coord41, coord42, coord43, coord44, coord45)
        var listofmicrowaves : List<Coordinate> = mutableListOf(coord21)
        var listofvendingmachines : List<Coordinate> = mutableListOf(coord31, coord32, coord33)
        var listofbathrooms : List<Coordinate> = mutableListOf(coord11, coord12)

        dataprocessor.addLocation(string1, listofprinters)
        dataprocessor.addLocation(string2, listofmicrowaves)
        dataprocessor.addLocation(string3, listofvendingmachines)
        dataprocessor.addLocation(string4, listofbathrooms)

        var strings = dataprocessor.getLocations()

        for (f in strings) {
            var currentLocations = dataprocessor.getCoordinates(f)
            for (g in currentLocations) {
                var currColor = dataprocessor.getColor(f)
                var currDescrip = g.getDescription()
                var xVal = g.getxValue()
                var yVal = g.getyValue()
                var currCoordinate = LatLng(xVal, yVal)
                mMap.addMarker(MarkerOptions().position(currCoordinate).title(f).icon(BitmapDescriptorFactory.defaultMarker(currColor)).snippet(currDescrip))
            }
        }

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
