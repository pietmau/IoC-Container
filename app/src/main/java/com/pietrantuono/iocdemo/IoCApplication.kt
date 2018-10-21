package com.pietrantuono.iocdemo

import android.app.Application
import com.pietrantuono.ioccontainer.Injector


class IoCApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.scan(Presenter::class)
        Injector.scan(Api::class)
        Injector.scan(Model::class)
    }
}