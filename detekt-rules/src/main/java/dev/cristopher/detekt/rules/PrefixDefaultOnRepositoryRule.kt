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
                classOrObject.name?.contains(REPOSITORY_KEYWORD) == true &&
                classOrObject.name?.startsWith(PREFIX_REPOSITORY) == false
            ) {
                report(
                    classOrObject,
                    "The repository implementation '${classOrObject.name}' needs to start with the prefix 'Default'."
                )
            }
        }
    }

    private fun report(classOrObject: KtClassOrObject, message: String) {
        report(CodeSmell(issue, Entity.atName(classOrObject), message))
    }
}
