package ac.id.del.delifood.ui.myAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ac.id.del.delifood.databinding.FragmentMyAccountBinding

class MyAccountFragment : Fragment() {

    private var _binding: FragmentMyAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[MyAccountViewModel::class.java]

        _binding = FragmentMyAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMyAccount
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}