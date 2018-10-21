package com.pietrantuono.ioccontainer

class Injector(val providers: Providers) {

    fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) {
        providers.addProvider(clazz, provider)
    }

    inline fun <reified T> get(): T = providers.providerForClass<T>().get(T::class.java, this)


    companion object {
        val injector = Injector(Providers())

        fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) =
            injector.addProvider(clazz, provider)

        inline fun <reified T> get(): T = injector.get()
    }
}

inline fun <reified T> fieldInject(): Lazy<T> = lazy { Injector.get<T>() }
