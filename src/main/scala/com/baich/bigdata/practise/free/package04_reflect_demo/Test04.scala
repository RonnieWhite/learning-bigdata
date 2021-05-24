package com.baich.bigdata.practise.free.package04_reflect_demo


class Test04(name: String, age: Int) {
  def sayHello(): Unit = {
    println(s"Hello, my name is $name, I'm $age years old...")
  }
}

object Test04 {
  def main(args: Array[String]): Unit = {
    //    val universe = scala.reflect.runtime.universe
    //    val mirror = universe.runtimeMirror(getClass.getClassLoader)
    //    val test04Class = universe.typeOf[Test04].typeSymbol.asClass
    //    val classMirror = mirror.reflectClass(test04Class)
    //    val constructor = universe.typeOf[Test04].decl(universe.termNames.CONSTRUCTOR).asMethod
    //    val methodMirror = classMirror.reflectConstructor(constructor)
    //    val entity = methodMirror("Jordan", 23)
    //    println(entity.toString)
    val universe = scala.reflect.runtime.universe
    val mirror = universe.runtimeMirror(getClass.getClassLoader)
    val classMirror = mirror.reflectClass(universe.typeOf[Test04].typeSymbol.asClass)
    val constructor = universe.typeOf[Test04].decl(universe.termNames.CONSTRUCTOR).asMethod
    val methodMirror = classMirror.reflectConstructor(constructor)
    val test04Entity = methodMirror("Jordan", 23)
    val instanceMirror = mirror.reflect(test04Entity)
    val method = universe.typeOf[Test04].decl(universe.TermName("sayHello")).asMethod
    val myMethod = instanceMirror.reflectMethod(method)
    myMethod()
  }
}
