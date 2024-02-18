package de.pacheco.froggame.core.domain.usecases.catchfrog

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatchFrogHighScoreUseCase @Inject constructor(private val catchFrogRepository: ICatchFrogRepository) {

    operator fun invoke(frogAmount: Int): Flow<Int> {
        return catchFrogRepository.highScore(frogAmount)
    }
}