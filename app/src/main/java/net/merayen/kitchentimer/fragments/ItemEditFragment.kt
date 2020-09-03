package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.ItemEditViewModel

private const val ITEM_ID = "itemId"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemEditFragment : Fragment() {
    private var itemId = 0

    private val viewModel by viewModels<ItemEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt(ITEM_ID)
        } ?: return

        viewModel.get(itemId).observe(this, Observer {
            if (it != null) {
                view?.findViewById<EditText>(R.id.itemName)?.setText(it.name)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val view = requireView()

        view.findViewById<EditText>(R.id.itemName).addTextChangedListener {
            val item = viewModel.get()
            if (item != null) {
                item.name = it.toString()
                viewModel.save(item)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(itemId: Int) =
            ItemEditFragment().apply {
                arguments = Bundle().apply {
                    println("ItemId: $itemId")
                    putInt(ITEM_ID, itemId)
                }
            }
    }
}