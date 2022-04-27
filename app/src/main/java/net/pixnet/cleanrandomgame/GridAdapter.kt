package net.pixnet.cleanrandomgame

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.pixnet.cleanrandomgame.databinding.GridItemLayoutBinding

class GridAdapter(private val context: Context, private val row: Int, private val column: Int) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {
    var coordinateIndex: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(context, GridItemLayoutBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == coordinateIndex) {
            println("onBindViewHolder: ${position}")
            holder.setItemText()
        }
    }

    override fun getItemCount(): Int {
        return row * column
    }

    fun setItemText(index: Int) {
        this.coordinateIndex = index
        notifyDataSetChanged()
    }


    inner class ViewHolder(
        private val context: Context,
        private val binding: GridItemLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setItemText() {
            println("position: ${position}")
            println("---------------------")
            binding.tvRandomText.text = context.getString(R.string.random)
        }
    }


}