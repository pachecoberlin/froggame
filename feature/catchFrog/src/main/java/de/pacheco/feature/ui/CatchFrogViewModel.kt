package de.pacheco.feature.ui

import androidx.lifecycle.ViewModel
import de.pacheco.froggame.core.data.repos.CatchFrogRepository
import javax.inject.Inject

class CatchFrogViewModel @Inject constructor(
    private val catchFrogRepository: CatchFrogRepository
) : ViewModel() {

}