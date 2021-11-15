package softing.ubah4ukdev.testingrxjava.presenter.details

import softing.ubah4ukdev.testingrxjava.presenter.PresenterContract

/**
 *   Project: TestingRXJava
 *
 *   Package: softing.ubah4ukdev.testingrxjava.presenter.details
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
internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}