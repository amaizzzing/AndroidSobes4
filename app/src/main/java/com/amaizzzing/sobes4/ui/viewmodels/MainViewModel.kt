package com.amaizzzing.sobes4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amaizzzing.sobes4.data.repositories.*
import com.amaizzzing.sobes4.states.BaseState
import com.amaizzzing.sobes4.ui.models.ClassesModel
import com.amaizzzing.sobes4.ui.models.HomeworkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.min

class MainViewModel @Inject constructor(
    private val classesRepository: IClassesRepository,
    private val timerRepository: ITimerRepository,
    private val homeworkRepository: IHomeworkRepository
): BaseViewModel<BaseState>() {
    private var timerJob: Job? = null

    private val _timerData = MutableLiveData<String>()
    val timerData: LiveData<String> = _timerData
    fun setTimerData(newData: String) {
        _timerData.value = newData
    }

    private val _homeworkData = MutableLiveData<List<HomeworkModel>>()
    val homeworkData: LiveData<List<HomeworkModel>> = _homeworkData
    fun setHomeworkData(newData: List<HomeworkModel>) {
        _homeworkData.value = newData
    }

    fun startTimer(newTime: Long) {
        timerJob = viewModelScope.launch {
            timerRepository.startTimer(newTime)?.collect { time ->
                val seconds = (time) % 60
                val minutes = (time / (60) % 60)
                val hours = (time / (60 * 60) % 24)
                val corrHours = if (hours > 9) "$hours" else "0$hours"
                val corrMinutes = if (minutes > 9) "$minutes" else "0$minutes"
                val corrSeconds = if (seconds > 9) "$seconds" else "0$seconds"

                withContext(Dispatchers.Main) {
                    setTimerData("$corrHours:$corrMinutes:$corrSeconds")
                }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    fun getCurrentTimerTime(): Long = timerRepository.getCurrentTime()

    fun getClasses() = viewModelScope.launch {
        setData(BaseState.Loading)

        val classesInfo = withContext(Dispatchers.IO) {
            classesRepository.getTodaySortedClasses(false)
        }

        setData(BaseState.Success(classesInfo to getIndexToScroll(classesInfo)))
    }

    fun getHomeworks() = viewModelScope.launch {
        setHomeworkData(homeworkRepository.getSortedHomeworks())
    }

    private fun getIndexToScroll(resultList: List<ClassesModel>): Int {
        var ind = 0
        var mintime = Long.MAX_VALUE
        val currentTime = System.currentTimeMillis()

        resultList.forEachIndexed { index, classesModel ->
            val diff =
                min(
                    abs(currentTime - classesModel.timeStartInMillis),
                    abs(currentTime - classesModel.timeEndInMillis)
                )
            if (diff < mintime) {
                mintime = diff
                ind = index
            }
        }

        return ind
    }
}