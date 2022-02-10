package eu.seijindemon.mywidget.usecase

import eu.seijindemon.mywidget.data.repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhoneUseCase  @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return dataStorePreferenceRepository.getPhone()
    }
}