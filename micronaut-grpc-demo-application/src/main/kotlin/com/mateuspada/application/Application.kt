package com.mateuspada.application

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.mateuspada.application", "com.mateuspada.endpoint")
        .start()
}