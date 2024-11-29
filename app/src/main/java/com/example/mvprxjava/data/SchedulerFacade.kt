// SchedulerFacade.kt
package com.example.mvprxjava.data

import io.reactivex.Scheduler

interface SchedulerFacade {
    fun io(): Scheduler
    fun mainThread(): Scheduler
}
