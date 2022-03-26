package com.example.geoquiz

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.Projection
import java.io.InputStreamReader


class LearningModeActivity : AppCompatActivity() {

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private lateinit var map : MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        setContentView(R.layout.activity_learning_mode)

        map = findViewById<MapView>(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.minZoomLevel = 0.15
        map.setScrollableAreaLimitLatitude(MapView.getTileSystem().maxLatitude, MapView.getTileSystem().minLatitude, 0)

        map.controller.setZoom(1.0)
        map.tilesScaleFactor = 3F
    }

    override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        var countries = arrayListOf<Country>()

        val csvReader = CSVReaderBuilder(InputStreamReader(assets.open("countries.csv")))
            .withCSVParser(CSVParserBuilder().withSeparator(',').build())
            .build()
        var line: Array<String>? = csvReader.readNext()
        while (line != null) {
            countries.add(Country(line[0], line[1], line[2], line[3], line[4], line[5]))
            line = csvReader.readNext()
        }

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

                        val intent = Intent(this, InfoCardActivity::class.java).apply {
                            putExtra("country", country)
                        }
                        startActivity(intent)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}