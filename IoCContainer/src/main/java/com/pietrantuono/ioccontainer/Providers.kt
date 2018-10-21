package com.pietrantuono.ioccontainer


class Providers {
    val map = hashMapOf<Class<*>, Provider<*>>()

    fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) {
        map.put(clazz, provider)
    }

    inline fun <reified T> providerForClass() =
        map[T::class.java] as Provider<T> ?: throw MissingProviderException()

    class MissingProviderException : Exception("Missing provider")
}