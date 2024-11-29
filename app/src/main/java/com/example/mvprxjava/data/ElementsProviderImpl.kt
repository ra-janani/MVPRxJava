package com.example.mvprxjava.data

import com.example.mvprxjava.model.Element
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class ElementsProviderImpl : ElementsProvider {
    override fun loadElements(): Single<List<Element>> {
        // Simulating a network delay using Single.timer() (3 seconds delay)
        return Single.timer(3, TimeUnit.SECONDS) // Simulate a delay of 3 seconds
            .flatMap {
                // After the delay, return a list of elements (simulate data fetch)
//                val elements = listOf(
//                    Element(id = "1", title = "Element 1"),
//                    Element(id = "2", title = "Element 2"),
//                    Element(id = "3", title = "Element 3")
//                )

                val elements = emptyList<Element>()
                Single.just(elements)  // Return the list of elements
            }
    }
}
