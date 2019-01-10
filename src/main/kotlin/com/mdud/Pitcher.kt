package com.mdud

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

object Pitcher {

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getInstance(kClass: KClass<T>): T {
        return when(kClass) {
            Char::class -> '0' as T
            Int::class -> 0 as T
            else -> kClass.createInstance()
        }
    }

    fun <T : Any, R : Any>checkClassEquality(actual : KClass<T>, expected : KClass<R> ) : Boolean {
        return actual == expected
    }
}