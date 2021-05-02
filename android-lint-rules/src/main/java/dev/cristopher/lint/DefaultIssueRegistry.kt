package dev.cristopher.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import dev.cristopher.lint.rules.HardcodedHexColorCodeDetector
import dev.cristopher.lint.rules.HardcodedHexColorXmlDetector

@Suppress("UnstableApiUsage")
class DefaultIssueRegistry : IssueRegistry() {
    override val issues = listOf(
        HardcodedHexColorXmlDetector.ISSUE,
        HardcodedHexColorCodeDetector.ISSUE
    )

    override val api: Int
        get() = CURRENT_API

    override val minApi: Int
        get() = 8
}
