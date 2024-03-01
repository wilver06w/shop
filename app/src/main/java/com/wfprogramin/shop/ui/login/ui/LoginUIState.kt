package com.wfprogramin.shop.ui.login.ui


sealed class LoginUIState  {
    abstract val model: Model

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LoginUIState) return false

        return model == other.model
    }

    override fun hashCode(): Int {
        return model.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(model=$model)"
    }

    data class InitialState(override val model: Model) : LoginUIState()

    data class LoadingState(override val model: Model): LoginUIState()

    data class ChangedData(override val model: Model): LoginUIState()

    data class SuccessState(override val model: Model): LoginUIState()

    data class ErrorState(override val model: Model): LoginUIState()
}

data class Model(val email: String = "", val password: String = "", val loginEnabled: Boolean = false) {

    fun copy(email: String? = this.email, password: String? = this.password, loginEnabled: Boolean? = this.loginEnabled): Model {
        return Model(email ?: this.email, password ?: this.password, loginEnabled ?: this.loginEnabled)
    }

    companion object {
        fun withDefaults(): Model {
            return Model("") // Provide default value for itemYugiOh
        }
    }
}
