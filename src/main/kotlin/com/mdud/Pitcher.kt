package com.mdud

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

object Pitcher {

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> createInstance(kClass: KClass<T>): T {
        return when(kClass) {
            Byte::class -> 0b0.toByte() as T
            Char::class -> '0' as T
            Short::class -> 0 as T
            Int::class -> 0 as T
            Float::class -> 0f as T
            Long::class -> 0 as T
            Double::class -> 0 as T
            else -> {
                val parametersInstances = kClass.primaryConstructor?.parameters?.map { createInstance(it.type.jvmErasure) }
                        ?.toTypedArray() ?: arrayOf()
                kClass.primaryConstructor?.call(*parametersInstances)
                        ?: throw RuntimeException("Cannot instantiate class ${kClass.simpleName}")
            }
        }
    }

    fun <T : Any, R : Any>checkClassEquality(actual : KClass<T>, expected : KClass<R> ) : Boolean {
        return actual == expected
    }
}