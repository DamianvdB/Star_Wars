package com.dvdb.starwars.presentation.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dvdb.starwars.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

private val TAG = SplashScreenActivity::class.java.simpleName

class SplashScreenActivity : AppCompatActivity(), KodeinAware, LifecycleOwner {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: SplashScreenViewModelFactory by instance()
    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        progress_bar.visibility = View.VISIBLE
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this,
            Observer<SplashScreenViewModelState> { state ->
                when (state) {
                    is SplashScreenViewModelState.Success -> {
                        Log.d(TAG, "Number of film list items loaded: ${state.listItems.count()}")
                        state.listItems
                    }
                    is SplashScreenViewModelState.Error -> {
                        Log.e(TAG, "Could not load film list items", state.throwable)
                    }
                }
                progress_bar.visibility = View.GONE
            }
        )
    }
}