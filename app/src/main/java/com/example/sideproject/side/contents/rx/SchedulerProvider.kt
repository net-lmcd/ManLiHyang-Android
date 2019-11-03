package com.example.sideproject.side.contents.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun main(): Scheduler

    fun compute(): Scheduler
}