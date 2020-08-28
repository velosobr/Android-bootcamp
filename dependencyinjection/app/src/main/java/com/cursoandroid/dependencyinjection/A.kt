package com.cursoandroid.dependencyinjection

class A(private val b: B) {
    b

}

class B {
    fun doSomething(){
        print("testing DI")
    }
}