package com.pietrantuono.ioccontainer


import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class InjectorTest {
    @Mock
    lateinit var providers: Providers
    @InjectMocks
    lateinit var injector: Injector

    @Test
    fun when_setProvider_then_setsProviders() {
        //WHEN
        val (clazz, provider) = setUpProvider()
        //THEN
        verify(providers).addProvider(clazz, provider)
    }

    private fun setUpProvider(): Pair<Class<CoffeeMaker>, CoffeeMakerProvider> {
        val clazz = CoffeeMaker::class.java
        val provider = CoffeeMakerProvider()
        injector.addProvider(clazz, provider)
        return Pair(clazz, provider)
    }
}