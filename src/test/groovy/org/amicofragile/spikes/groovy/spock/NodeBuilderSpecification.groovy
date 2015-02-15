package org.amicofragile.spikes.groovy.spock

import spock.lang.Specification
import static org.custommonkey.xmlunit.XMLUnit.*

class NodeBuilderSpecification extends Specification {
	class Customer {
		def id, firstName, surname, street, city
	}

	def "Build tree-structure using GroovyBuilder"() {
		given:
		def builder = new groovy.util.NodeBuilder()

		def fred = new Customer(id:1001,firstName:"Fred", surname:"Flintstone",
		    street:"1 Rock Road",city:"Bedrock")
		def barney =  new Customer(id:1002,firstName:"Barney", surname:"Rubble",
		    street:"2 Rock Road",city:"Bedrock")
		def wilma = new Customer(id:1003,firstName:"Wilma", surname:"Flintstone",
		    street:"1 Rock Road",city:"Bedrock")
		def betty =  new Customer(id:1004,firstName:"Betty", surname:"Rubble",
		    street:"2 Rock Road",city:"Bedrock")
		def customerList = [ fred, barney,wilma,betty]

		when:
		def customers = builder.customers {
		    for (cust in customerList) {
			customer(id:cust.id) {
			    name(firstName:cust.firstName,surname:cust.surname)
			    address(street:cust.street, city:cust.city)
			}
		    }
		}

		then:
		customers.customer[0].'@id' == 1001
		customers.customer[1].'@id' == 1002
		customers.customer[0].address[0].'@street' ==
				       customers.customer[2].address[0].'@street'
		customers.grep{
		     it.name.any{it.'@surname' == "Rubble"}
		}.size == 2
		customers.grep{
		     it.name.any{it.'@surname' == "Rubble"}
		}.address.every{ it.'@street'[0] == "2 Rock Road"}
	}
}
