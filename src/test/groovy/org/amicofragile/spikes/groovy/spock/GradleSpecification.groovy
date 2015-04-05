package org.amicofragile.spikes.groovy.spock

import spock.lang.Specification

class GradleSpecification extends Specification {
	def "hello task says Hello, World!"() {
		given: "we have a gradle build command"
			def command = "${getGradleCommand()} -q -b gradle/hello-with-actions.gradle hello"
		when: "we run the build command"
			def proc = command.execute()
			proc.waitFor()
		then: "the script outputs the correct details"
			def result = proc.in.text.trim().replaceAll(System.getProperty("line.separator"), "\n")
			"And this one?\nFirst\nLast" == result
	}
	
	private String getOperatingSystem() {
		System.getProperty("os.name").toLowerCase()
	}
	
	private boolean isWindows() {
		getOperatingSystem().indexOf("win") >= 0
	}
	
	private String getGradleCommand() {
		String cmd = "gradle"
		if(isWindows()) {
			cmd += ".bat"
		}
		return cmd
	}
}
