package com.amaizzzing.sobes4.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.amaizzzing.sobes4.data.repositories.IClassesRepository
import com.amaizzzing.sobes4.data.repositories.IHomeworkRepository
import com.amaizzzing.sobes4.data.repositories.ITimerRepository
import com.amaizzzing.sobes4.states.BaseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val classesRepository: IClassesRepository
): BaseViewModel<BaseState>() {
    fun getClasses() = viewModelScope.launch {
        setData(BaseState.Loading)

        val classesInfo = withContext(Dispatchers.IO) {
            classesRepository.getTodaySortedClasses(true)
        }

        setData(BaseState.Success(classesInfo))
    }
}