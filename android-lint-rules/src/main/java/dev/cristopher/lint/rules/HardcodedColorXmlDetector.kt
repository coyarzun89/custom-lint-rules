package dev.cristopher.lint.rules

import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class HardcodedColorXmlDetector : ResourceXmlDetector() {

    companion object {
        val REGEX_HEX_COLOR = "#[a-fA-F\\d]{3,8}".toRegex()

        val ISSUE = Issue.create(
            id = "HardcodedColorXml",
            briefDescription = "Prohibits hardcoded colors in layout XML",
            explanation = "Hardcoded colors should be declared as a '<color>' resource",
            category = Category.CORRECTNESS,
            severity = Severity.ERROR,
            implementation = Implementation(
                HardcodedColorXmlDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun getApplicableAttributes(): Collection<String>? {
        // Return the set of attribute names we want to analyze. The `visitAttribute` method
        // below will be called each time lint sees one of these attributes in a
        // XML resource file. In this case, we want to analyze every attribute
        // in every XML resource file.
        return XmlScannerConstants.ALL
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        // Get the value of the XML attribute.
        val attributeValue = attribute.nodeValue
        if (attributeValue.matches(REGEX_HEX_COLOR)) {
            context.report(
                issue = ISSUE,
                scope = attribute,
                location = context.getValueLocation(attribute),
                message = "Hardcoded hex colors should be declared in a '<color>' resource."
            )
        }
    }
}
