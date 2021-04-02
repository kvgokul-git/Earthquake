package com.gklabs.earthquake.ui.earthquakedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gklabs.earthquake.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EarthQuakesDetailsFragment : Fragment(), OnMapReadyCallback {

    private var lat: Double = 0.0
    private var lng: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_earth_quakes_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: EarthQuakesDetailsFragmentArgs by navArgs()
        lat = args.lat.toDouble()
        lng = args.lng.toDouble()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val earthQuakeLocation = LatLng(lat, lng)
        googleMap?.addMarker(MarkerOptions().position(earthQuakeLocation))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(earthQuakeLocation))
    }
}