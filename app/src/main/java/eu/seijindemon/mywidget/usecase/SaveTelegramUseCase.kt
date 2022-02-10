package eu.seijindemon.mywidget.usecase

import eu.seijindemon.mywidget.data.repository.DataStorePreferenceRepository
import javax.inject.Inject

class SaveTelegramUseCase  @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) {
    suspend operator fun invoke(telegram: String) {
        return dataStorePreferenceRepository.setTelegram(telegram)
    }
}