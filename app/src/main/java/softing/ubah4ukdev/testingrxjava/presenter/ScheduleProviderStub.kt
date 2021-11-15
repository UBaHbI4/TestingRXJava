package softing.ubah4ukdev.testingrxjava.presenter

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import softing.ubah4ukdev.testingrxjava.presenter.SchedulerProvider

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
class ScheduleProviderStub : SchedulerProvider {
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }
}