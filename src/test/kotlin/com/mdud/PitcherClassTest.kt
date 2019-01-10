package com.mdud

import kotlin.test.Test
import kotlin.test.assertTrue

class PitcherClassTest {

    @Test
    fun createInstance_NoArgsConstructorClass_ShouldReturnSameClass() {
        val instance = Pitcher.createInstance(NoArgsConstructorClass::class)
        val result = Pitcher.checkClassEquality(instance::class, NoArgsConstructorClass::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_PrimitiveTypesConstructor_ShouldReturnSameClass() {
        val instance = Pitcher.createInstance(PrimitiveTypesConstructor::class)
        val result = Pitcher.checkClassEquality(instance::class, PrimitiveTypesConstructor::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_CompositeClassConstructor_ShouldReturnSameClass() {
        val instance = Pitcher.createInstance(CompositeClassConstructor::class)
        val result = Pitcher.checkClassEquality(instance::class, CompositeClassConstructor::class)
        assertTrue(result)
    }
}

class NoArgsConstructorClass
class PrimitiveTypesConstructor(val int: Int, val byte: Byte, val string: String)
class CompositeClassConstructor(val int: Int, val noArgsConstructorClass : NoArgsConstructorClass)
