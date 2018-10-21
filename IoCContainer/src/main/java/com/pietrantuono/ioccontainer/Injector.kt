package com.pietrantuono.ioccontainer


import javax.inject.Inject
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

class Injector(val providers: Providers) {

    fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) {
        providers.addProvider(clazz, provider)
    }

    inline fun <reified T> get(): T = providers.providerForClass<T>().get(T::class.java, this)

    fun inject(obj: Any) {
        for (prop in obj::class.memberProperties) {
            val javaField = prop.javaField
            javaField ?: return
            val annotations = javaField.annotations
            annotations?.find { it is Inject } ?: return
            if (prop is KMutableProperty<*>) {
                val type = javaField.type
                @SuppressWarnings("unchecked")
                val provider = providers.providerForClass(type) as? Provider<Any>
                val value = provider?.get(Any::class.java, this)
                prop.setter.call(obj, value)
            }
        }
    }

    companion object {
        val injector = Injector(Providers())

        fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) =
            injector.addProvider(clazz, provider)

        inline fun <reified T> get(): T = injector.get()

        fun inject(obj: Any) {
            injector.inject(obj)
        }
    }
}

inline fun <reified T> fieldInject(): Lazy<T> = lazy { Injector.get<T>() }
