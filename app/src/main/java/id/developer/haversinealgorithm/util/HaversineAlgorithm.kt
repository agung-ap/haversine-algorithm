package id.developer.haversinealgorithm.util

object HaversineAlgorithm {
    const val earthRadiusKm: Double = 6372.8

    fun calculate (latStart : Float, lngStart : Float, latEnd: Float, lngEnd:Float): Double {
        val dLat = Math.toRadians((latEnd - latStart).toDouble())
        val dLon = Math.toRadians((lngEnd - lngStart).toDouble())
        val originLat = Math.toRadians(latStart.toDouble())
        val destinationLat = Math.toRadians(latEnd.toDouble())

        val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(Math.sin(dLon / 2), 2.toDouble()) * Math.cos(originLat) * Math.cos(destinationLat)
        val c = 2 * Math.asin(Math.sqrt(a))
        return earthRadiusKm * c
    }
}