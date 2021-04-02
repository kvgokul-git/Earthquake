package com.gklabs.earthquake.ui.earthquake.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gklabs.earthquake.R
import com.gklabs.earthquake.data.model.EarthQuakesResponse
import com.gklabs.earthquake.databinding.EarthquakeItemBinding
import com.gklabs.earthquake.ui.earthquake.EarthQuakesFragmentDirections

class EarthQuakeAdapter :
    ListAdapter<EarthQuakesResponse.Earthquake, EarthQuakeAdapter.EarthQuakeViewHolder>(
        EarthQuakeComparator()
    ) {

    override fun onBindViewHolder(holder: EarthQuakeViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
        if (currentItem.magnitude >= 8){
            holder.itemView.setBackgroundColor(Color.CYAN)
        }
        holder.itemView.setOnClickListener { view ->
            val action =
                EarthQuakesFragmentDirections.actionEarthQuakesFragmentToEarthQuakeDetailsFragment(
                    currentItem.lat.toString(), currentItem.lng.toString()
                )
            view.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeViewHolder {
        val binding =
            EarthquakeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EarthQuakeViewHolder(binding)
    }

    class EarthQuakeViewHolder(private val binding: EarthquakeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(earthquake: EarthQuakesResponse.Earthquake) {
            binding.apply {
                depth.text = earthquake.depth.toString()
                eqid.text = earthquake.eqid
                magnitude.text = earthquake.magnitude.toString()
            }
        }
    }

    class EarthQuakeComparator : DiffUtil.ItemCallback<EarthQuakesResponse.Earthquake>() {
        override fun areItemsTheSame(
            oldItem: EarthQuakesResponse.Earthquake,
            newItem: EarthQuakesResponse.Earthquake
        ) = oldItem.eqid == newItem.eqid

        override fun areContentsTheSame(
            oldItem: EarthQuakesResponse.Earthquake,
            newItem: EarthQuakesResponse.Earthquake
        ) = oldItem == newItem
    }
}