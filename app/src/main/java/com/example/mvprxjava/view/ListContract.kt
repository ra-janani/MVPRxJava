package com.example.mvprxjava.view

import com.example.mvprxjava.model.Element

interface ListContract {
    interface View {
        fun setElements(elements: List<Element>)
        fun showLoading()
        fun hideLoading()
        fun showError()
    }
}
