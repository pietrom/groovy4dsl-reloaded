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

binding.block2 = { spec, closure ->
    def cloned = closure.clone()
    cloned.delegate = delegate
    this.enclosing = "block2"
	        
    println "${spec} encountered"
    cloned()
}

binding.nestedBlock2 = { spec, closure ->
    assert closure.delegate.enclosing == "block2"
    def cloned = closure.clone()
    cloned.delegate = delegate
    this.enclosing = "nestedBlock2"
		    
    println "${spec} encountered"
    cloned()
}

block2 ("first block") {
    	nestedBlock2 ("first nested"){   
    	}
}
block2 "second block", {
    	nestedBlock2 ("second nested"){   
	}
}
