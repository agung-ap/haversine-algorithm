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
import com.google.android.libraries.places.api.Places
import android.content.SharedPreferences
import id.developer.haversinealgorithm.util.GlobalFunction
import id.developer.haversinealgorithm.util.HaversineAlgorithm


class MainActivity : AppCompatActivity() {
    private lateinit var startLoc: EditText
    private lateinit var endLoc: EditText
    private lateinit var calculate: Button
    private lateinit var result: TextView
    private lateinit var startPosition: TextView
    private lateinit var endPosition: TextView

    private val AUTOCOMPLETE_REQUEST_CODE_START: Int = 0
    private val AUTOCOMPLETE_REQUEST_CODE_END: Int = 1

    private lateinit var prefs: SharedPreferences
    private lateinit var prefs2: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the SDK
        Places.initialize(applicationContext, "AIzaSyCI-aoNQAxR_oVzG1qgfvPSiRKVdAmhZD0")
        prefs = getSharedPreferences(applicationContext.getString(R.string.GET_CREDENTIAL), 0)
        prefs2 = getSharedPreferences(applicationContext.getString(R.string.GET_CREDENTIAL_2), 0)

        bindView()
        startLoc.setOnClickListener {
            onSearchCalled(AUTOCOMPLETE_REQUEST_CODE_START)
        }
        endLoc.setOnClickListener {
            onSearchCalled(AUTOCOMPLETE_REQUEST_CODE_END)
        }
        calculate.setOnClickListener {
            var latStart: Float = prefs.getFloat(applicationContext.getString(R.string.LAT_START), 0.0f)
            var lngStart: Float = prefs.getFloat(applicationContext.getString(R.string.LNG_END), 0.0f)
            var latEnd: Float = prefs2.getFloat(applicationContext.getString(R.string.LAT_END), 0.0f)
            var lngEnd: Float = prefs2.getFloat(applicationContext.getString(R.string.LNG_END), 0.0f)

            Toast.makeText(this,
                "Result " + latStart + "," + lngStart + "," + latEnd + "," + lngEnd,
                Toast.LENGTH_LONG).show()

            var startPos : String = "Start Position: " + latStart + " , " + lngStart
            var endPos : String = "Start Position: " + latEnd + " , " + lngEnd


            result.setText("" + HaversineAlgorithm.calculate(latStart, lngStart, latEnd, lngEnd))
            startPosition.setText(startPos)
            endPosition.setText(endPos)
        }
    }

    fun bindView(){
        startLoc = findViewById(R.id.start_loc)
        endLoc = findViewById(R.id.end_loc)
        calculate = findViewById(R.id.calculate_haversine)
        result = findViewById(R.id.result)
        startPosition = findViewById(R.id.start_position)
        endPosition = findViewById(R.id.end_position)
    }

    fun onSearchCalled(requestCode: Int){
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
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE_START) {
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                GlobalFunction.addLatLngStartPositon(this@MainActivity, place.latLng)
                startLoc.setText(place.name)

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
        }else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_END){
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                endLoc.setText(place.name)
                GlobalFunction.addLatLngEndPositon(this@MainActivity, place.latLng)

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
        }
    }



}
