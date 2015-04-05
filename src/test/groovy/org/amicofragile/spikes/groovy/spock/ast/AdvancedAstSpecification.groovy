package org.amicofragile.spikes.groovy.spock.ast

import org.amicofragile.spikes.groovy.spock.ScriptSpecificationSupport

class AdvancedAstSpecification extends ScriptSpecificationSupport {
	def "Apply an advanced AST transformation (built from spec)"() {
		given:
		def target = new Advanced(name : 'Pietro Martinelli', age : 37)

		when:
		target.prettyPrint()
		then:
		output().replaceAll(System.getProperty("line.separator"), "\n") == "age: 37\nname: Pietro Martinelli"
	}
}
