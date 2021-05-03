package dev.cristopher.ktlint

import com.github.shyiko.ktlint.core.RuleSet
import com.github.shyiko.ktlint.core.RuleSetProvider
import dev.cristopher.ktlint.rules.PrefixDataOnDtoModelsRule

class CustomRuleSetProvider : RuleSetProvider {
    private val ruleSetId: String = "custom-ktlint-rules"

    override fun get() = RuleSet(ruleSetId, PrefixDataOnDtoModelsRule())
}
