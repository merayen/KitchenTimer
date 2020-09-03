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
import net.merayen.kitchentimer.viewmodels.ItemInstanceEditViewModel

private const val ITEM_INSTANCE_ID = "itemInstanceId"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemInstanceEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemInstanceEditFragment : Fragment() {
    private var itemInstanceId = 0

    private val viewModel by viewModels<ItemInstanceEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemInstanceId = it.getInt(ITEM_INSTANCE_ID)
        } ?: return

        viewModel.get(itemInstanceId).observe(this, Observer {
            if (it != null) {
                view?.findViewById<EditText>(R.id.itemName)?.setText(it.name)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_instance_edit, container, false)
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
        fun newInstance(itemInstanceId: Int) =
            ItemInstanceEditFragment().apply {
                arguments = Bundle().apply {
                    putInt(ITEM_INSTANCE_ID, itemInstanceId)
                }
            }
    }
}