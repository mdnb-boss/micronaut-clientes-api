package br.com.marcelodaniel

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.marcelodaniel")
		.start()
}

