package net.merayen.kitchentimer.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Typeface
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
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.viewmodels.ItemListViewModel

private const val ARG_PARAM1 = "param1"

class ItemListFragment : Fragment() {
    interface Handler {
        fun onClick(itemId: Int)
    }

    private var parentHandler: Handler? = null

    private var param1: String? = null
    private val viewModel by viewModels<ItemListViewModel>()

    private var items: ArrayList<Item> = ArrayList()
    private var itemsById: Map<Int, Item> = HashMap()
    private val itemLevel = HashMap<Int, Int>()
    private var itemSelected = 0

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                // Instantiates the fragment_task_list layout xml for each item in the list
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.fragment_item_list_list_item, parent, false)
            )
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val data = items[position]

            with(holder.view.findViewById<TextView>(R.id.itemName)) {
                text = data.name
            }

            holder.view.setPadding(itemLevel[data.parent]!! * 10, 0, 0, 0)

            with(holder.itemView) {
                setOnClickListener {
                    parentHandler?.onClick(data.id)
                    itemSelected = data.id
                    notifyDataSetChanged()
                }
            }

            if (data.id == itemSelected)
                holder.view.setBackgroundColor(Color.YELLOW)
            else
                holder.view.setBackgroundColor(Color.TRANSPARENT)
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
            // Recursive sorting for display
            items.clear()
            fun p(parent: Int = 0, level: Int = 0) {
                for (item in it) {
                    if (item.parent == parent) {
                        items.add(item)
                        p(item.id, level + 1)
                    }
                }
            }
            p()

            itemsById = it.map { it.id to it }.toMap()

            itemLevel.clear()
            itemLevel[0] = 0
            for (item in items) {
                if (item.parent !in itemLevel) {
                    var current = item
                    var r = 0
                    while (current.parent != 0) {
                        current = itemsById[current.parent] ?: throw RuntimeException("Should not happen")
                        r++
                    }

                    itemLevel[item.parent] = r
                }
            }

            itemList.adapter!!.notifyDataSetChanged()
        })

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val parentFragment = parentFragment
        if (parentFragment is Handler)
            parentHandler = parentFragment
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