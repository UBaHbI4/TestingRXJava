package softing.ubah4ukdev.testingrxjava.presenter

import io.reactivex.Observable
import softing.ubah4ukdev.testingrxjava.model.SearchResponse
import softing.ubah4ukdev.testingrxjava.repository.RepositoryCallback

/**
 *   Project: TestingRXJava
 *
 *   Package: softing.ubah4ukdev.testingrxjava.presenter
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
interface RepositoryContract {

    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )

    fun searchGithub(
        query: String
    ): Observable<SearchResponse>
}