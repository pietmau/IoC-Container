# IoC Contanier

## Contents
This repository contains two modules:
* the library itself;
* an Android demo application.

## How to use it

Create the providers:
```
class CoffeeMakerProvider : Provider<CoffeeMaker> {

    override fun get(clazz: Class<CoffeeMaker>, injector: Injector): CoffeeMaker {
        var grinder = injector.get<CoffeeGrinder>()
        return CoffeeMaker(grinder)
    }
}

class CoffeeGrinderProvider : Provider<CoffeeGrinder> {

    override fun get(clazz: Class<CoffeeGrinder>, injector: Injector) = CoffeeGrinder()
}

```

Add the providers to the Injector:
```
 val injector = Injector(Providers())
 injector.addProvider(CoffeeMaker::class.java, CoffeeMakerProvider())
 injector.addProvider(CoffeeGrinder::class.java, CoffeeGrinderProvider())
```

Get the needed dependency:
```
 val coffeeMaker = injector.get<CoffeeMaker>()
```

## Field injection for Android

Ideally I would implement constructor injection and use annotations.

For Android Activities, Fragments, Services, etc. is not possible to use constructor injection,

so I implemented a very simple field injection (also here I would use annotations):

first add the Providers (maybe in the Application Class)
```
Injector.addProvider(Model::class.java, ModelProvider())
Injector.addProvider(Presenter::class.java, PresenterProvider())
```

then in the Activity you can do:
```
class MainActivity : AppCompatActivity() {
    val presenter: Presenter by fieldInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        presenter.run()
    }
}
```
this will lazily initialize the presenter.

