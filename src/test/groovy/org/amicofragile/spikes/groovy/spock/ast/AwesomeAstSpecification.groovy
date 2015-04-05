package org.amicofragile.spikes.groovy.spock.ast

import org.amicofragile.spikes.groovy.spock.ScriptSpecificationSupport

class AwesomeAstSpecification extends ScriptSpecificationSupport {
	def "Apply an awesome AST transformation (adding trait)"() {
		given:
		def target = new Awesome(name : 'Pietro Martinelli', age : 37)

		when:
		target.prettyPrint()
		then:
		output().replaceAll(System.getProperty("line.separator"), "\n") == "age: 37\nname: Pietro Martinelli"
	}
}
