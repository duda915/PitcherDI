package com.mdud

import kotlin.math.exp
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

object Pitcher {

    val interfaceHashMap = mutableMapOf<KClass<*>, KClass<*>>()

    @Suppress("UNCHECKED_CAST")
    fun <T:Any> createInstance(kClass : KClass<T>) : T{
        return instanceBuilder(checkIfClassImplementationExist(kClass)) as T
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> instanceBuilder(kClass: KClass<T>): T {
        return when(kClass) {
            Byte::class -> 0b0.toByte() as T
            Char::class -> '0' as T
            Short::class -> 0 as T
            Int::class -> 0 as T
            Float::class -> 0f as T
            Long::class -> 0 as T
            Double::class -> 0 as T
            else -> instantiateFromConstructor(kClass)
        }
    }

    private fun <T : Any> instantiateFromConstructor(kClass: KClass<T>): T {
        val parametersInstances = kClass.primaryConstructor?.parameters?.map { instanceBuilder(it.type.jvmErasure) }
                ?.toTypedArray() ?: arrayOf()
        return kClass.primaryConstructor?.call(*parametersInstances)
                ?: throw RuntimeException("Cannot instantiate class ${kClass.simpleName}")
    }

    fun checkClassEquality(actual : KClass<*>, expected : KClass<*> ) : Boolean {
        return actual == expected
    }


    fun <T : Any, R : Any> addImplementation(interfaceClass: KClass<T>, implementationClass: KClass<R>) {
        interfaceHashMap[interfaceClass] = implementationClass
    }

    private  fun <T : Any> checkIfClassImplementationExist(classToCheck : KClass<T>) : KClass<*> {
        return interfaceHashMap[classToCheck] ?: classToCheck
    }


}