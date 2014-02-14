package org.amicofragile.spikes.groovy.spock

import spock.lang.Specification;

class HelloSpecification extends Specification {
	def hello = new Hello()
	
	def "Hello to the world is so abused..."() {
		given: hello
		expect: hello.sayHello() == "Hello, World!" 
	}
}
