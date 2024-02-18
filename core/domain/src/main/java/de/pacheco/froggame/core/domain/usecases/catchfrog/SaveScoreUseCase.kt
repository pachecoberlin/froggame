package de.pacheco.froggame.core.domain.usecases.catchfrog

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import javax.inject.Inject

class SaveScoreUseCase @Inject constructor(private val catchFrogRepository: ICatchFrogRepository) {
    suspend operator fun invoke(score: Int, frogCount:Int) {
        catchFrogRepository.saveScore(score,frogCount)
    }
}
