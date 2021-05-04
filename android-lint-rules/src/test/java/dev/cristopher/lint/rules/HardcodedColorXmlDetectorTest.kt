package dev.cristopher.lint.rules

import com.android.tools.lint.checks.infrastructure.TestFiles.xml
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

class HardcodedColorXmlDetectorTest {

    @Test fun appCustomColor() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
          <TextView xmlns:app="http://schemas.android.com/apk/res-auto"
              app:someCustomColor="#fff"/>"""
                ).indented()
            )
            .issues(HardcodedColorXmlDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expect(
                """
          |res/layout/layout.xml:2: Error: Hardcoded hex colors should be declared in a '<color>' resource. [HardcodedColorXml]
          |    app:someCustomColor="#fff"/>
          |                         ~~~~
          |1 errors, 0 warnings""".trimMargin()
            )
    }

    @Test fun textColor() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
                        <TextView xmlns:android="http://schemas.android.com/apk/res/android"
                            android:textColor="#fff"/>"""
                ).indented()
            )
            .issues(HardcodedColorXmlDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expect(
                """
          |res/layout/layout.xml:2: Error: Hardcoded hex colors should be declared in a '<color>' resource. [HardcodedColorXml]
          |    android:textColor="#fff"/>
          |                       ~~~~
          |1 errors, 0 warnings""".trimMargin()
            )
    }
}
