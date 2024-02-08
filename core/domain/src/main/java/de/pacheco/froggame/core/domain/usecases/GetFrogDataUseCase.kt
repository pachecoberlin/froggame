package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.data.repos.interfaces.IFrogDataRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetFrogDataUseCase @Inject constructor(private val frogDataRepository: IFrogDataRepository) {
    operator fun invoke(): Flow<List<String>> {
        return frogDataRepository.frogDatas()
    }
}