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
import androidx.lifecycle.Observer
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.NamedItem
import net.merayen.kitchentimer.fragments.common.SelectableList
import net.merayen.kitchentimer.viewmodels.ItemSetupTabViewModel

class ItemSetupTab : Fragment(), SelectableList.Handler, ItemInstanceListFragment.Handler {
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

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        if (childFragment is SelectableList) {
            if (childFragment.id == R.id.itemList) {
                viewModel.getItems().observe(viewLifecycleOwner, Observer {
                    childFragment.applyData(it)
                })
            }
        }
    }

    override fun onClick(item: NamedItem) {
        val itemInstanceEdit = childFragmentManager.findFragmentById(R.id.itemInstanceList) as ItemInstanceListFragment
        itemInstanceEdit.showForItem(item.id)

        val view = view ?: return
        val itemEdit = view.findViewById<FrameLayout>(R.id.itemEdit)
        itemEdit.removeAllViews()
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