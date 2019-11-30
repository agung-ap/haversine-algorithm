package id.developer.haversinealgorithm.util

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.maps.model.LatLng
import id.developer.haversinealgorithm.R

object GlobalFunction {
    fun addLatLngStartPositon(context: Context, pos:LatLng?) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.GET_CREDENTIAL), 0)
        val editor = sharedPreferences.edit()
        if (pos != null) {
            editor.putFloat(context.getString(R.string.LAT_START), pos.latitude.toFloat())
            editor.putFloat(context.getString(R.string.LNG_START), pos.longitude.toFloat())
        }else{
            editor.putFloat(context.getString(R.string.LAT_START), 0.0f)
            editor.putFloat(context.getString(R.string.LNG_START), 0.0f)
        }
        editor.apply()
    }

    fun addLatLngEndPositon(context: Context, pos: LatLng?) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.GET_CREDENTIAL_2), 0)
        val editor = sharedPreferences.edit()
        if (pos != null) {
            editor.putFloat(context.getString(R.string.LAT_END), pos.latitude.toFloat())
            editor.putFloat(context.getString(R.string.LNG_END), pos.longitude.toFloat())
        }else {
            editor.putFloat(context.getString(R.string.LAT_END), 0.0f)
            editor.putFloat(context.getString(R.string.LNG_END), 0.0f)
        }
        editor.apply()
    }
}