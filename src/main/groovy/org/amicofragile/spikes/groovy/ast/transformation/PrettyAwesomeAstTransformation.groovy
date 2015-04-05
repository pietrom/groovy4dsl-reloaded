package org.amicofragile.spikes.groovy.ast.transformation

import groovy.transform.CompilationUnitAware

import org.amicofragile.spikes.groovy.ast.PrettyAwesomeTrait
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.control.CompilationUnit
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.trait.TraitComposer

@GroovyASTTransformation (phase = CompilePhase.SEMANTIC_ANALYSIS)
class PrettyAwesomeAstTransformation implements ASTTransformation, CompilationUnitAware {

    def compilationUnit

    void visit(ASTNode[] nodes, SourceUnit source) {
        ClassNode classNode = nodes[1]

        def traitNode = ClassHelper.make(PrettyAwesomeTrait)
        if (!classNode.implementsInterface(traitNode)) {
            classNode.addInterface(traitNode)
            TraitComposer.doExtendTraits(
                classNode,source,compilationUnit)
        }
    }

    @Override
    void setCompilationUnit(CompilationUnit unit) {
        compilationUnit = unit
    }
}
