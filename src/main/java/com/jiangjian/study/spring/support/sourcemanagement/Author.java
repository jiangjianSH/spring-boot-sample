package com.jiangjian.study.spring.support.sourcemanagement;

import java.lang.annotation.Documented;

@Documented
public @interface Author {
    String author();
    String date();
    String currentRevision() default "1";
    String lastModified() default "N/A";
    String lastModifiedBy() default "N/A";
    String[] reviewers();
}
