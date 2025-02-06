package com.eddymy1304.climaapp.feature.search

import com.google.android.gms.maps.model.LatLng

data class UiStateSearchMapScreen(
    val latLng: LatLng? = null,
    val city: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
