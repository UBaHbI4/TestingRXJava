package softing.ubah4ukdev.testingrxjava.presenter.search

import softing.ubah4ukdev.testingrxjava.presenter.PresenterContract

/**
 *   Project: TestingRXJava
 *
 *   Package: softing.ubah4ukdev.testingrxjava.presenter.search
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
internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
}