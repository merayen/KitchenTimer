package net.merayen.kitchentimer.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.merayen.kitchentimer.viewmodels.ItemListViewModel

class ItemListFragment : Fragment() {
    private var param1: String? = null
    private val viewModel by viewModels<ItemListViewModel>()


}