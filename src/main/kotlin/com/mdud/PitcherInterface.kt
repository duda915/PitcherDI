package com.mdud

import kotlin.reflect.KClass

interface PitcherInterface {
    fun <T : Any> pour(kClass : KClass<T>) : T
    fun <T : Any, R : Any> mix(abstractClass: KClass<T>, implementationClass: KClass<R>)
    fun <T : Any> purify(abstractClass : KClass<T>)
    fun <T : Any> addFormula(formula: () -> T)
    fun <T : Any> removeFormulaForClass(kClass: KClass<T>)
}