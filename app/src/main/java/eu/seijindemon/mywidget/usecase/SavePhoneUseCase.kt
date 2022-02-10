package eu.seijindemon.mywidget.usecase

import eu.seijindemon.mywidget.data.repository.DataStorePreferenceRepository
import javax.inject.Inject

class SavePhoneUseCase  @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) {
    suspend operator fun invoke(phone: String) {
        return dataStorePreferenceRepository.setPhone(phone)
    }
}