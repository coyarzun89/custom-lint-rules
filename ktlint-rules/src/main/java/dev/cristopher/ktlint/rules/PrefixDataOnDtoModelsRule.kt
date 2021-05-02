package dev.cristopher.ktlint.rules

import com.github.shyiko.ktlint.core.Rule
import com.github.shyiko.ktlint.core.ast.ElementType
import com.github.shyiko.ktlint.core.ast.children
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtPackageDirective

class PrefixDataOnDtoModelsRule : Rule("prefix-data-on-dto-model") {
    companion object {
        const val DATA_PREFIX = "Data"
        const val IMPORT_DTO = "data.dto"
    }
    override fun visit(node: ASTNode, autoCorrect: Boolean, emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit) {
        if (node.elementType == ElementType.PACKAGE_DIRECTIVE) {
            val qualifiedName = (node.psi as KtPackageDirective).qualifiedName
            if (qualifiedName.isEmpty()) {
                return
            }

            if (qualifiedName.endsWith(IMPORT_DTO)) {
                node.treeParent.children().forEach {
                    if (it.elementType == ElementType.CLASS) {
                        val klass = it.psi as KtClass
                        if (klass.name?.startsWith(DATA_PREFIX, ignoreCase = true) != true) {
                            emit(it.startOffset, "${klass.name} class is not using the prefix Data. Classes inside any 'data.dto' package should use that prefix", false)
                        }
                    }
                }
            }
        }
    }
}
