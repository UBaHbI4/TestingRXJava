package softing.ubah4ukdev.testingrxjava

import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import softing.ubah4ukdev.testingrxjava.model.SearchResponse
import softing.ubah4ukdev.testingrxjava.presenter.ScheduleProviderStub
import softing.ubah4ukdev.testingrxjava.presenter.search.SearchPresenter
import softing.ubah4ukdev.testingrxjava.repository.GitHubRepository
import softing.ubah4ukdev.testingrxjava.view.search.ViewSearchContract

/**
 *   Project: TestingRXJava
 *
 *   Package: softing.ubah4ukdev.testingrxjava
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.11.16
 *
 *   v1.0
 */
class SearchPresenterTestRx {

    private lateinit var presenter: SearchPresenter

    @Mock
    private lateinit var repository: GitHubRepository

    @Mock
    private lateinit var viewContract: ViewSearchContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(viewContract, repository, ScheduleProviderStub())
    }


    @Test
    fun searchPresenter_ErrorResponseTest() {
        Mockito.`when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.error(Throwable(ERROR_TEXT))
        )

        presenter.searchGitHub(SEARCH_QUERY)
        Mockito.verify(viewContract, Mockito.times(1)).displayError("error")
    }

    @Test
    fun searchPresenter_SuccessResponseTest() {
        Mockito.`when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.just(
                SearchResponse(
                    42,
                    listOf()
                )
            )
        )

        presenter.searchGitHub(SEARCH_QUERY)
        Mockito.verify(viewContract, Mockito.times(1)).displaySearchResults(listOf(), 42)
    }

    @Test
    fun searchPresenter_ResponseTotalCountIsNull() {
        Mockito.`when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.just(
                SearchResponse(
                    null,
                    listOf()
                )
            )
        )

        presenter.searchGitHub(SEARCH_QUERY)
        Mockito.verify(viewContract, Mockito.times(1)).displayError("Search results or total count are null")
    }

    @Test
    fun searchPresenter_ResponseTotalCountOne() {
        Mockito.`when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.just(
                SearchResponse(
                    1,
                    listOf()
                )
            )
        )

        presenter.searchGitHub(SEARCH_QUERY)
        Mockito.verify(repository, Mockito.times(1)).searchGithub(SEARCH_QUERY)
    }

    @Test
    fun searchPresenter_TotalCountIsNullInOrderMethodsRun() {
        Mockito.`when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.just(
                SearchResponse(
                    null,
                    listOf()
                )
            )
        )

        presenter.searchGitHub(SEARCH_QUERY)

        val inOrder = Mockito.inOrder(viewContract)
        inOrder.verify(viewContract).displayLoading(true)
        inOrder.verify(viewContract).displayError("Search results or total count are null")
        inOrder.verify(viewContract).displayLoading(false)
    }

    companion object {
        private const val SEARCH_QUERY = "some query"
        private const val ERROR_TEXT = "error"
    }
}