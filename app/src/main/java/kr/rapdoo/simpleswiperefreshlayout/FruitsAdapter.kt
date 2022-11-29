package kr.rapdoo.simpleswiperefreshlayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kr.rapdoo.simpleswiperefreshlayout.databinding.ListItemBinding

class FruitsAdapter(private val fruits: ArrayList<Fruits>) : Adapter<FruitsAdapter.FruitViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FruitViewHolder(ListItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.bind(fruits[position])
    }

    override fun getItemCount(): Int = fruits.size

    inner class FruitViewHolder(private val binding: ListItemBinding) : ViewHolder(binding.root) {
        fun bind(fruit: Fruits) {
            binding.textKor.text = fruit.kor_fruit
            binding.textEng.text = fruit.eng_fruit
        }
    }
}