package org.amicofragile.spikes.groovy.spock

import spock.lang.Specification


class MapParametersSpecification extends Specification {
	def namedParamsMethod1(Map params) {
		assert params.a == 1
		assert params.b == 2
		assert params.c == 3
		true
	}

	def namedParamsMethod2(Map params, String param2, String param3) {
		assert params.a == 1
		assert params.b == 2
		assert params.c == 3
		assert param2 == "param2"
		assert param3 == "param3"
		true
	}

	def namedParamsMethodNoTypes(params, param2, param3) {
                assert params.a == 1
                assert params.b == 2
                assert params.c == 3
                assert param2 == "param2"
                assert param3 == "param3"
                true
        }

	def "Default map-constructor" () {
		given:
		    	def pogo1 = new Pogo(a:1, b:2, c:3)
		    	def pogo2 = new Pogo( b:2, c:3)
		    	def pogo3 = new Pogo(b:2, a:3)
		expect:
		    	pogo1.a == 1
		    	pogo1.b == 2
		    	pogo1.c == 3
		and:
		    	pogo2.a == 0
		    	pogo2.b == 2
		    	pogo2.c == 3
		and:
		    	pogo3.a == 3
			pogo3.b == 2
	}
	
	def "Named parameters" () {
		given:
			def pogo = new Pogo()
		when:
			pogo.setValues(a:1, b:2, c:3)
		then:
			pogo.a == 1
	                pogo.b == 2
        	        pogo.c == 3
	}

	def "Named parameters like in the book" () {
		expect:
			namedParamsMethod1(a:1, b:2, c:3)
			namedParamsMethod1(b:2, c:3, a:1)
			namedParamsMethod1(c:3, a:1, b:2)
	}

	def "Mixed normal and named parameters like in the book" () {
		expect: "We can mix named and regular parameter in any order"
			namedParamsMethod2(a:1, b:2, c:3, "param2", "param3")
		    	namedParamsMethod2("param2", b:2, "param3", c:3, a:1)
		    	namedParamsMethod2(c:3, "param2", a:1, "param3", b:2)
	}

	def "Mixed normal and named parameters without types in the signature" () {
		expect: "We can mix named and regular parameter in any order"
			namedParamsMethodNoTypes(a:1, b:2, c:3, "param2", "param3")
		    	namedParamsMethodNoTypes("param2", b:2, "param3", c:3, a:1)
		    	namedParamsMethodNoTypes(c:3, "param2", a:1, "param3", b:2)
	}

	def "No paretnhesis" () {
		expect: "We can leave out parentheses"
			namedParamsMethod2 a:1, b:2, c:3, "param2", "param3"
			namedParamsMethod2 "param2", b:2, "param3", c:3, a:1
			namedParamsMethod2 c:3, "param2", a:1, "param3", b:2
	}
}
