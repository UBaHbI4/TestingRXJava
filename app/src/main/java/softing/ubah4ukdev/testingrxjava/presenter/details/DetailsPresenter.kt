package softing.ubah4ukdev.testingrxjava.presenter.details

import softing.ubah4ukdev.testingrxjava.view.details.ViewDetailsContract

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
internal class DetailsPresenter internal constructor(
    private val viewContract: ViewDetailsContract,
    private var count: Int = 0
) : PresenterDetailsContract {

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContract.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract.setCount(count)
    }
}