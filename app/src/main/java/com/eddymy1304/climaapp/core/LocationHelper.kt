package com.eddymy1304.climaapp.core

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

interface LocationTracker {
    suspend fun getLastLocation(): Location?
    suspend fun getCurrentLocation(priority: Boolean = true): Location?
    fun verifyLocationPermission(): Boolean

    suspend fun getLocation(): Location?
}

@SuppressLint("MissingPermission")
class DefaultLocationTracker(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getLastLocation(): Location? {

        if (!verifyLocationPermission()) return null

        return suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        continuation.resume(result)
                    } else {
                        continuation.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    continuation.resume(it)
                }
                addOnFailureListener {
                    continuation.resume(null)
                }
                addOnCanceledListener {
                    continuation.cancel()
                }
            }
        }
    }

    override suspend fun getCurrentLocation(priority: Boolean): Location? {

        if (!verifyLocationPermission()) return null

        val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
        else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        return suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.getCurrentLocation(
                accuracy,
                CancellationTokenSource().token
            ).addOnSuccessListener {
                continuation.resume(it)
            }.addOnFailureListener {
                continuation.resume(null)
            }.addOnCanceledListener {
                continuation.cancel()
            }
        }

    }

    override fun verifyLocationPermission(): Boolean {

        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager

        val isGpsEnabled =
            locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                    locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER)

        Log.d(
            "LocationTracker", """
            
            GPS: $isGpsEnabled
            
            ACCESS_FINE_LOCATION: $hasAccessFineLocationPermission
            ACCESS_COARSE_LOCATION: $hasAccessCoarseLocationPermission
            
            """.trimIndent()
        )

        return (isGpsEnabled && (hasAccessFineLocationPermission || hasAccessCoarseLocationPermission))

    }

    override suspend fun getLocation(): Location? {
        return getCurrentLocation() ?: getLastLocation()
    }
}

