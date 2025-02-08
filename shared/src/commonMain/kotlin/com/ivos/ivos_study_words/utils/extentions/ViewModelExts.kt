package com.ivos.ivos_study_words.utils.extentions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

fun ViewModel.executeSuspend(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> Unit,
) {
    viewModelScope.launch(dispatcher) { block.invoke() }
}
