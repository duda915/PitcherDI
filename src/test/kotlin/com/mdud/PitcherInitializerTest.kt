package com.mdud

import kotlin.test.Test
import kotlin.test.assertTrue

class PitcherInitializerTest {

    @Test
    fun init_CheckRecipeAnnotationScanner_ShouldReturnClassFromAnnotatedRecipe() {
        PitcherInitializer.init("com.mdud")
        val instance = Pitcher.pour(C::class)
        val result = instance.int == 20
        assertTrue(result)
    }
}

class C(val int: Int)

@PitcherRecipe
fun createC() : C {
    return C(20)
}