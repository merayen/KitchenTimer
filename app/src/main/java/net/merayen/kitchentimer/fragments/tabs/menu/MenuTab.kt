package net.merayen.kitchentimer.fragments.tabs.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.merayen.kitchentimer.databinding.MenuTabFragmentBinding

class MenuTab : Fragment() {
	private lateinit var binding: MenuTabFragmentBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = MenuTabFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}
}