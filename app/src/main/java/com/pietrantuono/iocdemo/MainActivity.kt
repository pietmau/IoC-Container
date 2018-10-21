package com.pietrantuono.iocdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pietrantuono.ioccontainer.Injector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.inject(this)
        presenter.run()
    }
}
