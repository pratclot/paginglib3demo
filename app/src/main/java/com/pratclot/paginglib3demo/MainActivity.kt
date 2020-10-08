package com.pratclot.paginglib3demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState.Loading
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratclot.paginglib3demo.data.CatWrap
import com.pratclot.paginglib3demo.recycler.adapters.CatImageAdapter
import com.pratclot.paginglib3demo.recycler.adapters.CatWrapAdapter
import com.pratclot.paginglib3demo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val pagingAdapter1 = CatImageAdapter()
    private val pagingAdapter2 = CatWrapAdapter()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView.apply {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navCatImages -> setupRecyclerView(pagingAdapter1)
                    R.id.navCatWraps -> setupRecyclerView(pagingAdapter2)
                    else -> false
                }
            }
        }
    }

    private fun setupRecyclerView(pagingAdapter: PagingDataAdapter<*, *>): Boolean {
        recyclerView.apply {
            adapter = pagingAdapter
            layoutManager = LinearLayoutManager(context)
        }

        pagingAdapter.addLoadStateListener { loadStates: CombinedLoadStates ->
            progressBar.setVisibility(if (loadStates.refresh is Loading) View.VISIBLE else View.GONE)
        }

        disposable += when (pagingAdapter) {
            is CatImageAdapter ->
                viewModel.obs
                    .subscribe(
                        {
                            pagingAdapter.submitData(lifecycle, it)
                        },
                        {
                            showToast("Error is: $it")
                        }
                    )
            is CatWrapAdapter ->
                viewModel.obs
                    .map {
                        it.mapSync {
                            val catId = it.id
                            CatWrap.from(it) { showToast(catId) }
                        }
                    }
                    .subscribe(
                        {
                            pagingAdapter.submitData(lifecycle, it)
                        },
                        {
                            showToast("Error is: $it")
                        }
                    )
            else -> CompositeDisposable()
        }

        return true
    }

    private fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}