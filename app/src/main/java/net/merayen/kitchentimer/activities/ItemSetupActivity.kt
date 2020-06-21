package net.merayen.kitchentimer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_fullscreen.*
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.fragments.RunningTimersListFragment
import net.merayen.kitchentimer.fragments.dummy.DummyContent

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ItemSetupActivity : AppCompatActivity(), RunningTimersListFragment.OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        supportActionBar?.hide()
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        println("YOU TAPPED $item") // TODO should be captured by a sub fragment showing the running timers?
    }
}
