import spock.lang.Specification
import static Currency.*


class CommandsChainReloadedSpecification extends Specification {

	class A {
		def methodA(p) {
			new B(a:p)
		}
	}

	class B {
		def a
		def methodB(p) {
			new C(a:a, b:p)
		}
	}
	
	class C {
		def a, b
		def methodC(p) {
			"${a}${b}${p}"
		}
	}
	
	def "commands chain" () {
		given:
			A a = new A()

		when:
			def c = a.methodA("aa").methodB("bb")
			println c
			c.methodC("cc")
			def res = a.methodA "aa" methodB "bb" methodC "cc"

		then:
			res == "aabbcc"
	}
}
