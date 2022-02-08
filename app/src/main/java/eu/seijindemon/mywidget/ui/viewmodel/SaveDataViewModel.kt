package eu.seijindemon.mywidget.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.mywidget.data.repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveDataViewModel @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
): ViewModel() {

    private val _phone = MutableLiveData("")
    var phone: LiveData<String> = _phone

    private val _telegram = MutableLiveData("")
    var telegram: LiveData<String> = _telegram

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getPhone().collect {
                _phone.value = it
            }
            dataStorePreferenceRepository.getTelegram().collect {
                _telegram.value = it
            }
        }
    }

    suspend fun savePhone(phone: String) {
        dataStorePreferenceRepository.setPhone(phone = phone)
    }

    suspend fun saveTelegram(telegram: String) {
        dataStorePreferenceRepository.setTelegram(telegram = telegram)
    }
}