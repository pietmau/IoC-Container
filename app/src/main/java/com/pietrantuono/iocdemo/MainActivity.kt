package com.pietrantuono.iocdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pietrantuono.ioccontainer.fieldInject

class MainActivity : AppCompatActivity() {
    val presenter: Presenter by fieldInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.run()
    }
}
