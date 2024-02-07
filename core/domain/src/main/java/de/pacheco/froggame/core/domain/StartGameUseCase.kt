package de.pacheco.froggame.core.domain

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartGameUseCase @Inject constructor(private val catchFrogRepository: ICatchFrogRepository) {
    operator fun invoke(rows:Int,cols:Int): Flow<List<Int>> {
        catchFrogRepository.stopGame()
        return catchFrogRepository.startGame(rows*cols)
    }
}