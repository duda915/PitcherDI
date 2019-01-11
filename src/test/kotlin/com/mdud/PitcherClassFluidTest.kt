package com.mdud

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PitcherClassFluidTest {
    @Test
    fun pour_AddFormula_ShouldReturnClassFromFormula() {
        Pitcher.addFormula(FactoryClass::aBean)
        val instance = Pitcher.pour(A::class)
        val result = instance.int == 10
        assertTrue(result)
    }

    @Test
    fun pour_AddFormula_ShouldReturnClassFromFormula_SecondMethod() {
        Pitcher.addFormula { A(15) }
        val instance = Pitcher.pour(A::class)
        val result = instance.int == 15
        assertTrue(result)
    }
    @Test
    fun pour_RemoveFormula_ShouldInstantiateClassWithDefaultValues() {
        Pitcher.addFormula(FactoryClass::bBean)
        Pitcher.removeFormulaForClass(B::class)
        val instance = Pitcher.pour(B::class)
        val result = instance.int == 0
        assertTrue(result)
    }

}

object FactoryClass {
    fun aBean() : A {
        return A(10)
    }

    fun bBean() : B {
        return B (15, 15.0)
    }
}

class A (val int: Int)
class B (val int : Int, var double : Double)
