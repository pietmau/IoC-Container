package com.pietrantuono.iocdemo

import android.app.Application
import com.pietrantuono.ioccontainer.*


class IoCApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.addProvider(Model::class.java, ModelProvider())
        Injector.addProvider(Presenter::class.java, PresenterProvider())
    }
}