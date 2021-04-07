package com.gklabs.earthquake.ui.earthquake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gklabs.earthquake.R
import com.gklabs.earthquake.databinding.EarthQuakesFragmentBinding
import com.gklabs.earthquake.ui.earthquake.adapter.EarthQuakeAdapter
import com.gklabs.earthquake.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EarthQuakesFragment : Fragment() {

    private val viewModel: EarthQuakesViewModel by viewModels()
    private lateinit var binding: EarthQuakesFragmentBinding
    private lateinit var earthQuakeAdapter: EarthQuakeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.earth_quakes_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        earthQuakeAdapter = EarthQuakeAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EarthQuakesFragmentBinding.bind(view)
        binding.apply {
            recyclerviewEarthquakes.apply {
                adapter = earthQuakeAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            viewModel.earthQuakes.observe(viewLifecycleOwner) { result ->
                earthQuakeAdapter.submitList(result.data)
                progressBar.isVisible = result is Resource.Loading
                textViewError.isVisible = result is Resource.Error
                textViewError.text = result.error?.localizedMessage
            }
        }
    }
}


