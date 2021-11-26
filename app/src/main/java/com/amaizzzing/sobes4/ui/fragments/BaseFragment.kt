package com.amaizzzing.sobes4.ui.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.amaizzzing.sobes4.states.BaseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VBinding : ViewBinding?, VViewModel : ViewModel>: Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()

    protected lateinit var viewModel: VViewModel
    protected abstract fun getViewModelClass(): Class<VViewModel>
    protected abstract fun initViewModel()

    protected var binding: VBinding? = null

    protected abstract fun getViewBinding(): VBinding?
    protected abstract fun renderData(data: BaseState)
    open fun observeData() {}
    open fun setupViews() {}

    protected var skypeClick: () -> Unit = {
        val packageManager: PackageManager = requireContext().packageManager
        try {
            startActivity(packageManager.getLaunchIntentForPackage("com.skype.raider.Main"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Skype not found!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBindingAndViewModel()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeData()
    }

    private fun initBindingAndViewModel() {
        binding = getViewBinding()
        initViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        coroutineContext.cancelChildren()
        binding = null
    }
}