package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.ItemSetupTabViewModel

class ItemSetupTab : Fragment(), ItemListFragment.Handler, ItemInstanceListFragment.Handler {
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

    override fun onClickItemListItem(itemId: Int) {

        val itemInstanceEdit = childFragmentManager.findFragmentById(R.id.itemInstanceList) as ItemInstanceListFragment
        itemInstanceEdit.showForItem(itemId)

    }

    override fun onClickItemInstanceListItem(itemInstanceId: Int) {
        val view = view ?: return
        val itemEdit = view.findViewById<FrameLayout>(R.id.itemEdit)

        childFragmentManager.commit {
            itemEdit.removeAllViews()
            add(R.id.itemEdit, ItemInstanceEditFragment::class.java, bundleOf("itemInstanceId" to itemInstanceId))
        }
    }
}