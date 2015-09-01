package com.ambergleam.lint;

import com.android.annotations.NonNull;
import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.client.api.LintClient;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Project;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static com.android.SdkConstants.DOT_JAVA;

public class EnumDetectorTest extends LintDetectorTest {

    private Set<Issue> mEnabled = new HashSet<Issue>();

    @Override
    protected Detector getDetector() {
        return new EnumDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Collections.singletonList(EnumDetector.ISSUE);
    }

    @Override
    protected TestConfiguration getConfiguration(LintClient client, Project project) {
        return new TestConfiguration(client, project, null) {
            @Override
            public boolean isEnabled(@NonNull Issue issue) {
                return super.isEnabled(issue) && mEnabled.contains(issue);
            }
        };
    }

    public void testEmptyCase() throws Exception {
        mEnabled = Collections.singleton(EnumDetector.ISSUE);
        String expected = "No warnings.";
        String result = lintProject(java(DOT_JAVA, ""));
        assertEquals(expected, result);
    }

    public void testEnumCase() throws Exception {
        mEnabled = Collections.singleton(EnumDetector.ISSUE);
        String expected = "EnumDetectorTest_testEnumCase: Warning: Avoid Using Enums [EnumDetector]\n0 errors, 1 warnings\n";
        String result = lintProject(
                java(DOT_JAVA,
                     String.format("package com.example.lint; public enum Pet { CAT, DOG, TURTLE }")
                )
        );
        assertEquals(expected, result);
    }

}