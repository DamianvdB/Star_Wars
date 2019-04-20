package com.dvdb.starwars.presentation.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dvdb.starwars.R
import com.dvdb.starwars.presentation.util.NavigationManager
import com.dvdb.starwars.presentation.util.SnackbarFactory
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

private val TAG = SplashScreenActivity::class.java.simpleName

class SplashScreenActivity : AppCompatActivity(), KodeinAware, LifecycleOwner {
    override val kodein: Kodein by kodein()
    private val viewModelFactory: SplashScreenViewModelFactory by instance()
    private val navigationManagerFactory: NavigationManager.Factory by instance()
    private val navigationManager: NavigationManager by lazy { navigationManagerFactory.create(supportFragmentManager) }
    private val snackbarFactory: SnackbarFactory by instance()
    private val viewModel: SplashScreenViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        observeViewModel()
        showProgressIndicator()
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, Observer<SplashScreenViewModelState> { state ->
            when (state) {
                is SplashScreenViewModelState.Loading -> {
                    progress_bar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                }
                is SplashScreenViewModelState.Success -> {
                    Log.d(TAG, "Number of film list items loaded: ${state.listItems.count()}")
                    startFilmActivityAndFinishCurrent()
                }
                is SplashScreenViewModelState.Error -> {
                    Log.e(TAG, "Could not load film list items", state.throwable)
                    snackbarFactory.getSnackbarWithAction(root_layout,
                        getString(R.string.generic_loading_data_error),
                        actionText = getString(R.string.retry),
                        actionOnClickListener = View.OnClickListener {
                            viewModel.updateFilmItemList()
                        }).show()
                }
            }
        })
    }

    private fun startFilmActivityAndFinishCurrent() {
        startActivity(navigationManager.getFilmActivityIntent())
        finish()
    }

    private fun showProgressIndicator(){
        progress_bar.visibility = View.VISIBLE
    }
}