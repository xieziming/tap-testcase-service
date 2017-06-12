/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.controller;

import com.xieziming.tap.testcase.dto.TestCasePathSearchResult;
import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.search.SearchCondition;
import com.xieziming.tap.testcase.service.TestCaseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Suny on 5/31/17.
 */
@RestController
@RequestMapping("/search")
public class TestCaseSearchServiceController {
    @Autowired
    TestCaseSearchService testCaseSearchService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<TestCase> findAll(){
        return testCaseSearchService.findAll();
    }

    @RequestMapping(value = "", method = {RequestMethod.PUT, RequestMethod.POST})
    public List<TestCase> search(@RequestBody SearchCondition searchCondition) throws Exception {
        return testCaseSearchService.findByConditions(searchCondition);
    }

    @RequestMapping(value = "/path", method = RequestMethod.POST)
    public TestCasePathSearchResult retrievePath(@RequestParam String path){
        return testCaseSearchService.retrievePath(path);
    }
}
