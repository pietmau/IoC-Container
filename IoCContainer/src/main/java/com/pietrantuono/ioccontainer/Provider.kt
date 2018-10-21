package com.pietrantuono.ioccontainer


interface Provider<T> {
    fun get(clazz: Class<T>, injector: Injector): T
}