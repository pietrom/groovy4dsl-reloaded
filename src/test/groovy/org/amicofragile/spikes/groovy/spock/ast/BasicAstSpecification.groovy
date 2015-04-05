package org.amicofragile.spikes.groovy.spock.ast

import org.amicofragile.spikes.groovy.spock.ScriptSpecificationSupport

class BasicAstSpecification extends ScriptSpecificationSupport {
	def "Apply a basic AST transformation"() {
		given:
		def target = new Basic()

		when:
		target.prettyPrint()
		then:
		"I'm so pretty. Oh so pretty!" == output()
	}
}
