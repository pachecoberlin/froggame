package de.pacheco.froggame.core.domain

import de.pacheco.froggame.core.data.FrogDataRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetFrogDataUseCase @Inject constructor(private val frogDataRepository: FrogDataRepository) {
    operator fun invoke(): Flow<List<String>> {
        return frogDataRepository.frogDatas
    }
}