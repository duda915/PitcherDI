package com.mdud

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

object Pitcher : PitcherInterface{

    private val abstractToConcreteHashMap = mutableMapOf<KClass<*>, KClass<*>>()
    private val instantiateFunctionsHashMap = mutableMapOf<KClass<*>, () -> Any>()

    init {

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T:Any> pour(kClass : KClass<T>) : T{
        val classToPour = getImplementationClass(kClass)
        return if(checkForFormulaExistence(classToPour)) {
            formulaBuilder(classToPour) as T
        } else {
            instanceBuilder(classToPour) as T
        }
    }

    override fun <T : Any, R : Any> mix(abstractClass: KClass<T>, implementationClass: KClass<R>) {
        if(PitcherUtils.checkConcreteToAbstractEquality(implementationClass, abstractClass)) {
            abstractToConcreteHashMap[abstractClass] = implementationClass
        } else {
            throw RuntimeException("${implementationClass.simpleName} is not implementation of ${abstractClass.simpleName}")
        }
        abstractToConcreteHashMap[abstractClass] = implementationClass
    }

    override fun <T : Any> purify(abstractClass : KClass<T>) {
        abstractToConcreteHashMap.remove(abstractClass)
    }

    override fun <T : Any> addFormula(formula: () -> T) {
        instantiateFunctionsHashMap[formula.invoke()::class] = formula
    }

    override fun <T : Any> removeFormulaForClass(kClass: KClass<T>) {
        instantiateFunctionsHashMap.remove(kClass)
    }

    private fun <T : Any> checkForFormulaExistence(kClass: KClass<T>) : Boolean{
        return instantiateFunctionsHashMap.containsKey(kClass)
    }

    private fun <T : Any> formulaBuilder(kClass: KClass<T>) : Any? {
        return instantiateFunctionsHashMap[kClass]?.invoke()
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

    private  fun <T : Any> getImplementationClass(classToCheck : KClass<T>) : KClass<*> {
        return abstractToConcreteHashMap[classToCheck] ?: classToCheck
    }


}

