package com.pietrantuono.ioccontainer

class Injector(val providers: Providers = Providers()) {

    fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) {
        providers.addProvider(clazz, provider)
    }

    inline fun <reified T> get(): T = providers.providerForClass<T>().get(T::class.java, this)

}
