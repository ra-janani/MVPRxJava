package com.example.mvprxjava.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.mvprxjava.data.ElementsProviderImpl
import com.example.mvprxjava.data.SchedulerFacade
import com.example.mvprxjava.data.SchedulerFacadeImpl
import com.example.mvprxjava.model.Element
import com.example.mvprxjava.presenter.ListPresenter
import com.example.mvprxjava.ui.theme.MvprxjavaTheme

class MyActivity : AppCompatActivity(), ListContract.View {

    // State variables for UI
    private var elementsList by mutableStateOf<List<Element>>(emptyList())  // Renamed the state variable
    private var isLoading by mutableStateOf(false)
    private var errorOccurred by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Call ListUI composable to display the UI

            MvprxjavaTheme {
                ListUI(elementsList, isLoading, errorOccurred, this)
            }


            // Initialize the presenter and load data
            val schedulerFacade = SchedulerFacadeImpl()
            val elementsProvider = ElementsProviderImpl() // Replace with actual data provider
            val presenter = ListPresenter(this, elementsProvider, schedulerFacade)
            showLoading()
            presenter.loadElements()
        }
    }

    // ListContract.View methods (used by presenter to update the UI)
    override fun setElements(elements: List<Element>) {
       this.elementsList = elements
        errorOccurred = false
        hideLoading()
    }

    override fun showLoading() {
        isLoading = true
    }

    override fun hideLoading() {
        isLoading = false
    }

    override fun showError() {
        errorOccurred = true
    }
}

