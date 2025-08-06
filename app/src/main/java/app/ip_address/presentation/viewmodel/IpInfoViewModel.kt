package app.ip_address.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ip_address.domain.model.IpInfo
import app.ip_address.domain.usecase.GetIpInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IpInfoViewModel @Inject constructor(
    private val useCase: GetIpInfoUseCase
) : ViewModel() {

    var state by mutableStateOf<IpInfo?>(null)
        private set

    var isLoading by mutableStateOf(false)

    init {
        fetchIpInfo()
    }

    fun fetchIpInfo() {
        viewModelScope.launch {
            isLoading = true
            state = useCase()
            isLoading = false
        }
    }
}