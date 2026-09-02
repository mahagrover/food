package com.example.foodapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.ItemFoodBinding
import java.util.Locale

class FoodAdapter(
    private val items: List<FoodItem>,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvEmoji.text = item.emoji
            tvName.text = item.name
            tvDescription.text = item.description
            tvPrice.text = String.format(Locale.US, "$%.2f", item.price)
            tvQuantity.text = item.quantity.toString()

            btnMinus.setOnClickListener {
                if (item.quantity > 0) {
                    item.quantity -= 1
                    tvQuantity.text = item.quantity.toString()
                    onQuantityChanged()
                }
            }

            btnPlus.setOnClickListener {
                item.quantity += 1
                tvQuantity.text = item.quantity.toString()
                onQuantityChanged()
            }
        }
    }
}
