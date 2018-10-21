package com.pietrantuono.iocdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pietrantuono.ioccontainer.Injector

class MainActivity : AppCompatActivity() {
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val injector = setupInjector()
        presenter = injector.get()
    }

    private fun setupInjector(): Injector {
        val injector = Injector()
        injector.addProvider(Model::class.java, ModelProvider())
        injector.addProvider(Presenter::class.java, PresenterProvider())
        return injector
    }
}
