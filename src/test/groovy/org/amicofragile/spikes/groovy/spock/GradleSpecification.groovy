package org.amicofragile.spikes.groovy.spock

import spock.lang.Specification

class GradleSpecification extends Specification {
	def "hello task says Hello, World!"() {
		given: "we have a gradle build command"
			def command = "gradle -q -b gradle/hello-with-actions.gradle hello"
		when: "we run the build command"
			def proc = command.execute()
			proc.waitFor()
		then: "the script outputs the correct details"
			"And this one?\nFirst\nLast" == proc.in.text.trim()
	}
}
