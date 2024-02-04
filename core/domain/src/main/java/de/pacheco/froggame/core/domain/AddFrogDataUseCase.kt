package de.pacheco.froggame.core.domain

import de.pacheco.froggame.core.data.FrogDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddFrogDataUseCase @Inject constructor(
    private val frogDataRepository: FrogDataRepository
) {
    suspend operator fun invoke(name: String) {
        withContext(Dispatchers.Default) { frogDataRepository.add(name) }
    }
}