package dev.cristopher.detekt.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtClassOrObject

/**
 * This rule reports a missing prefix when inheriting from a `Repository` interface.
 */
class PrefixDefaultOnRepositoryRule(config: Config = Config.empty) : Rule(config) {

    companion object {
        const val PREFIX_REPOSITORY = "Default"
        const val REPOSITORY_KEYWORD = "Repository"
        const val RULE_ID = "PrefixDefaultOnRepositoryRule"
    }
    override val issue: Issue = Issue(
        RULE_ID,
        Severity.Style,
        "Use the prefix Default on every 'XXXRepository' implementations.",
        Debt.FIVE_MINS
    )

    override fun visitClassOrObject(classOrObject: KtClassOrObject) {
        for (superEntry in classOrObject.superTypeListEntries) {
            if (superEntry.text.endsWith(REPOSITORY_KEYWORD) &&
                classOrObject.text.contains(REPOSITORY_KEYWORD) &&
                !classOrObject.text.startsWith(PREFIX_REPOSITORY)
            ) {
                report(
                    classOrObject,
                    "The repository implementation ${classOrObject.text} needs to start with the prefix 'Default'."
                )
            }
        }
        super.visitClassOrObject(classOrObject)
    }

    private fun report(classOrObject: KtClassOrObject, message: String) {
        report(CodeSmell(issue, Entity.atName(classOrObject), message))
    }
}
