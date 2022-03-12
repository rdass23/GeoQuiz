package com.example.geoquiz

import android.location.Geocoder
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.osmdroid.bonuspack.location.GeocoderNominatim
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.Projection


class LearningModeActivity : AppCompatActivity() {

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private lateinit var map : MapView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_learning_mode)

        map = findViewById<MapView>(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.minZoomLevel = 0.15
        map.setScrollableAreaLimitLatitude(MapView.getTileSystem().getMaxLatitude(), MapView.getTileSystem().getMinLatitude(), 0);

        map.controller.setZoom(1.0)
        map.tilesScaleFactor = 3F
    }

    override fun onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>();
        var i = 0;
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i]);
            i++;
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val actionType = ev.action
        when (actionType) {
            MotionEvent.ACTION_UP -> {
                val proj: Projection = map.projection
                val loc: GeoPoint = proj.fromPixels(ev.x.toInt(), ev.y.toInt()) as GeoPoint
                val longitude = loc.longitude
                val latitude = loc.latitude
                val results = Geocoder(this).getFromLocation(latitude, longitude, 1)
                if (results != null) {
                    if(results.size > 0) {
                        val country = results[0].countryName
                        val toast = Toast.makeText(
                            applicationContext,
                            "Country $country", Toast.LENGTH_LONG
                        )
                        toast.show()
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}