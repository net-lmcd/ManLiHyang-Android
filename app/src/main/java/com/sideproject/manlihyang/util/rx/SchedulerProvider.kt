package com.sideproject.manlihyang.util.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun main(): Scheduler

    fun compute(): Scheduler
}