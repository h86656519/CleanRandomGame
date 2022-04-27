package net.pixnet.cleanrandomgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.pixnet.cleanrandomgame.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentFirstBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)

        binding.btnConfirm.setOnClickListener(this)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm -> {
                if (binding.edRow.text.toString().isNotEmpty() &&
                    binding.edColumn.text.toString().isNotEmpty()
                ) {
                    (requireActivity() as MainActivity).toSecondPage(
                        binding.edRow.text.toString().toInt(),
                        binding.edColumn.text.toString().toInt()
                    )
                }else{
                    (requireActivity() as MainActivity).showErrorToast()
                }

            }
        }
    }


}