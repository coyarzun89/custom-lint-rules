package dev.cristopher.customrules.domain.repository

import dev.cristopher.customrules.data.dto.DataBranch

interface BranchRepository {

    fun fetchBranches(): List<DataBranch>
}
