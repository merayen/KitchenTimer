package net.merayen.kitchentimer.fragments

import android.content.Context
import android.graphics.Color
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
import net.merayen.kitchentimer.data.ItemInstance
import net.merayen.kitchentimer.viewmodels.ItemInstanceListViewModel

private const val ITEM_ID = "itemId"

class ItemInstanceListFragment : Fragment() {
    interface Handler {
        fun onClickItemInstanceListItem(itemInstanceId: Int)
    }

    private var parentHandler: Handler? = null

    private val viewModel by viewModels<ItemInstanceListViewModel>()

    private var itemId = 0
    private var items: List<ItemInstance> = ArrayList()
    private var itemsById: Map<Int, ItemInstance> = HashMap()
    private var itemSelected = 0

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                // Instantiates the fragment_task_list layout xml for each item in the list
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.fragment_item_instance_list_item, parent, false)
            )
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val data = items[position]

            with(holder.view.findViewById<TextView>(R.id.itemName)) {
                text = data.name
            }

            with(holder.itemView) {
                setOnClickListener {
                    parentHandler?.onClickItemInstanceListItem(data.id)
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
            itemId = it.getInt("itemId")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_instance_list, container, false)
        val itemList = view.findViewById<RecyclerView>(R.id.itemInstanceListRecyclerView)

        with(itemList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MyAdapter()
        }

        if (itemId != 0)
            showForItem(itemId)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val parentFragment = parentFragment
        if (parentFragment is Handler)
            parentHandler = parentFragment
    }

    fun showForItem(itemId: Int) {
        this.itemId = itemId
        load()
    }

    private fun load() {
        val view = view ?: return
        val itemList = view.findViewById<RecyclerView>(R.id.itemInstanceListRecyclerView)

        viewModel.getByItem(itemId).observe(viewLifecycleOwner, Observer {
            items = it
            itemsById = it.map { it.id to it }.toMap()
            itemList.adapter!!.notifyDataSetChanged()
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ItemInstanceEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ITEM_ID, param1)
                }
            }
    }
}