package dev.cristopher.ktlint.rules

import com.github.shyiko.ktlint.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PrefixDataOnDtoModelsRuleTest {

    @Test fun incorrectPrefixDataOnDtoModel() {
        val findings = PrefixDataOnDtoModelsRule().lint(
            """
            package dev.cristopher.customrules.data.dto

            class BrandColor {
                var id: String? = null
            }

            """.trimIndent()
        )

        assertThat(findings).hasSize(1)
        assertThat(findings[0].detail).isEqualTo(
            "'BrandColor' class is not using the prefix Data. " +
                "Classes inside any 'data.dto' package should use that prefix"
        )
    }

    @Test fun correctPrefixDataOnDtoModel() {
        val findings = PrefixDataOnDtoModelsRule().lint(
            """
            package dev.cristopher.customrules.data.dto

            data class DataBranch(val id: String)
            """.trimIndent()
        )

        assertThat(findings).hasSize(0)
    }
}
