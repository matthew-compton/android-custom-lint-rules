package com.ambergleam.lint;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.Speed;
import com.android.tools.lint.detector.api.TextFormat;

import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.EnumConstant;
import lombok.ast.EnumDeclaration;
import lombok.ast.EnumTypeBody;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.Node;

/**
 * Checks for enums.
 */
public class EnumDetector extends Detector implements Detector.JavaScanner {

    private static final Implementation IMPLEMENTATION = new Implementation(
            EnumDetector.class,
            Scope.JAVA_FILE_SCOPE);

    public static final Issue ISSUE = Issue.create(
            "EnumDetector",
            "Avoid Using Enums",
            "No real Android programmer should ever use enums.",
            Category.PERFORMANCE,
            8,
            Severity.WARNING,
            IMPLEMENTATION
    );

    /**
     * Constructs a new {@link EnumDetector} check
     */
    public EnumDetector() {
    }

    @Override
    public boolean appliesTo(@NonNull Context context, @NonNull File file) {
        return true;
    }

    @NonNull
    @Override
    public Speed getSpeed() {
        return Speed.FAST;
    }

    // ---- Implements JavaScanner ----


    @Override
    public EnumSet<Scope> getApplicableFiles() {
        return Scope.JAVA_FILE_SCOPE;
    }

    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        return Arrays.<Class<? extends Node>>asList(
                EnumDeclaration.class,
                EnumTypeBody.class,
                EnumConstant.class);
    }

    @Override
    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
        return new EnumChecker(context);
    }

    private static class EnumChecker extends ForwardingAstVisitor {

        private final JavaContext mContext;

        public EnumChecker(JavaContext context) {
            mContext = context;
        }

        @Override
        public boolean visitEnumTypeBody(EnumTypeBody node) {
            mContext.report(ISSUE, Location.create(mContext.file), ISSUE.getBriefDescription(TextFormat.TEXT));
            return super.visitEnumTypeBody(node);
        }

        @Override
        public boolean visitEnumConstant(EnumConstant node) {
            mContext.report(ISSUE, Location.create(mContext.file), ISSUE.getBriefDescription(TextFormat.TEXT));
            return super.visitEnumConstant(node);
        }

        @Override
        public boolean visitEnumDeclaration(EnumDeclaration node) {
            mContext.report(ISSUE, Location.create(mContext.file), ISSUE.getBriefDescription(TextFormat.TEXT));
            return super.visitEnumDeclaration(node);
        }

    }

}