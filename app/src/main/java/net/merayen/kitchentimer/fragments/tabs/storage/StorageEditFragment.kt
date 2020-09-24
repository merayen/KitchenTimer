package net.merayen.kitchentimer.fragments.tabs.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.merayen.kitchentimer.R

class StorageEditFragment : Fragment() {
    companion object {
        fun newInstance() = StorageTab()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.storage_edit_fragment, container, false)
    }
}