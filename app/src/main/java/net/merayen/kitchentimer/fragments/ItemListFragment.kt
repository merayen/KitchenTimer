package net.merayen.kitchentimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_item_list.view.*
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.viewmodels.ItemListViewModel

private const val ARG_PARAM1 = "param1"

class ItemListFragment : Fragment() {
    private var param1: String? = null
    private val viewModel by viewModels<ItemListViewModel>()

    private var items: List<Item> = ArrayList()

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            viewModel.get().observe(viewLifecycleOwner, Observer {
                items = it
                println("Loaded data: ${items.size}")
                notifyDataSetChanged()
            })

            return MyViewHolder(
                // Instantiates the fragment_task_list layout xml for each item in the list
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.fragment_item_list_list_item, parent, false)
            )
        }

        override fun getItemCount(): Int {
            println("Got size: ${items.size}")
            return items.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            println("MyAdapter position: $position")
            println(holder.view.itemList)

            val data = items[position]

            holder.view.findViewById<TextView>(R.id.itemName).text = data.name

            with(holder) {
                // TODO
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val itemList = view.findViewById<RecyclerView>(R.id.itemList)

        with(itemList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MyAdapter()
        }

        viewModel.get().observe(viewLifecycleOwner, Observer {
            items = it
            itemList.adapter!!.notifyDataSetChanged()
        })

        return view
    }

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