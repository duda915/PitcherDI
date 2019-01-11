package com.mdud

import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

object PitcherUtils {
    fun checkClassEquality(actual : KClass<*>, expected : KClass<*>) : Boolean {
        return actual == expected
    }

    private fun checkAndThrowIfClassIsNotConcrete(concrete: KClass<*>) {
        if (concrete.isAbstract) {
            throw RuntimeException("${concrete.simpleName} is abstract")
        }
    }

    private fun <T : Any> checkAndThrowIfClassIsNotAbstract(abstractClass: KClass<T>) {
        if (!abstractClass.isAbstract) {
            throw RuntimeException("${abstractClass.simpleName} is not abstract")
        }
    }

    internal fun checkConcreteToAbstractEquality(concrete: KClass<*>, abstract: KClass<*>) : Boolean {
        checkAndThrowIfClassIsNotConcrete(concrete)
        checkAndThrowIfClassIsNotAbstract(abstract)
        return concrete.isSubclassOf(abstract)
    }
}

