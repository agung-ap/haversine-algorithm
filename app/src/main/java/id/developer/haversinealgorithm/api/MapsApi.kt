package id.developer.haversinealgorithm.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.POST



interface MapsApi {
    @POST("/directions/json")
    fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") key: String
    ): Call<ResponseBody>

    @POST("/place/findplacefromtext/json")
    fun getPlace(
        @Query("input") input: String,
        @Query("inputtype") inputtype: String,
        @Query("fields") fields: String,
        @Query("key") key: String
    ): Call<ResponseBody>
}