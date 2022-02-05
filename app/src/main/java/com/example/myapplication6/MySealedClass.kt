package com.example.myapplication6

sealed class MySealedClass

data class Const(val number:Int): MySealedClass()

data class Sum(val d1:Int,val d2:Int):MySealedClass()

object Number :MySealedClass()