package net.merayen.kitchentimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.ItemInstanceProperty

/**
 * Denne skal vise en liste over alle properties på en ItemInstance. Redigering
 * foregår inne i selve listen?
 */
class ItemInstancePropertyListFragment : Fragment() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                // Instantiates the fragment_task_list layout xml for each item in the list
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.fragment_item_instance_property_list_item, parent, false)
            )
        }

        override fun getItemCount() = ItemInstanceProperty.Type.values().size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val data = ItemInstanceProperty.Type.values()[position]

            with(holder.view.findViewById<TextView>(R.id.property_name)) {
                text = data.name
            }

            with(holder.view.findViewById<TextView>(R.id.unit)) {
                text = data.unit
            }

            with(holder.itemView) {
                setOnClickListener {
                    notifyDataSetChanged()
                }
            }

            with(holder.view.findViewById<EditText>(R.id.value)) {
                setText("123")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_item_instance_property_list, container, false)

        val propertyList = view.findViewById<RecyclerView>(R.id.property_list)

        with(propertyList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MyAdapter()
        }

        return view
    }
}