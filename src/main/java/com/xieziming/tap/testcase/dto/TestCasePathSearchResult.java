package com.xieziming.tap.testcase.dto;

import com.xieziming.tap.testcase.model.TestCase;
import lombok.Data;

import java.util.List;

/**
 * Created by Suny on 6/12/17.
 */
@Data
public class TestCasePathSearchResult {
    private String parentTestCasePath;
    private List<TestCasePath> testCasePaths;
    private List<TestCase> testCases;
}
