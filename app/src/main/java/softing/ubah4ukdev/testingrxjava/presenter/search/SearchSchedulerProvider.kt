package softing.ubah4ukdev.testingrxjava.presenter.search

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import softing.ubah4ukdev.testingrxjava.presenter.SchedulerProvider

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
internal class SearchSchedulerProvider : SchedulerProvider {

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }
}