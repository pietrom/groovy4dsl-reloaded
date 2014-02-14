import spock.lang.Specification


class TransientTruthSpecification extends Specification {
	def truth = false
	
	def "Change and check" () {
		given: truth = true
		expect: truth
	}
	
	def "Expects no truth" () {
		expect: !truth
	}
}
