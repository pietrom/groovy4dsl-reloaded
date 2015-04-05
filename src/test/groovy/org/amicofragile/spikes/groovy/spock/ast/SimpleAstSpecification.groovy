package org.amicofragile.spikes.groovy.spock.ast

import org.amicofragile.spikes.groovy.spock.ScriptSpecificationSupport

class SimpleAstSpecification extends ScriptSpecificationSupport {
	def "Apply a simple AST transformation (built from code)"() {
		given:
		def target = new Simple(name : 'Pietro', surname : 'Martinelli')

		when:
		target.prettyPrint()
		then:
		output().replaceAll(System.getProperty("line.separator"), "\n") == "name: Pietro\nsurname: Martinelli"
	}
}
