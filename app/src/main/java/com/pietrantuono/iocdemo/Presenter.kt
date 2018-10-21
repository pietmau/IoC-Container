package com.pietrantuono.iocdemo

import com.pietrantuono.ioccontainer.Injector
import com.pietrantuono.ioccontainer.Provider
import javax.inject.Inject


class Presenter @Inject constructor(private val model: Model, private val api: Api) {
    fun run() {}
}

class Model @Inject constructor()

class Api @Inject constructor()

class ModelProvider : Provider<Model> {
    override fun get(clazz: Class<Model>, injector: Injector) =
        Model()
}

class PresenterProvider : Provider<Presenter> {
    override fun get(clazz: Class<Presenter>, injector: Injector): Presenter {
        val model = injector.get<Model>()
        val api = injector.get<Api>()
        return Presenter(model, api)
    }
}