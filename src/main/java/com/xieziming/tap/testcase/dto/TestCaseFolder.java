package com.xieziming.tap.testcase.dto;

import com.xieziming.tap.testcase.model.TestCase;
import lombok.Data;

import java.util.List;

/**
 * Created by Suny on 6/14/17.
 */
@Data
public class TestCaseFolder {
    private String path;
    private List<TestCaseFolder> testCaseFolders;
    private List<TestCase> testCases;
}
