package com.pietrantuono.ioccontainer


class Presenter(private val model: Model) {
    fun run() {}
}

class Model

class ModelProvider : Provider<Model> {

    override fun get(clazz: Class<Model>, injector: Injector) =
        Model()
}

class PresenterProvider : Provider<Presenter> {

    override fun get(clazz: Class<Presenter>, injector: Injector): Presenter {
        val model = injector.get<Model>()
        return Presenter(model)
    }
}