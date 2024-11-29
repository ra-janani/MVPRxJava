package com.example.mvprxjava.presenter

import com.example.mvprxjava.data.ElementsProvider
import com.example.mvprxjava.data.SchedulerFacade
import com.example.mvprxjava.model.Element
import com.example.mvprxjava.view.ListContract
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ListPresenterTest {

    // Mocked dependencies
    private lateinit var view: ListContract.View
    private lateinit var elementsProvider: ElementsProvider
    private lateinit var presenter: ListPresenter

    @Before
    fun setup() {
        // Initialize mocks
        view = mock(ListContract.View::class.java)
        elementsProvider = mock(ElementsProvider::class.java)

        // Initialize presenter with the mock view and elementsProvider
        presenter = ListPresenter(view, elementsProvider, object : SchedulerFacade {
            override fun io() = Schedulers.trampoline() // Use trampoline to run synchronously
            override fun mainThread() = Schedulers.trampoline()
        })
    }

    @Test
    fun `loadElements - should show loading and display elements on success`() {
        // Arrange
        val mockElements = listOf(
            Element(id = "1", title = "Element 1"),
            Element(id = "2", title = "Element 2")
        )
        `when`(elementsProvider.loadElements()).thenReturn(Single.just(mockElements))

        // Act
        presenter.loadElements()

        // Assert
        verify(view).showLoading()
        verify(view).setElements(mockElements)
        verify(view).hideLoading()
        verify(view, never()).showError() // Ensure no error shown
    }

    @Test
    fun `loadElements - should show loading and display error on failure`() {
        // Arrange
        `when`(elementsProvider.loadElements()).thenReturn(Single.error(Exception("Error loading elements")))

        // Act
        presenter.loadElements()

        // Assert
        verify(view).showLoading()
        verify(view).showError()
        verify(view).hideLoading()
        verify(view, never()).setElements(anyList()) // Ensure elements are not set
    }

    @Test
    fun `loadElements - should display error when list is empty`() {
        // Arrange
        val emptyList = emptyList<Element>()
        `when`(elementsProvider.loadElements()).thenReturn(Single.just(emptyList))

        // Act
        presenter.loadElements()

        // Assert
        verify(view).showLoading()
        verify(view).showError() // Show error for empty list
        verify(view).hideLoading()
        verify(view, never()).setElements(anyList()) // Ensure elements are not set
    }
}
