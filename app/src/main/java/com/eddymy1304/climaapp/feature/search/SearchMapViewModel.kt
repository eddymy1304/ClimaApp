package com.eddymy1304.climaapp.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.eddymy1304.climaapp.feature.search.navigation.SearchMapScreen
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchMapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _uiState = MutableStateFlow(
        UiStateSearchMapScreen(
            latLng = LatLng(
                savedStateHandle.toRoute<SearchMapScreen>().latitude ?: 0.0,
                savedStateHandle.toRoute<SearchMapScreen>().longitude ?: 0.0
            ),
            city = savedStateHandle.toRoute<SearchMapScreen>().city
        )
    )

    val uiState = _uiState.asStateFlow()


    fun setLatLng(latLng: LatLng) {
        _uiState.update {
            it.copy(
                latLng = latLng
            )
        }
    }
}