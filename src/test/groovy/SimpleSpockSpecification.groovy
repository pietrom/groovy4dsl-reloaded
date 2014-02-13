import spock.lang.Specification

class SimpleSpockSpecification extends Specification {
	def "the truth could not be truer" () {
        	given: 
	            def truth = true

        	expect:
	            truth
	}

	void "two wrongs don't make a right" () {
	    given: "two false statements"
        	def theWorldIsFlat = false
	        def theEarthOrbitsTheSun = false

	    when: "we combine the two falsehoods"
        	def copernicusWasWrong = theWorldIsFlat && theEarthOrbitsTheSun
		
	    then: "Copernicus was telling the truth"
        	! copernicusWasWrong
	}

	void "we can extend Spock specs with and blocks" () {
 	   given: "Two Integer numbers"
        	Number a = 10
	        Number b = 5

	    expect: "Integer multiplication is commutative"
        	a * b == b * a

	    and: "Integer addition is commutative"
        	a + b == b + a
	}
}
