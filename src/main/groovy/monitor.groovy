import java.lang.management.*
import java.lang.management.ManagementFactory as Factory
import javax.management.remote.JMXConnectorFactory as JMX
import javax.management.remote.JMXServiceURL as ServiceURL

def serverUrl = 'service:jmx:rmi:///jndi/rmi://localhost:3333/jmxrmi'
def server = JMX.connect(
                    new ServiceURL(serverUrl)).MBeanServerConnection

println "HEAP USAGE"
def mem = Factory.newPlatformMXBeanProxy(server, 
                     Factory.MEMORY_MXBEAN_NAME, MemoryMXBean.class)
def heapUsage = mem.heapMemoryUsage
println """Memory usage : $heapUsage.used"""
mem.gc()
heapUsage = mem.heapMemoryUsage
println """Memory usage after GC: $heapUsage.used"""
