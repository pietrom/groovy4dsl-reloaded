import spock.lang.Specification

class SimpleSpockSpecification extends Specification {
    def "the truth could not be truer" () {
        given: 
            def truth = true

        expect:
            truth
    }
}
