package com.pietrantuono.ioccontainer

import kotlin.reflect.KFunction

class ReflectiveProvider<T>(private val constructor: KFunction<T>) : Provider<T> {

    override fun get(clazz: Class<T>, injector: Injector): T {
        val params = constructor.parameters
            .map { it.type.javaClass }
            .map { injector.get(it) }
            .toTypedArray()
        return constructor.call(*params)
    }

}
