package com.airtelx.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.airtelx.app.data.models.AddressList
import com.airtelx.app.data.models.SearchResultResponseModel
import com.airtelx.app.data.models.Suggestions
import com.airtelx.app.data.remote.Resource
import com.airtelx.app.data.remote.Status
import com.airtelx.app.repo.Repository
import com.airtelx.app.viewmodel.MainViewModel
import com.airtelx.app.viewmodel.ViewModelFactory
import com.arlib.floatingsearchview.FloatingSearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewMode: MainViewModel
    lateinit var dataRepository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRepository = Repository()

        val viewModeFact = ViewModelFactory(dataRepository)

        viewMode = ViewModelProviders.of(this, viewModeFact).get(MainViewModel::class.java)

        floating_search_view.setOnQueryChangeListener { oldQuery, newQuery ->
            loadData(newQuery, "")

        }

    }

    private fun loadData(queryString: String, city: String) {

        viewMode.getMatchingAddress(queryString, city).observe(this, Observer {

            when (it.status) {

                Status.ERROR -> {
                    Toast.makeText(applicationContext, "Network error", Toast.LENGTH_LONG).show()

                }

                Status.SUCCESS -> {

                    floating_search_view.swapSuggestions(getSuggestions(it))

                }

                Status.LOADING -> {
                    //progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun getSuggestions(
        it: Resource<SearchResultResponseModel>
    ): ArrayList<Suggestions> {
        val suggestions = ArrayList<Suggestions>()
        val list = it.data?.data?.addressList
        list?.forEach {
            val s = Suggestions(it.addressString)
            suggestions.add(s)
        }
        return suggestions
    }
}
