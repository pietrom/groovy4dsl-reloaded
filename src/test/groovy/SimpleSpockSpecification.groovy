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
}
