package net.merayen.kitchentimer.fragments.tabs.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.merayen.kitchentimer.databinding.OrderTabFragmentBinding

class OrderTab : Fragment() {
	private lateinit var binding: OrderTabFragmentBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = OrderTabFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}
}