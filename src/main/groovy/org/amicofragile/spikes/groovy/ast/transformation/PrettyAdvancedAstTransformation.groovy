package org.amicofragile.spikes.groovy.ast.transformation

import groovyjarjarasm.asm.Opcodes

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation (phase = CompilePhase.SEMANTIC_ANALYSIS)
class PrettyAdvancedAstTransformation implements ASTTransformation {

	void visit(ASTNode[] nodes, SourceUnit source) {
		if (!nodes) return
			if (!(nodes[0] instanceof AnnotationNode)) return
			if (!nodes[1] && !(nodes[1] instanceof ClassNode)) return
			ClassNode classNode = nodes[1]

		def astNodes = new AstBuilder().buildFromSpec {
			method( 'prettyPrint', Opcodes.ACC_PUBLIC, String) {
				parameters {}
				exceptions {}
				block {
					owner.expression.addAll (
					new AstBuilder().buildFromCode {
						this.properties.sort { it.key }.each {
							if (it.key != 'prettyPrint' &&
							it.key != 'class')
								println it.key + ": " + it.value
						}
					})
				}
				annotations {}
			}
		}
		def methodNode = astNodes[0]
		
		classNode.addMethod(methodNode)
	}
}
