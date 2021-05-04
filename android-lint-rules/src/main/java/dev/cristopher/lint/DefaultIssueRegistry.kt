package dev.cristopher.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import dev.cristopher.lint.rules.HardcodedColorXmlDetector

@Suppress("UnstableApiUsage")
class DefaultIssueRegistry : IssueRegistry() {
    override val issues = listOf(
        HardcodedColorXmlDetector.ISSUE,
    )

    override val api: Int
        get() = CURRENT_API
}
