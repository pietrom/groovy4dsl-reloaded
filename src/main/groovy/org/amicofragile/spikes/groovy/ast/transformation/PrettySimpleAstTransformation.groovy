package org.amicofragile.spikes.groovy.ast.transformation;

import java.lang.reflect.Modifier

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class PrettySimpleAstTransformation implements ASTTransformation {

	void visit(ASTNode[] nodes, SourceUnit source) {
  ClassNode classNode = nodes[1]

        def astNodes = new AstBuilder().buildFromCode {
            this.properties.sort { it.key }.each {
                if (it.key != 'prettyPrint' && it.key != 'class')
                    println it.key.trim() + ": " + it.value
            }
            }
        def methodStatement = astNodes[0]
        def methodNode = new MethodNode(
                'prettyPrint',
				Modifier.PUBLIC,
				null,
                new Parameter[0],
                null,
                methodStatement
        )
        classNode.addMethod(methodNode)
    }
}
