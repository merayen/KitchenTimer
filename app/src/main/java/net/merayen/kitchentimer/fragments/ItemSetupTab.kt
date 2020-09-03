package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.findFragment
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
        val view = view ?: return
        val itemEdit = view.findViewById<FrameLayout>(R.id.itemEdit) // TODO should not edit the Item, but the ItemInstance

        //val itemInstanceEdit = view.findViewById<FrameLayout>(R.id.itemInstanceList).getChildAt(0)

        val itemInstanceEdit = childFragmentManager.findFragmentById(R.id.itemInstanceList) as ItemInstanceListFragment
        itemInstanceEdit.showForItem(itemId)

        childFragmentManager.commit {
            itemEdit.removeAllViews()
            add(R.id.itemEdit, ItemEditFragment::class.java, bundleOf("itemId" to itemId))
        }
    }
}