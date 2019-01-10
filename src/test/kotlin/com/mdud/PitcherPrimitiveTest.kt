package com.mdud

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PitcherPrimitiveTest {


    @Test
    fun createInstance_ByteClass_ShouldReturnByteClass() {
        val instance = Pitcher.getInstance(Byte::class)
        val result = Pitcher.checkClassEquality(instance::class, Byte::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_CharClass_ShouldReturnCharClass() {
        val instance = Pitcher.getInstance(Char::class)
        val result = Pitcher.checkClassEquality(instance::class, Char::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_ShortClass_ShouldReturnShortClass() {
        val instance = Pitcher.getInstance(Short::class)
        val result = Pitcher.checkClassEquality(instance::class, Short::class)
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
    fun createInstance_LongClass_ShouldReturnLongClass() {
        val instance = Pitcher.getInstance(Long::class)
        val result = Pitcher.checkClassEquality(instance::class, Long::class)
        assertTrue(result)
    }

    @Test
    fun createInstance_DoubleClass_ShouldReturnDoubleClass() {
        val instance = Pitcher.getInstance(Double::class)
        val result = Pitcher.checkClassEquality(instance::class, Double::class)
        assertTrue(result)
    }
}

