package com.dvdb.starwars.presentation.film

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dvdb.starwars.R
import com.dvdb.starwars.presentation.film.list.FilmListFragment
import kotlinx.android.synthetic.main.activity_film.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

private val FRAGMENT_LIST_TAG = FilmListFragment::class.java.simpleName

class FilmActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        setSupportActionBarAndTitle()
        addFilmListFragment()
    }

    private fun setSupportActionBarAndTitle() {
        setSupportActionBar(toolbar)
        setTitle(R.string.films)
    }

    private fun addFilmListFragment() {
        supportFragmentManager.beginTransaction()
            .add(frame_layout.id, FilmListFragment(), FRAGMENT_LIST_TAG)
            .commit()
    }
}
