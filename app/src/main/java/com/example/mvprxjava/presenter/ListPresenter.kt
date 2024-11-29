package com.example.mvprxjava.presenter
import android.annotation.SuppressLint
import com.example.mvprxjava.data.ElementsProvider
import com.example.mvprxjava.data.SchedulerFacade
import com.example.mvprxjava.view.ListContract

class ListPresenter(
    private val view: ListContract.View,
    private val elementsProvider: ElementsProvider,
    private val schedulerFacade: SchedulerFacade // This helps manage thread scheduling
) {

    @SuppressLint("CheckResult")
    fun loadElements() {
        view.showLoading() // Show the loading progress
        elementsProvider.loadElements() // Request data from the provider
            .subscribeOn(schedulerFacade.io()) // Perform this on the IO thread
            .observeOn(schedulerFacade.mainThread()) // Observe on the main thread
            .doFinally { view.hideLoading() } // Hide loading once the request is complete
            .subscribe(
                { elements ->
                    if (elements.isEmpty()) {
                        view.showError() // If the list is empty, show an error
                    } else {
                        view.setElements(elements) // Otherwise, show the list
                    }
                },
                { error ->
                    view.showError() // If there's an error, show the error
                }
            )
    }
}

