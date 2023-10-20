package learn.atharv.hodmoudule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.atharv.hodmoudule.viewmodel.HodHomeViewModel
import learn.atharv.hodmoudule.R

class HodHomeFragment : Fragment() {

    companion object {
        fun newInstance() = HodHomeFragment()
    }

    private lateinit var viewModel: HodHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyComposable()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}

@Composable
fun MyComposable() {
    Text(text = "Vishal is my name atharv")
}
