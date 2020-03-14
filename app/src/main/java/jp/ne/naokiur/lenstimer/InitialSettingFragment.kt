package jp.ne.naokiur.lenstimer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import jp.ne.naokiur.lenstimer.databinding.FragmentInitialSettingBinding

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

        return binding.root
    }
}
