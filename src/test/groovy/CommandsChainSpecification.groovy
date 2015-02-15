import spock.lang.Specification
import static Currency.*


class CommandsChainSpecification extends Specification {

	class Account {
		double balance
	}
	
	static def convert ( currency, amount) {
		def result
		switch (currency) {
			case Currency.USD: result = amount
			        break
			case Currency.GBP: result = amount * 1.3
				break
			case Currency.EUR: result = amount * 1.1
		}
		    result
	}

	static def deposit (double amount) {
		println "Deposit ${amount}"
		[
			currency: {  Currency currency ->
				[
					to: { account ->
				        	account.balance += convert( currency , amount )
        	        		}
			        ]

        		}
    		]
	}

	def "commands chain" () {
		given:
			Account savings = new Account()

		when:
			deposit (100.00).currency(USD).to(savings)
			deposit 100.00 currency GBP to savings

		then:
			savings.balance == 230.0
	}
}
