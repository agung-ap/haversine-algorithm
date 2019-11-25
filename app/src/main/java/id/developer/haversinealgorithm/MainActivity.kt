package id.developer.haversinealgorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.libraries.places.widget.Autocomplete
import android.content.Intent
import com.google.android.libraries.places.api.model.Place
import android.widget.Toast
import android.app.Activity
import com.google.android.libraries.places.widget.AutocompleteActivity
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var startLoc: EditText
    private lateinit var endLoc: EditText
    private lateinit var calculate: Button
    private lateinit var result: TextView

    private var latStart: Double = 0.0
    private var lngStart: Double = 0.0
    private var latEnd: Double = 0.0
    private var lngEnd: Double = 0.0

    private var AUTOCOMPLETE_REQUEST_CODE: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        startLoc.setOnClickListener {
            onSearchCalled()
        }
    }

    fun bindView(){
        startLoc = findViewById(R.id.start_loc)
        endLoc = findViewById(R.id.end_loc)
        calculate = findViewById(R.id.calculate_haversine)
        result = findViewById(R.id.result)
    }

    fun onSearchCalled(){
        // Set the fields to specify which types of place data to return.
        val fields = listOf(
            Place.Field.ID,
            Place.Field.NAME,
            Place.Field.ADDRESS,
            Place.Field.LAT_LNG
        )
        // Start the autocomplete intent.
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.FULLSCREEN, fields
        ).setCountry("ID") //NIGERIA
            .build(this)
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                Log.i(
                    "MainActivity",
                    "Place: " + place.name + ", " + place.id + ", " + place.address
                )

                Toast.makeText(
                    this@MainActivity,
                    "ID: " + place.id + "address:" + place.address + "Name:" + place.name + " latlong: " + place.latLng,
                    Toast.LENGTH_LONG
                ).show()
//                val address = place.address
                // do query with address

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                val status = Autocomplete.getStatusFromIntent(data!!)
                Toast.makeText(
                    this@MainActivity,
                    "Error: " + status.statusMessage,
                    Toast.LENGTH_LONG
                ).show()
                val message: String = status.statusMessage!!
                Log.i("MainActivity", message)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }    }

}
