package dev.cristopher.lint.rules

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.XmlScannerConstants
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class HardcodedHexColorXmlDetector : ResourceXmlDetector() {

    companion object {
        val ISSUE = Issue.create(
            id = "HardcodedHexColorXml",
            briefDescription = "Prohibits hardcoded hex colors in layout XML",
            explanation = "Hardcoded hex colors should be declared as a `<color>` resource",
            category = Category.CORRECTNESS,
            severity = Severity.ERROR,
            implementation = Implementation(
                HardcodedHexColorXmlDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        // Return true if we want to analyze resource files in the specified resource
        // folder type. In this case we only need to analyze layout resource files.
        return folderType == ResourceFolderType.LAYOUT
    }

    override fun getApplicableAttributes(): Collection<String>? {
        // Return the set of attribute names we want to analyze. The `visitAttribute` method
        // below will be called each time lint sees one of these attributes in a
        // layout XML resource file. In this case, we want to analyze every attribute
        // in every layout XML resource file.
        return XmlScannerConstants.ALL
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        // Get the value of the XML attribute.
        val attributeValue = attribute.nodeValue

        if (!attributeValue.startsWith("#")) {
            // Do nothing if the attribute value isn't a hex color.
            return
        }

        context.report(
            issue = ISSUE,
            scope = attribute,
            location = context.getValueLocation(attribute),
            message = "Hardcoded hex colors should be declared in a `<color>` resource."
        )
    }
}
