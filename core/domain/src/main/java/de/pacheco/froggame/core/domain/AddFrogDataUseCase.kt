package de.pacheco.froggame.core.domain

import de.pacheco.core.utils.annotations.Dispatcher
import de.pacheco.core.utils.annotations.CoroutineDispatchers
import de.pacheco.froggame.core.data.repos.interfaces.IFrogDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddFrogDataUseCase @Inject constructor(
    private val frogDataRepository: IFrogDataRepository,
    @Dispatcher(CoroutineDispatchers.IO) private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(name: String) {
        withContext(defaultDispatcher) { frogDataRepository.add(name) }
    }
}