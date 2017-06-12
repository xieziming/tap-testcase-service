package com.xieziming.tap.testcase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Suny on 6/11/17.
 */
@Data
@AllArgsConstructor
public class TestCasePath {
    private String path;
    private Integer testCaseCount;
}
