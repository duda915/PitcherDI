package com.mdud

import kotlin.test.Test
import kotlin.test.assertTrue

class PitcherInterfaceRegister {



    @Test
    fun pour_AddInterfaceBindingAndInstantiate_ShouldReturnProperInterfaceImplementation() {
        Pitcher.mix(ITest::class, TestImpl::class)
        val instance = Pitcher.pour(ITest::class)
        val result = instance is TestImpl
        assertTrue(result)
    }

    @Test
    fun pour_AddTwoInterfaces_ShouldContainAndReturnOnlyLastAddedImplementation() {
        Pitcher.mix(ITest::class, TestImpl::class)
        Pitcher.mix(ITest::class, TestImpl2::class)
        val instance = Pitcher.pour(ITest::class)
        val result = instance is TestImpl2
        assertTrue(result)
    }

    @Test(expected = RuntimeException::class)
    fun pour_TryInstantiateWithoutBinding_ShouldThrowRuntimeException() {
        Pitcher.purify(ITest::class)
        Pitcher.pour(ITest::class)
    }

    @Test(expected = RuntimeException::class)
    fun mix_TryAddInterfaceBindingToClassNotImplementingThisInterface_ShouldThrowRuntimeException() {
        Pitcher.mix(ITest::class, NotImplementationClass::class)
    }

    @Test(expected = RuntimeException::class)
    fun mix_TryAddNotInterfaceBinding_ShouldThrowRuntimeException() {
        Pitcher.mix(TestImpl2::class, TestImpl::class)
    }

    @Test(expected = RuntimeException::class)
    fun mix_TryAddInterfaceToInterfaceBinding_ShouldThrowRuntimeException() {
        Pitcher.mix(ITest::class, ITest::class)
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

class NotImplementationClass