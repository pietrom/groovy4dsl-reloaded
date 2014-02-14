package org.amicofragile.spikes.groovy.spock

class HelloScriptSpecification extends ScriptSpecificationSupport {
	def "HelloWorld says Hello World"() {
		given: "we have a Hello World script"
			def script = new File("scripts/hello.groovy")
		when: "we run the script"
			shell.evaluate script
		then: "the script outputs the correct details"
			"Hello, World!" == output()
	}
}
