package net.merayen.kitchentimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_item_list.view.*
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.ItemListViewModel

private const val ARG_PARAM1 = "param1"

class ItemListFragment : Fragment() {
    private var param1: String? = null
    private val viewModel by viewModels<ItemListViewModel>()

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
            // Instantiates the fragment_task_list layout xml for each item in the list
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_item_list, parent, false)
        )

        override fun getItemCount(): Int {
            return 4
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            println("MyAdapter position: $position")
            println(holder.view.itemList)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        viewModel.get().observe(this, Observer {

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_item_list, container, false)

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ItemEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}