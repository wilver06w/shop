package com.wfprogramin.shop.ui.splash.ui

import com.wfprogramin.shop.ui.login.ui.Model


sealed class SplashUIState  {
    abstract val modelSplash: ModelSplash

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SplashUIState ) return false

        return modelSplash == other.modelSplash
    }

    override fun hashCode(): Int {
        return modelSplash.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(model=$modelSplash)"
    }

    data class InitialState(override val modelSplash: ModelSplash) : SplashUIState()

    data class SuccessState(override val modelSplash: ModelSplash): SplashUIState()
}

data class ModelSplash(val value: Int = 0) {

    fun copy(value: Int? = this.value): ModelSplash {
        return ModelSplash(value ?: this.value)
    }

    companion object {
        fun withDefaults(): ModelSplash {
            return ModelSplash(0) // Provide default value for itemYugiOh
        }
    }
}
