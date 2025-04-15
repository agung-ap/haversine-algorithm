# Haversine Algorithm Android Application

## Overview
This Android application calculates the distance between two geographic locations using the Haversine formula. The app allows users to select starting and ending locations via Google Places Autocomplete interface, and then calculates the distance between these points.

## Features
- Google Places Autocomplete integration for location selection
- Geographic coordinate display for selected locations
- Distance calculation using the Haversine formula
- Simple and intuitive user interface

## Prerequisites
- Android Studio
- Google Places API key
- Android SDK 

## Installation
1. Clone the repository
2. Open the project in Android Studio
3. Replace the Google Places API key in the `Places.initialize()` method with your own API key
4. Build and run the application on your device or emulator

## How to Use
1. Launch the application
2. Tap on the "Start Location" field to select your starting point using the Google Places Autocomplete interface
3. Tap on the "End Location" field to select your destination using Google Places Autocomplete
4. Press the "Calculate" button to compute the distance between the two locations
5. View the result along with the geographic coordinates of both locations

## Project Structure
- `MainActivity.kt`: Main activity handling user interaction and calculation
- `HaversineAlgorithm.kt`: Utility class that implements the Haversine formula
- `GlobalFunction.kt`: Helper functions for storing location data
- `activity_main.xml`: Main layout file for the UI

## Haversine Formula
The Haversine formula determines the great-circle distance between two points on a sphere given their longitudes and latitudes. It is particularly useful for calculating distances between points on the Earth.

## Dependencies
- Google Play Services Places API
- AndroidX AppCompat

## Configuration
The application uses SharedPreferences to store location data:
- Start location coordinates are stored with keys defined in `R.string.LAT_START` and `R.string.LNG_END`
- End location coordinates are stored with keys defined in `R.string.LAT_END` and `R.string.LNG_END`
