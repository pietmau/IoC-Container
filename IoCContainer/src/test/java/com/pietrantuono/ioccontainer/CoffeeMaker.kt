package com.pietrantuono.ioccontainer


class CoffeeMaker(grinder: CoffeeGrinder)

class CoffeeGrinder

class CoffeeGrinderProvider : Provider<CoffeeGrinder> {
    override fun get(clazz: Class<CoffeeGrinder>, injector: Injector) = CoffeeGrinder()
}

class CoffeeMakerProvider : Provider<CoffeeMaker> {

    override fun get(clazz: Class<CoffeeMaker>, injector: Injector): CoffeeMaker {
        var grinder = injector.get<CoffeeGrinder>()
        return CoffeeMaker(grinder)
    }
}