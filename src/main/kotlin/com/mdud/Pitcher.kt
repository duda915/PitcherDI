package com.mdud

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

object Pitcher {

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getInstance(kClass: KClass<T>): T {
        return when(kClass) {
            Byte::class -> 0 as T
            Char::class -> '0' as T
            Short::class -> 0 as T
            Int::class -> 0 as T
            Float::class -> 0f as T
            Long::class -> 0 as T
            Double::class -> 0 as T
            else -> kClass.createInstance()
        }
    }

    fun <T : Any, R : Any>checkClassEquality(actual : KClass<T>, expected : KClass<R> ) : Boolean {
        return actual == expected
    }
}