def binding = new Binding()
binding.saved = { 
}

binding.deferred = { closure ->
   closure.delegate = delegate
   closure()
}

def shell = new GroovyShell(binding)
shell.evaluate(
"""   
saved = {
      println "saved"
        deferred {
         println "deferred"
        }
   }
"""
)
storeSaved = binding.saved

def binding2 = new Binding()

binding2.saved = storeSaved
binding2.deferred = { closure ->
   closure.delegate = delegate
   closure()
}

def shell2 = new GroovyShell(binding2)
shell2.evaluate("saved.delegate = this; saved()")