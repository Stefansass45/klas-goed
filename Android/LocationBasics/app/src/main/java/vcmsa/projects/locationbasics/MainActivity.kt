package vcmsa.projects.locationbasics

import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException
import java.util.Locale

class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var tvOutput: TextView
    private lateinit var tvAddress: TextView
    private val locationPermissionsCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        tvOutput = findViewById(R.id.lblOutput)
        tvAddress = findViewById(R.id.lblAddress)
        btnUpdate.setOnClickListener {
            getLocation()
        }
    }

    override fun onLocationChanged(location: Location) {
        tvOutput.text = "Latitude: ${location.latitude} + Longitude: ${location.longitude}"
        getAddessFromLocation(location)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionsCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getAddessFromLocation(location: Location){
        val geocoder = Geocoder(this, Locale.getDefault())
        tvAddress = findViewById<TextView>(R.id.lblAddress)
        try {
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                val addressLine = address.getAddressLine(0)
                tvAddress.text = addressLine
            }else{
                tvAddress.text = "Unable to get address"
            }
        } catch (e: IOException) {
            e.printStackTrace()
            tvAddress.text = "Error getting address"
        }
    }
    private fun getLocation() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.ACCESS_FINE_LOCATION
        )
                    != PackageManager.PERMISSION_GRANTED)
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionsCode
            )
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000, 5f, this)

        }
    }
}
