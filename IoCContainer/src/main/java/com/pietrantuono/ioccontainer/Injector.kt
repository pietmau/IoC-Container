package com.pietrantuono.ioccontainer


import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

class Injector(val providers: Providers) {

    fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) {
        providers.addProvider(clazz, provider)
    }

    inline fun <reified T> get(): T = providers.providerForClass<T>().get(T::class.java, this)

    inline fun <T> get(clazz: Class<T>) = providers.providerForClass(clazz).get(clazz, this)

    fun inject(obj: Any) {
        for (prop in obj::class.memberProperties) {
            val javaField = prop.javaField
            javaField ?: return
            javaField.annotations?.find { it is Inject } ?: return
            if (prop is KMutableProperty<*>) {
                val type = javaField.type
                injectByType(type, prop, obj)
            }
        }
    }

    //TODO throw exception if it finds many constructors
    inline fun <reified T : Any> scan(clazz: KClass<T>) {
        val constructors = clazz.constructors
        val constructor = constructors
            .map { it to it.annotations }
            .filter { (_, annotations) -> annotations.isNotEmpty() }
            .find { (_, annotations) -> annotations.find { it is Inject } != null }
            ?.first
        constructor ?: return
        providers.addProvider(clazz.java, ReflectiveProvider(constructor))
    }

    private fun injectByType(type: Class<*>, prop: KMutableProperty<*>, obj: Any) {
        val value = getByType(type)
        prop.setter.call(obj, value)
    }

    @SuppressWarnings("unchecked")
    private fun getByType(type: Class<*>): Any? {
        val provider = providers.providerForClass(type) as? Provider<Any>
        return provider?.get(Any::class.java, this)
    }

    companion object {
        val injector = Injector(Providers())

        fun <T> addProvider(clazz: Class<T>, provider: Provider<T>) =
            injector.addProvider(clazz, provider)

        inline fun <reified T> get(): T = injector.get()

        fun inject(obj: Any) {
            injector.inject(obj)
        }

        inline fun <reified T : Any> scan(clazz: KClass<T>) {
            injector.scan(clazz)
        }
    }
}

inline fun <reified T> fieldInject(): Lazy<T> = lazy { Injector.get<T>() }
