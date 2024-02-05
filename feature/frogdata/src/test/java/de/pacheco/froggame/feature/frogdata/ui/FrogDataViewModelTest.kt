package de.pacheco.froggame.feature.frogdata.ui

import de.pacheco.froggame.core.data.repos.FakeFrogDataRepository
import de.pacheco.froggame.core.domain.AddFrogDataUseCase
import de.pacheco.froggame.core.domain.GetFrogDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FrogDataViewModelTest {
    @Test
    fun uiState_initiallyLoading() = runTest {
        val fakeFrogDataRepository = FakeFrogDataRepository()
        val viewModel = FrogDataViewModel(GetFrogDataUseCase(fakeFrogDataRepository), AddFrogDataUseCase(fakeFrogDataRepository, Dispatchers.IO))
        assertEquals(viewModel.uiState.first(), FrogDataUiState.Loading)
    }
}