
package dev.cristopher.lint.rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Detector.UastScanner
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UElement
import org.jetbrains.uast.ULiteralExpression
import org.jetbrains.uast.evaluateString

/**
 * Sample detector showing how to analyze Kotlin/Java code. This example
 * flags all string literals in the code that contain the word "lint".
 */
@Suppress("UnstableApiUsage")
class HardcodedHexColorCodeDetector : Detector(), UastScanner {

    companion object {
        val ISSUE = Issue.create(
            id = "HardcodedHexColorOnCode",
            briefDescription = "Prohibits hardcoded hex colors in the codebase",
            explanation = "Hardcoded hex colors should be declared in a `<color>` resource",
            category = Category.CORRECTNESS,
            severity = Severity.ERROR,
            implementation = Implementation(
                HardcodedHexColorCodeDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement?>> {
        return listOf(ULiteralExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        // Note: Visiting UAST nodes is a pretty general purpose mechanism;
        // Lint has specialized support to do common things like "visit every class
        // that extends a given super class or implements a given interface", and
        // "visit every call site that calls a method by a given name" etc.
        // Take a careful look at UastScanner and the various existing lint check
        // implementations before doing things the "hard way".
        // Also be aware of context.getJavaEvaluator() which provides a lot of
        // utility functionality.
        return object : UElementHandler() {
            override fun visitLiteralExpression(node: ULiteralExpression) {
                val string = node.evaluateString() ?: return
                // TODO: Check better ways to do this
                if (string.startsWith("#")) {
                    context.report(
                        issue = ISSUE,
                        scope = node,
                        location = context.getLocation(node),
                        message = "Hardcoded hex colors should be declared in a `<color>` resource."
                    )
                }
            }
        }
    }
}
