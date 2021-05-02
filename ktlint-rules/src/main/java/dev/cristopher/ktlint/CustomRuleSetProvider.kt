package dev.cristopher.ktlint

import com.github.shyiko.ktlint.core.RuleSet
import com.github.shyiko.ktlint.core.RuleSetProvider
import dev.cristopher.ktlint.rules.PrefixDataOnDtoModelsRule

class CustomRuleSetProvider : RuleSetProvider {
    override fun get() = RuleSet("custom-ktlint-rules", PrefixDataOnDtoModelsRule())
}
