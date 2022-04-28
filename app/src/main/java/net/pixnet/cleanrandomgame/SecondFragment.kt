package net.pixnet.cleanrandomgame

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import net.pixnet.cleanrandomgame.databinding.SecondFragmentBinding

class SecondFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: SecondFragmentBinding
    private lateinit var viewModel: SecondViewModel
    private var inputRow: Int = 0 //x
    private var inputColumn: Int = 0   //y
    private var lastCoordinateIndex = 0
    private lateinit var adapter: GridAdapter

    companion object {
        @JvmStatic
        fun newInstance(row: Int, column: Int) = SecondFragment().apply {
            arguments = Bundle().apply {
                putInt("key_row", row)
                putInt("key_column", column)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            inputRow = it.getInt("key_row", 0)
            inputColumn = it.getInt("key_column", 0)
        }
        viewModel = ViewModelProvider(this)[SecondViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SecondFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val manager = GridLayoutManager(requireActivity(), inputColumn)
        binding.rvRandom.layoutManager = manager
        adapter = GridAdapter(requireActivity(), inputRow, inputColumn)
        binding.rvRandom.adapter = GridAdapter(requireActivity(), inputRow, inputColumn)
        binding.rvRandom.adapter = adapter

        viewModel.getRandomNumberLiveData().observe(viewLifecycleOwner) {
            adapter.setItemText(it.index)
            disableButton()
            setButtonEnable(it.x - 1)

            viewModel.stopUpdates()
        }

        addConfirmButton(inputColumn)
        viewModel.startUpdates(inputRow, inputColumn)

    }

    private fun addConfirmButton(buttonNumber: Int) {
        for (i in 0 until buttonNumber) {
            val btns = Button(requireActivity()).apply {
                isEnabled = false
                text = requireActivity().getString(R.string.confirm)
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
            params.weight = 1f
            btns.layoutParams = params

            btns.setOnClickListener(this)
            btns.backgroundTintList = requireActivity().getColorStateList(R.color.button_selector)
            binding.llConfirmBtns.addView(btns)
        }
    }

    private fun disableButton() {
        val btn = binding.llConfirmBtns[lastCoordinateIndex] as Button
        btn.isEnabled = false
    }

    private fun setButtonEnable(btnIndex: Int) {
        lastCoordinateIndex = btnIndex
        val btn = binding.llConfirmBtns[btnIndex] as Button
        btn.isEnabled = true
    }

    fun cleanRandom() {
        adapter.sethideRandomIndex()
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            viewModel.startUpdates(inputRow, inputColumn)
            cleanRandom()
            disableButton()
        }
    }
}