package de.pacheco.froggame.core.domain

import de.pacheco.core.utils.network.Dispatcher
import de.pacheco.core.utils.network.PachecoDispatchers
import de.pacheco.froggame.core.data.FrogDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddFrogDataUseCase @Inject constructor(
    private val frogDataRepository: FrogDataRepository,
    @Dispatcher(PachecoDispatchers.IO) private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(name: String) {
        withContext(defaultDispatcher) { frogDataRepository.add(name) }
    }
}