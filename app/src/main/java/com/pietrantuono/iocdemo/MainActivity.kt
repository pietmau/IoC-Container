package com.pietrantuono.iocdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject
import kotlin.reflect.full.declaredMemberProperties

class MainActivity : AppCompatActivity() {
    @set:[Inject]
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val declaredMemberProperties = MainActivity::class.declaredMemberProperties
        ///val ann = declaredMemberProperties.findAnnotation<Inject>()
        for (prop in declaredMemberProperties) {
            val annotatopns = prop.annotations
            fff()
        }

    }

    private fun fff() {


    }
}
