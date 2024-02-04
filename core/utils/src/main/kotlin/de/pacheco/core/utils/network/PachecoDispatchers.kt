package de.pacheco.core.utils.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: PachecoDispatchers)

enum class PachecoDispatchers {
    Default,
    IO,
}
