package dev.cristopher.customrules.data.dto

data class DataProduct(val id: String, val name: String, val type: Type)

enum class Type {
    CUSTOM, CATALOG
}
