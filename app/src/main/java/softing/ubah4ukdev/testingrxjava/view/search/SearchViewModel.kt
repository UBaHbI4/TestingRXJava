package softing.ubah4ukdev.testingrxjava.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import softing.ubah4ukdev.testingrxjava.view.search.MainActivity.Companion.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory
import softing.ubah4ukdev.testingrxjava.model.SearchResponse
import softing.ubah4ukdev.testingrxjava.presenter.RepositoryContract
import softing.ubah4ukdev.testingrxjava.presenter.SchedulerProvider
import softing.ubah4ukdev.testingrxjava.presenter.search.SearchSchedulerProvider
import softing.ubah4ukdev.testingrxjava.repository.GitHubApi
import softing.ubah4ukdev.testingrxjava.repository.GitHubRepository
import softing.ubah4ukdev.testingrxjava.view.ScreenState

/**
 *   Project: TestingRXJava
 *
 *   Package: softing.ubah4ukdev.testingrxjava.view.search
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
class SearchViewModel(
    private val repository: RepositoryContract = GitHubRepository(
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubApi::class.java)
    ),
    private val appSchedulerProvider: SchedulerProvider = SearchSchedulerProvider()
) : ViewModel() {

    private val _liveData = MutableLiveData<ScreenState>()
    private val liveData: LiveData<ScreenState> = _liveData
    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })


    fun subscribeToLiveData() = liveData

//    fun searchGitHub(searchQuery: String) {
//        val compositeDisposable = CompositeDisposable()
//        compositeDisposable.add(
//            repository.searchGithub(searchQuery)
//                .subscribeOn(appSchedulerProvider.io())
//                .observeOn(appSchedulerProvider.ui())
//                .doOnSubscribe { _liveData.value = ScreenState.Loading }
//                .subscribeWith(object : DisposableObserver<SearchResponse>() {
//
//                    override fun onNext(searchResponse: SearchResponse) {
//                        val searchResults = searchResponse.searchResults
//                        val totalCount = searchResponse.totalCount
//                        if (searchResults != null && totalCount != null) {
//                            _liveData.value = ScreenState.Working(searchResponse)
//                        } else {
//                            _liveData.value =
//                                ScreenState.Error(Throwable("Search results or total count are null"))
//                        }
//                    }
//
//                    override fun onError(e: Throwable) {
//                        _liveData.value =
//                            ScreenState.Error(
//                                Throwable(
//                                    e.message ?: "Response is null or unsuccessful"
//                                )
//                            )
//                    }
//
//                    override fun onComplete() {}
//                }
//                )
//        )
//    }

    fun searchGitHub(searchQuery: String) {
        _liveData.value = ScreenState.Loading
        viewModelCoroutineScope.launch {
            val searchResponse = repository.searchGithubAsync(searchQuery)
            val searchResults = searchResponse.searchResults
            val totalCount = searchResponse.totalCount
            if (searchResults != null && totalCount != null) {
                _liveData.value = ScreenState.Working(searchResponse)
            } else {
                _liveData.value =
                    ScreenState.Error(Throwable("Search results or total count are null"))
            }
        }
    }

    private fun handleError(error: Throwable) {
        _liveData.value =
            ScreenState.Error(
                Throwable(
                    error.message ?: "Response is null or unsuccessful"
                )
            )

    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

}