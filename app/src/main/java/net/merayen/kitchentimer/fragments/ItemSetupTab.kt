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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.data.ItemInstance
import net.merayen.kitchentimer.data.NamedItem
import net.merayen.kitchentimer.fragments.common.SelectableList
import net.merayen.kitchentimer.viewmodels.ItemSetupTabViewModel

class ItemSetupTab : Fragment(), SelectableList.Handler {
    companion object {
        fun newInstance() = ItemSetupTab()
    }

    private val viewModel by viewModels<ItemSetupTabViewModel>()

    private var itemInstancesObserver: Observer<LiveData<List<ItemInstance>>>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_setup_tab_fragment, container, false)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        if (childFragment is SelectableList) {
            when (childFragment.id) {
                R.id.itemList -> {
                    viewModel.getItems().observe(viewLifecycleOwner, Observer {
                        childFragment.applyData(it)
                    })
                }
                R.id.itemInstanceList -> {
                    // We load item instance list only when one has been selected in the item list, so no loading here
                }
            }
        }
    }

    override fun onClick(item: NamedItem) {
        val view = view ?: return
        val itemEdit = view.findViewById<FrameLayout>(R.id.itemEdit)

        when (item) {
            is Item -> {
                val itemInstanceList = (childFragmentManager.findFragmentById(R.id.itemInstanceList) as SelectableList)
                itemInstanceList.unselect()
                viewModel.getItemInstances(viewLifecycleOwner, item.id, Observer {
                    itemInstanceList.applyData(it)
                })

                itemEdit.removeAllViews()
            }
            is ItemInstance -> {
                childFragmentManager.commit {
                    itemEdit.removeAllViews()
                    add(
                        R.id.itemEdit,
                        ItemInstanceEditFragment::class.java,
                        bundleOf("itemInstanceId" to item.id)
                    )
                }
            }
        }
    }
}