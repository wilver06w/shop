package com.wfprogramin.shop.ui.splash.ui


sealed class SplashUIState  {
    abstract val model: Model

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SplashUIState ) return false

        return model == other.model
    }

    override fun hashCode(): Int {
        return model.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(model=$model)"
    }

    data class InitialState(override val model: Model) : SplashUIState()

    data class SuccessState(override val model: Model): SplashUIState()
}

data class Model(val itemYugiOh: Int = 0) {

//    val itemYugiOh: Int? = null

//    final Int? itemYugiOh/

    // ... same code from previous response ...

    fun copy(itemYugiOh: Int? = this.itemYugiOh): Model {
        return Model(itemYugiOh ?: this.itemYugiOh)
    }

    companion object {
        fun withDefaults(): Model {
            return Model(0) // Provide default value for itemYugiOh
        }
    }
}
