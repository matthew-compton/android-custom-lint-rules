package com.ambergleam.lint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Collections;
import java.util.List;

/**
 * The list of issues that will be checked when running <code>lint</code>.
 */
@SuppressWarnings("unused")
public class CustomIssueRegistry extends IssueRegistry {

    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(EnumDetector.ISSUE);
    }

}