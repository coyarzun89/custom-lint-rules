package dev.cristopher.customrules.data.repository

import dev.cristopher.customrules.data.dto.DataBranch
import dev.cristopher.customrules.domain.repository.BranchRepository

class DefaultBranchRepository : BranchRepository {
    override fun fetchBranches(): List<DataBranch> {
        TODO("Not yet implemented")
    }
}
