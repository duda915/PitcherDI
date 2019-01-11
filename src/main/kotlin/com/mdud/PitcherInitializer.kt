package com.mdud

import org.reflections.Reflections
import org.reflections.scanners.MethodAnnotationsScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder

object PitcherInitializer : PitcherAnnotationScanner{
    override fun init(packageName: String) {
        val reflections = Reflections(ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(MethodAnnotationsScanner()))

        val list = reflections.getMethodsAnnotatedWith(PitcherRecipe::class.java)
        list.forEach { method -> Pitcher.addFormula { method.invoke(null) } }
    }
}