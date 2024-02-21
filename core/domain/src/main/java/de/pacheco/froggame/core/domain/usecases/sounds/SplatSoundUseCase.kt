package de.pacheco.froggame.core.domain.usecases.sounds

import de.pacheco.core.utils.annotations.CoroutineDispatchers
import de.pacheco.core.utils.annotations.Dispatcher
import de.pacheco.froggame.core.data.repos.interfaces.ISoundRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplatSoundUseCase @Inject constructor(
    private val soundRepository: ISoundRepository,
    @Dispatcher(CoroutineDispatchers.IO) private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke() {
        withContext(defaultDispatcher) { soundRepository.playSplat() }
    }
}