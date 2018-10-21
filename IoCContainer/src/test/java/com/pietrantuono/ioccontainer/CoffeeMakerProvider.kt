package com.pietrantuono.ioccontainer


class CoffeeMakerProvider : Provider<CoffeeMaker> {

    override fun get(clazz: Class<CoffeeMaker>, injector: Injector) = CoffeeMaker()
}