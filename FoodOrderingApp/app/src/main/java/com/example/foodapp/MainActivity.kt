package com.example.foodapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val foodItems = mutableListOf(
        FoodItem(1, "Margherita Pizza", "Classic cheese & tomato", 8.99, "🍕"),
        FoodItem(2, "Cheeseburger", "Beef patty, cheddar, lettuce", 6.49, "🍔"),
        FoodItem(3, "Sushi Platter", "Assorted nigiri & rolls", 12.99, "🍣"),
        FoodItem(4, "Caesar Salad", "Romaine, parmesan, croutons", 5.99, "🥗"),
        FoodItem(5, "Spaghetti Bolognese", "Slow-cooked beef ragu", 9.49, "🍝"),
        FoodItem(6, "Taco Trio", "Beef, chicken & veggie tacos", 7.99, "🌮"),
        FoodItem(7, "Chocolate Cake", "Rich double chocolate slice", 4.99, "🍰"),
        FoodItem(8, "Iced Coffee", "Cold brew over ice", 3.49, "🧋")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val adapter = FoodAdapter(foodItems) { updateTotal() }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnPlaceOrder.setOnClickListener { placeOrder() }

        updateTotal()
    }

    private fun updateTotal() {
        val total = foodItems.sumOf { it.price * it.quantity }
        val count = foodItems.sumOf { it.quantity }
        binding.tvTotal.text = String.format(Locale.US, "Total: $%.2f", total)
        binding.tvItemCount.text = "$count item(s) in cart"
        binding.btnPlaceOrder.isEnabled = count > 0
    }

    private fun placeOrder() {
        val orderedItems = foodItems.filter { it.quantity > 0 }
        val total = orderedItems.sumOf { it.price * it.quantity }
        val summary = orderedItems.joinToString("\n") {
            "${it.emoji} ${it.name} x${it.quantity} — ${String.format(Locale.US, "$%.2f", it.price * it.quantity)}"
        }

        AlertDialog.Builder(this)
            .setTitle("Order Confirmed \uD83C\uDF89")
            .setMessage("$summary\n\n${String.format(Locale.US, "Total: $%.2f", total)}")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                foodItems.forEach { it.quantity = 0 }
                binding.recyclerView.adapter?.notifyDataSetChanged()
                updateTotal()
                Toast.makeText(this, "Thanks for your order!", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
