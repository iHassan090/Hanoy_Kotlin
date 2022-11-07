package com.hassan.hanoykotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hassan.hanoykotlin.databinding.ItemBinding
import com.hassan.hanoykotlin.models.Item

class ItemAdapter(items: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var mItems: ArrayList<Item> = items

    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(mItems[position]) {
                binding.name.text = this.name
                binding.text1.text = this.firstText
                binding.text2.text = this.secondText
            }
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}