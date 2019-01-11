package com.mdud

import org.reflections.Reflections
import org.reflections.scanners.FieldAnnotationsScanner
import org.reflections.scanners.MethodAnnotationsScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.reflect.jvm.kotlinFunction
import kotlin.reflect.jvm.kotlinProperty

object PitcherInitializer : PitcherAnnotationScanner{
    override fun init(packageName: String) {
        val reflections = Reflections(ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(MethodAnnotationsScanner(),
                        FieldAnnotationsScanner()))

        val recipeList = reflections.getMethodsAnnotatedWith(PitcherRecipe::class.java)
        recipeList.forEach { method -> Pitcher.addFormula { method.invoke(null) } }

        val fieldList = reflections.getFieldsAnnotatedWith(PitcherPour::class.java)
    }
}