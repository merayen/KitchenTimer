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
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.viewmodels.ItemEditViewModel

/* TODO: Rename parameter arguments, choose names that match the fragment initialization parameters, e.g.
    ARG_ITEM_NUMBER */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemEditFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel by viewModels<ItemEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        (context )

        //viewModel.load(1)?.observe(this, Observer<Item> {
        //    println("Got this name: ${it.name}")
        //    val textView = view?.findViewById<EditText>(R.id.itemName)?.setText(it.name)
        //})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val textView = view?.findViewById<EditText>(R.id.itemName)

        viewModel.load(1) // TODO remove

        //textView?.setText(viewModel.item.value?.name)

        //textView?.addTextChangedListener {
        //    val item = viewModel.item.value ?: throw RuntimeException("Should not happen?")
        //    item.name = it.toString()
        //}
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemEditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}