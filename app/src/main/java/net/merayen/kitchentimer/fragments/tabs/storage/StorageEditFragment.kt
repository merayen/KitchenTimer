package net.merayen.kitchentimer.fragments.tabs.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import net.merayen.kitchentimer.R

class StorageEditFragment : Fragment() {
    companion object {
        fun newInstance(id: Int?, parent: Int?) = StorageTab().apply {
            arguments = Bundle().apply {
                if (id != null) putInt("id", id)
                if (parent != null) putInt("parent", parent)
            }
        }
    }

    private val viewModel by viewModels<StorageEditViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.storage_edit_fragment, container, false)

        val id = arguments!!["id"] as Int?
        if (id != null) {
            viewModel.get(id).observe(viewLifecycleOwner, Observer {
                view.findViewById<EditText>(R.id.name).setText(it.name)
            })
        }
        return view
    }
}