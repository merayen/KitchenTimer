package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.ItemSetupTabViewModel

class ItemSetupTab : Fragment(), ItemListFragment.Handler {
    companion object {
        fun newInstance() = ItemSetupTab()
    }

    private val viewModel by viewModels<ItemSetupTabViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_setup_tab_fragment, container, false)
    }

    override fun onClick(itemId: Int) {
        view?.findViewById<FragmentContainerView>(R.id.itemEdit)
    }
}
