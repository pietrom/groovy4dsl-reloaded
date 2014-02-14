package org.amicofragile.spikes.groovy.spock

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import spock.lang.Specification

abstract class ScriptSpecificationSupport extends Specification {
	GroovyShell shell
	Binding binding
	PrintStream orig
	ByteArrayOutputStream out

	def setup() {
		orig = System.out
		out = new ByteArrayOutputStream()
		System.setOut(new PrintStream(out))
		binding = new Binding()
		shell = new GroovyShell(binding)
	}
	def cleanup() {
		System.setOut(orig)
	}

	def output() {
		out.toString().trim()
	}
}
