package org.amicofragile.spikes.groovy.ast.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

import org.codehaus.groovy.transform.GroovyASTTransformationClass

@Target([ElementType.TYPE])
@Retention(RetentionPolicy.SOURCE)
@GroovyASTTransformationClass(["org.amicofragile.spikes.groovy.ast.transformation.PrettySimpleAstTransformation"])
public @interface PrettySimple {
}
