package org.amicofragile.spikes.groovy.spock

import spock.lang.Specification

class SumSpecification extends Specification {
	def "multiple sums cost like one!" () {
		given: x
			and: y
			and: total
		when: "we sum up x and y"
			def result = x + y
		then: "we obtain the total"
			total == result
		where: x  |  y  |  total
			   2  |  2  |   4
			   0  |  5  |   5
			   -3 | -3  |  -6
	}
}
