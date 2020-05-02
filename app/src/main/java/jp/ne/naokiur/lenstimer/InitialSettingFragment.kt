package jp.ne.naokiur.lenstimer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import jp.ne.naokiur.lenstimer.databases.AppDatabase
import jp.ne.naokiur.lenstimer.databinding.FragmentInitialSettingBinding
import jp.ne.naokiur.lenstimer.viewmodel.LensViewModel
import jp.ne.naokiur.lenstimer.viewmodel.LensViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class InitialSettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentInitialSettingBinding>(
            inflater,
            R.layout.fragment_initial_setting,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val viewModelFactory = LensViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LensViewModel::class.java)
        // ここで実行すると、本スレッドでやってはいけない、的なエラーが発生する
        // ViewModelに渡してあげなければならぬ

        binding.lifecycleOwner = this
        binding.lensViewModel = viewModel

        return binding.root
    }
}
