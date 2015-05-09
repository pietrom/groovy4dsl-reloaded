binding.block = { closure ->
    def cloned = closure.clone()
    cloned.delegate = delegate
    this.enclosing = "block"
	        
    println "block encountered"
    cloned()
}

binding.nestedBlock = { closure ->
    assert closure.delegate.enclosing == "block"
    def cloned = closure.clone()
    cloned.delegate = delegate
    println "0:" + this
    println "1:" + this.enclosing
    this.enclosing = "nestedBlock"
    println "2:" + this.enclosing

    println "nested block encountered"
    cloned()
}

block {
     	nestedBlock { 
     	}
}
block {
	nestedBlock { 
    	}
}
