package de.pacheco.feature.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.pacheco.froggame.core.data.repos.CatchFrogRepository
import javax.inject.Inject

@HiltViewModel
class CatchFrogViewModel @Inject constructor(
    private val catchFrogRepository: CatchFrogRepository
) : ViewModel() {
 val wtf=7
}