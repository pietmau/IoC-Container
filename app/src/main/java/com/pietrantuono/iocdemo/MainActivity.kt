package com.pietrantuono.iocdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pietrantuono.ioccontainer.Injector
import com.pietrantuono.ioccontainer.Presenter
import com.pietrantuono.ioccontainer.Providers
import javax.inject.Inject
import kotlin.reflect.jvm.javaType

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.inject(this)
        presenter.run()
        Injector(Providers()).scan(Moka::class)
    }

    private fun scan() {
        val constructor = Moka::class.constructors
            .map { it to it.annotations }
            .filter { (_, annotations) -> annotations.isNotEmpty() }
            .find { (_, annotations) -> annotations.find { it is Inject } != null }
            ?.first//TODO throw exception if finds multiple
        constructor ?: return
        val params = constructor.parameters.map { it.type.javaType }
        val instance = constructor.call(CoffeBeans(), Water())
    }

    private fun fff() {


    }


}
