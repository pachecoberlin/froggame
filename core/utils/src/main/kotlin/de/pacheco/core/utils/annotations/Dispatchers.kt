package de.pacheco.core.utils.annotations

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: CoroutineDispatchers)

enum class CoroutineDispatchers {
    Default,
    IO,
}
