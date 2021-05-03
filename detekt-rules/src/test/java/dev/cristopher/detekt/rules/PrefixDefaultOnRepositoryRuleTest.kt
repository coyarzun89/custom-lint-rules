package dev.cristopher.detekt.rules

import io.gitlab.arturbosch.detekt.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PrefixDefaultOnRepositoryRuleTest {

    @Test fun incorrectPrefixOnRepositoryImplementation() {
        val findings = PrefixDefaultOnRepositoryRule().lint(
            """
            class BranchRepositoryImpl : BranchRepository {
                override fun fetchBranches(): List<DataBranch> {
                    //
                }
            }
            """.trimIndent()
        )

        assertThat(findings).hasSize(1)
        assertThat(findings[0].message).isEqualTo("The repository implementation 'BranchRepositoryImpl' needs to start with the prefix 'Default'.")
    }

    @Test fun correctPrefixOnRepositoryImplementation() {
        val findings = PrefixDefaultOnRepositoryRule().lint(
            """
            class DefaultBranchRepository : BranchRepository {
                override fun fetchBranches(): List<DataBranch> {
                    //
                }
            }
            """.trimIndent()
        )

        assertThat(findings).hasSize(0)
    }
}
