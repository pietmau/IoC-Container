package com.pietrantuono.ioccontainer

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class ProvidersTest {
    private lateinit var providers: Providers

    @Before
    fun setUp() {
        providers = Providers()
    }

    @Test
    fun when_setsProvider_then_getsProvider() {
        //WHEN
        val provider = CoffeeMakerProvider()
        providers.addProvider(CoffeeMaker::class.java, provider)
        //THEN
        Assert.assertEquals(provider, providers.providerForClass<CoffeeMaker>())
    }
}