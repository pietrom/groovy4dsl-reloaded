def binding = new Binding()
binding.outerBlock = { closure ->
   closure.delegate = delegate
   closure()
   println "outerBlock: " + binding.message
}

binding.innerBlock = { closure ->
   closure.delegate = delegate
   closure()
   println "innerBlock: " + binding.message
}

def shell = new GroovyShell(binding)
shell.evaluate(
""" outerBlock {
        innerBlock {
         message = "Hello, World!"
        }
	}
	
	outerBlock {
		println message
	}
"""
)
println "caller: " + binding.message