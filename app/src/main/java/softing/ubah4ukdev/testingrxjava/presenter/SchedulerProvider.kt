package softing.ubah4ukdev.testingrxjava.presenter

import io.reactivex.Scheduler

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
interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}