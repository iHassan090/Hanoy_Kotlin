package com.hassan.hanoykotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hassan.hanoykotlin.databinding.TableBinding
import com.hassan.hanoykotlin.models.Table

class TableAdapter(private var mTables: ArrayList<Table>) :
    RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TableBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(mTables[position]) {
                val mAdapter = ItemAdapter(this.items)

                binding.recyclerView.adapter = mAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(
                    holder.itemView.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return mTables.size
    }
}