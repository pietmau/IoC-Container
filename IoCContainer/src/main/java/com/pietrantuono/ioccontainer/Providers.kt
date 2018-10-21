package com.pietrantuono.ioccontainer


class Providers {
    val map = hashMapOf<Class<*>, Provider<*>>()

    fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) {
        map.put(clazz, provider)
    }

    fun addProviderInternal(clazz: Class<out Any>, provider: Provider<Any>) {
        map.put(clazz, provider)
    }

    inline fun <reified T> providerForClass() =
        map[T::class.java] as? Provider<T>
            ?: throw MissingProviderException(T::class.java.simpleName)

    inline fun <T> providerForClass(clazz: Class<T>) =
        map[clazz] as? Provider<T> ?: throw MissingProviderException(clazz.simpleName)

    class MissingProviderException(simpleName: String) : Exception("Missing provider  " + simpleName)
}