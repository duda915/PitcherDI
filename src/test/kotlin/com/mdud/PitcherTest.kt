package com.mdud

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PitcherTest {

    @Test
    fun createInstance_CharClass_ShouldReturnCharClass() {
        val instance = Pitcher.getInstance(Char::class)
        val result = Pitcher.checkClassEquality(instance::class, Char::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_IntClass_ShouldReturnIntClass() {
        val instance = Pitcher.getInstance(Int::class)
        val result = Pitcher.checkClassEquality(instance::class, Int::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_FloatClass_ShouldReturnFloatClass() {
        val instance = Pitcher.getInstance(Float::class)
        val result = Pitcher.checkClassEquality(instance::class, Float::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_NoArgsConstructor_ShouldReturnSameKClass() {
        val instance = Pitcher.getInstance(NoArgsConstructorClass::class)
        val result = Pitcher.checkClassEquality(instance::class, NoArgsConstructorClass::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_StringClass_ShouldReturnStringClass() {
        val instance = Pitcher.getInstance(String::class)
        val result = Pitcher.checkClassEquality(instance::class, String::class)
        assertTrue(result)
    }

}

class NoArgsConstructorClass