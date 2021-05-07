package dev.cristopher.lint.rules

import com.android.tools.lint.checks.infrastructure.TestFiles.xml
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.android.tools.lint.detector.api.Severity
import org.junit.Test

class HardcodedColorXmlDetectorTest {

    @Test
    fun `Given a hardcoded color on a custom text view property, When we analyze our custom rule, Then display an error`() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
                    <TextView xmlns:app="http://schemas.android.com/apk/res-auto"
                    app:someCustomColor="#fff"/>
                    """
                ).indented()
            )
            .issues(HardcodedColorXmlDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectCount(1, Severity.ERROR)
    }

    @Test
    fun `Given a hardcoded color on a text view, When we analyze our custom rule, Then display an error`() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
                        <TextView xmlns:android="http://schemas.android.com/apk/res/android"
                            android:textColor="#80000000"/>
                        """
                ).indented()
            )
            .issues(HardcodedColorXmlDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectCount(1, Severity.ERROR)
    }

    @Test
    fun `Given a color from our resources on a text view, When we analyze our custom rule, Then expect no errors`() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
                        <TextView xmlns:android="http://schemas.android.com/apk/res/android"
                            android:textColor="@color/primaryColor"/>
                        """
                ).indented()
            )
            .issues(HardcodedColorXmlDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectClean()
    }
}
