package com.example.mvprxjava.data


import com.example.mvprxjava.model.Element
import io.reactivex.Single

interface ElementsProvider {
    fun loadElements(): Single<List<Element>>
}
