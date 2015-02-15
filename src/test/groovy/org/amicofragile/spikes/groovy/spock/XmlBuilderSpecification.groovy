package org.amicofragile.spikes.groovy.spock

import spock.lang.Specification
import static org.custommonkey.xmlunit.XMLUnit.*

class XmlBuilderSpecification extends Specification {
	def "Build XML using GroovyBuilder"() {
		given:
			def writer = new StringWriter()
			def builder = new groovy.xml.MarkupBuilder(writer)
		when:
			def customers = builder.customers {
				customer(id:1001) {
			        	name(firstName:"Fred",surname:"Flintstone")
				        address(street:"1 Rock Road",city:"Bedrock")
				}
				customer(id:1002) {
					name(firstName:"Barney",surname:"Rubble")
				        address(street:"2 Rock Road",city:"Bedrock")
				}
			}
		then:
			println writer.toString()
		    xmlIsIdentical (writer.toString(), "/customers.xml" )
		}

	def xmlIsIdentical(current, goldenMaster) {
		println( "Checking ${current} against ${goldenMaster}" )
		String goldenMasterText = this.getClass().getResource(goldenMaster).text
		compareXML(goldenMasterText, current).similar()
	}
}
