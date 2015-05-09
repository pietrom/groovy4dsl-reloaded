count = 1

assert count == 1
assert binding.getVariable("count") == 1
binding.setVariable("count" , 2)
assert count == 2
assert binding.getVariable("count") == 2 

def local = count 

assert local == 2
try {
    binding.getVariable("local")
    assert false
} catch (e) {
    assert e in MissingPropertyException
}

count = 10
assert binding.getVariable("count") == 10

binding.count = count + 1
assert binding.count == 11

def Binding bind = new Binding()
bind.message = "Hello, World!"
shell = new GroovyShell(bind)
shell.evaluate("message = message.toUpperCase()")
assert bind.message == "HELLO, WORLD!"

println "Done."
