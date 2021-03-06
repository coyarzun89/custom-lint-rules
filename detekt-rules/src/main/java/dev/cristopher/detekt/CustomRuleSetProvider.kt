package dev.cristopher.detekt

import dev.cristopher.detekt.rules.PrefixDefaultOnRepositoryRule
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {

    override val ruleSetId: String = "custom-detekt-rules"

    override fun instance(config: Config) =
        RuleSet(ruleSetId, listOf(PrefixDefaultOnRepositoryRule(config)))
}
