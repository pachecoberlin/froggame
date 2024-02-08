package de.pacheco.froggame.core.domain

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StartGameUseCase @Inject constructor(private val catchFrogRepository: ICatchFrogRepository) {
    suspend operator fun invoke(rows:Int, cols:Int): Flow<List<Int>> {
        catchFrogRepository.stopGame(0)
        catchFrogRepository.startGame(rows*cols)
        return flow {  }
    }
}