package com.mdud

import kotlin.test.Test
import kotlin.test.assertTrue

class PitcherInterfaceRegister {

    @Test
    fun createInstance_TestInterfaceInstantiation_ShouldReturnInterfaceInstance() {
        Pitcher.addImplementation(ITest::class, TestImpl::class)
        val instance = Pitcher.createInstance(ITest::class)
        val result = Pitcher.checkClassEquality(instance::class, ITest::class)
        assertTrue(instance is ITest)
    }
    

}

interface ITest {
    fun test()
}

class TestImpl (val int: Int) : ITest {
    override fun test() { }
    fun internal() {}
}

class TestImpl2 : ITest {
    override fun test() {
    }

    fun internal() {}
}