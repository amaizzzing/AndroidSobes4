package com.amaizzzing.sobes4.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaizzzing.sobes4.ClassesApp
import com.amaizzzing.sobes4.R
import com.amaizzzing.sobes4.data.services.IImageLoader
import com.amaizzzing.sobes4.databinding.FragmentMainBinding
import com.amaizzzing.sobes4.di.ViewModelFactory
import com.amaizzzing.sobes4.states.BaseState
import com.amaizzzing.sobes4.ui.adapters.ClassesAdapter
import com.amaizzzing.sobes4.ui.adapters.HomeworkAdapter
import com.amaizzzing.sobes4.ui.models.ClassesModel
import com.amaizzzing.sobes4.ui.viewmodels.MainViewModel
import javax.inject.Inject
import android.content.Intent

import android.content.ComponentName
import android.content.pm.PackageManager
import android.net.Uri
import java.lang.Exception
import android.R.id
import android.content.Context

import android.content.SharedPreferences

const val TIMER_KEY = "timerTime"
const val BEFORE_TIME_KEY = "current_time"

class MainFragment: BaseFragment<FragmentMainBinding, MainViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private var classesAdapter:ClassesAdapter? = null
    private var homeworkAdapter:HomeworkAdapter? = null

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
    override fun getViewBinding(): FragmentMainBinding? = FragmentMainBinding.inflate(layoutInflater)
    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[getViewModelClass()]
    }

    override fun setupViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding?.let {
            it.rvTodayClasses.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            classesAdapter = ClassesAdapter(imageLoader, listOf(), skypeClick)
            it.rvTodayClasses.adapter = classesAdapter

            it.rvHomeworks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            homeworkAdapter = HomeworkAdapter(imageLoader, listOf())
            it.rvHomeworks.adapter = homeworkAdapter
        }
    }

    override fun observeData() {
        viewModel.data.observe(viewLifecycleOwner) {
            it?.let {
                renderData(it)
            } ?: renderData(BaseState.Error(Error()))
        }

        viewModel.timerData.observe(viewLifecycleOwner) {
            it.split(":").also { time ->
                binding?.let { bind ->
                    with(bind) {
                        textHoursFirst.text = time[0].first().toString()
                        textHoursSecond.text = time[0][1].toString()
                        textMinutesFirst.text = time[1].first().toString()
                        textMinutesSecond.text = time[1][1].toString()
                        textSecondsFirst.text = time[2].first().toString()
                        textSecondsSecond.text = time[2][1].toString()
                    }
                }
            }
        }

        viewModel.homeworkData.observe(viewLifecycleOwner) { resultList ->
            homeworkAdapter?.let {
                it.homeworkList = resultList
                it.notifyDataSetChanged()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()

        viewModel.stopTimer()

        saveSharedPrefs()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ClassesApp.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            startTimer(getTimeForTimer())
            getClasses()
            getHomeworks()
        }
    }

    private fun saveSharedPrefs() {
        val pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val edt = pref.edit()
        edt.putLong(TIMER_KEY, viewModel.getCurrentTimerTime())
        edt.putLong(BEFORE_TIME_KEY, System.currentTimeMillis())
        edt.apply()
    }

    private fun getTimeForTimer(): Long {
        val pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val currentTime = System.currentTimeMillis()
        val beforeTime = pref.getLong(BEFORE_TIME_KEY, currentTime)
        val timeDiff = (currentTime - beforeTime) / 1000
        return pref.getLong(TIMER_KEY, 144000) - timeDiff
    }

    override fun renderData(data: BaseState) {
        when(data) {
            is BaseState.Success<*> -> {
                (data.resultData as Pair<List<ClassesModel>, Int>).also { result ->
                    classesAdapter?.let {
                        it.classesList = result.first
                        it.notifyDataSetChanged()
                    }
                    binding?.todayClasses?.text = getString(R.string.today_classes, result.first.size)

                    binding?.rvTodayClasses?.scrollToPosition(result.second)
                }
            }
            is BaseState.Error -> {
                Toast.makeText(requireContext(), "ERROR!", Toast.LENGTH_LONG).show()
            }
            BaseState.Loading -> {
                Toast.makeText(requireContext(), "Loading!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}