package com.mdud

import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

object Pitcher : PitcherInterface{

    val abstractToConcreteHashMap = mutableMapOf<KClass<*>, KClass<*>>()
    val instantiateFunctionsHashMap = mutableMapOf<KClass<*>, Function<*>>()

    @Suppress("UNCHECKED_CAST")
    override fun <T:Any> pour(kClass : KClass<T>) : T{
        return instanceBuilder(checkIfClassImplementationExist(kClass)) as T
    }

    override fun <T : Any, R : Any> mix(abstractClass: KClass<T>, implementationClass: KClass<R>) {
        if(checkConcreteToAbstractEquality(implementationClass, abstractClass)) {
            abstractToConcreteHashMap[abstractClass] = implementationClass
        } else {
            throw RuntimeException("${implementationClass.simpleName} is not implementation of ${abstractClass.simpleName}")
        }
        abstractToConcreteHashMap[abstractClass] = implementationClass
    }

    override fun <T : Any> purify(abstractClass : KClass<T>) {
        abstractToConcreteHashMap.remove(abstractClass)
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

    private fun checkConcreteToAbstractEquality(concrete: KClass<*>, abstract: KClass<*>) : Boolean {
        checkAndThrowIfClassIsNotConcrete(concrete)
        checkAndThrowIfClassIsNotAbstract(abstract)
        return concrete.isSubclassOf(abstract)
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

    private  fun <T : Any> checkIfClassImplementationExist(classToCheck : KClass<T>) : KClass<*> {
        return abstractToConcreteHashMap[classToCheck] ?: classToCheck
    }


}