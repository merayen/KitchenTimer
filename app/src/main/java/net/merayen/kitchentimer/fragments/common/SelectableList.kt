package net.merayen.kitchentimer.fragments.common

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.NamedItem
import net.merayen.kitchentimer.data.TreeItem
import net.merayen.kitchentimer.fragments.ItemInstanceEditFragment

/**
 * List where one item can be chosen at a time.
 * Made to reduce duplicate code.
 */
class SelectableList : Fragment() {
    interface Handler {
        fun onClick(item: NamedItem)
    }

    private var items: ArrayList<NamedItem> = ArrayList()
    private val itemLevel = HashMap<Int, Int>()
    private var itemsById: Map<Int, NamedItem> = HashMap()
    private var itemSelected = 0

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private var handler: Handler? = null

    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.fragment_selectablelist_item, parent, false)
            )
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = items[position]

            if (item is TreeItem)
                holder.view.setPadding(itemLevel[item.parent]!! * 10, 0, 0, 0)

            with(holder.itemView) {
                setOnClickListener {
                    this@SelectableList.handler?.onClick(item)

                    if (itemSelected != 0)
                        notifyItemChanged(items.indexOfFirst { it.id == itemSelected })

                    itemSelected = item.id
                    notifyItemChanged(position)
                }
            }

            val textViewName = holder.view.findViewById<TextView>(R.id.name)!!

            if (item.id == itemSelected) {
                holder.view.setBackgroundColor(Color.BLACK)
                textViewName.setTextColor(Color.WHITE)
            } else {
                holder.view.setBackgroundColor(Color.TRANSPARENT)
                textViewName.setTextColor(Color.BLACK)
            }

            textViewName.text = item.name
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_selectablelist, container, false)
        val itemList = view.findViewById<RecyclerView>(R.id.list)

        with(itemList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MyAdapter()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parentFragment = parentFragment
        if (parentFragment is Handler)
            handler = parentFragment // Is this good...?
    }

    fun applyData(newItems: List<NamedItem>) {
        if (newItems.map { it.javaClass }.toSet().size > 1)
            throw RuntimeException("Mix of types in data. Not allowed")

        // Recursive sorting for display
        items.clear()
        fun p(parent: Int = 0) {
            for (item in newItems) {
                if (item !is TreeItem) {
                    items.add(item)
                } else if (item.parent == parent) {
                    items.add(item)
                    p(item.id)
                }
            }
        }
        p()

        itemsById = newItems.map { it.id to it }.toMap()

        itemLevel.clear()
        itemLevel[0] = 0
        for (item in items) {
            if (item is TreeItem && item.parent !in itemLevel) {
                var current = item as TreeItem
                var r = 0
                while (current.parent != 0) {
                    current = itemsById[current.parent] as TreeItem
                    r++
                }

                itemLevel[item.parent] = r
            }
        }

        (view as? RecyclerView)?.adapter?.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemInstanceEditFragment().apply {
            arguments = Bundle().apply {}
        }
    }
}