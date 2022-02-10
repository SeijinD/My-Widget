package eu.seijindemon.mywidget.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.seijindemon.mywidget.usecase.GetPhoneUseCase
import eu.seijindemon.mywidget.usecase.GetTelegramUseCase
import eu.seijindemon.mywidget.usecase.SavePhoneUseCase
import eu.seijindemon.mywidget.usecase.SaveTelegramUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveDataViewModel @Inject constructor(
    private val savePhoneUseCase: SavePhoneUseCase,
    private val saveTelegramUseCase: SaveTelegramUseCase,
    private val getPhoneUseCase: GetPhoneUseCase,
    private val getTelegramUseCase: GetTelegramUseCase
): ViewModel() {

    private val _phone = MutableLiveData("")
    var phone: LiveData<String> = _phone

    private val _telegram = MutableLiveData("")
    var telegram: LiveData<String> = _telegram

    init {
        viewModelScope.launch {
            getPhoneUseCase().collect { phone ->
                _phone.value = phone
            }
            getTelegramUseCase().collect { telegram ->
                _telegram.value = telegram
            }
        }
    }

    suspend fun savePhone(phone: String) {
        savePhoneUseCase.invoke(phone = phone)
    }

    suspend fun saveTelegram(telegram: String) {
        saveTelegramUseCase.invoke(telegram = telegram)
    }
}