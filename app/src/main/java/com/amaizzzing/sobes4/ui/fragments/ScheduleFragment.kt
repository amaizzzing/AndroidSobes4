package com.amaizzzing.sobes4.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaizzzing.sobes4.ClassesApp
import com.amaizzzing.sobes4.data.services.IImageLoader
import com.amaizzzing.sobes4.databinding.FragmentScheduleBinding
import com.amaizzzing.sobes4.di.ViewModelFactory
import com.amaizzzing.sobes4.states.BaseState
import com.amaizzzing.sobes4.ui.adapters.ScheduleAdapter
import com.amaizzzing.sobes4.ui.models.ClassesModel
import com.amaizzzing.sobes4.ui.viewmodels.ScheduleViewModel
import javax.inject.Inject

class ScheduleFragment: BaseFragment<FragmentScheduleBinding, ScheduleViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private var scheduleAdapter: ScheduleAdapter? = null

    override fun getViewModelClass(): Class<ScheduleViewModel> = ScheduleViewModel::class.java
    override fun getViewBinding(): FragmentScheduleBinding = FragmentScheduleBinding.inflate(layoutInflater)
    override fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[getViewModelClass()]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ClassesApp.appComponent.inject(this)
    }

    override fun setupViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding?.let {
            it.rvSchedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            scheduleAdapter = ScheduleAdapter(imageLoader, listOf(), skypeClick)
            it.rvSchedule.adapter = scheduleAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getClasses()
    }

    override fun observeData() {
        viewModel.data.observe(viewLifecycleOwner) {
            it?.let {
                renderData(it)
            } ?: renderData(BaseState.Error(Error()))
        }
    }

    override fun renderData(data: BaseState) {
        when(data) {
            is BaseState.Success<*> -> {
                (data.resultData as List<ClassesModel>).also { result ->
                    scheduleAdapter?.let {
                        it.scheduleList = result
                        it.notifyDataSetChanged()
                    }
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