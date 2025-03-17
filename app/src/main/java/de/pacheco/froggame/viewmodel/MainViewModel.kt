package de.pacheco.froggame.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.pacheco.froggame.repos.interfaces.IStringRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val stringRepository: IStringRepository) : ViewModel()

