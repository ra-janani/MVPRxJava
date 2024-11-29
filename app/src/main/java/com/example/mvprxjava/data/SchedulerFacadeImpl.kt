
package com.example.mvprxjava.data

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerFacadeImpl : SchedulerFacade {
    override fun io(): Scheduler = Schedulers.io()
    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
}
