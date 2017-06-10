/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.controller;

import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.search.SearchCondition;
import com.xieziming.tap.testcase.service.TestCaseSearchService;
import com.xieziming.tap.testcase.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/**
 * Created by Suny on 5/31/17.
 */
@RestController
@RequestMapping("/testCases")
public class TestCaseServiceController {
    @Autowired
    TestCaseService testCaseService;

    @Autowired
    TestCaseSearchService testCaseSearchService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<TestCase> findAll(){
        return testCaseSearchService.findAll();
    }

    @RequestMapping(value = "", method = {RequestMethod.PUT, RequestMethod.POST})
    public TestCase save(@RequestBody TestCase testCase){
        return testCaseService.save(testCase);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public TestCase find(@PathVariable String uid){
        return testCaseSearchService.findOne(uid);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String uid){
        testCaseService.delete(uid);
    }

    @RequestMapping(value = "/search", method = {RequestMethod.PUT, RequestMethod.POST})
    public List<TestCase> search(@RequestBody SearchCondition searchCondition) throws Exception {
        return testCaseSearchService.findByConditions(searchCondition);
    }

    @RequestMapping(value = "/subPaths", method = RequestMethod.POST)
    public Set<String> findSubPaths(@RequestParam String path){
        return testCaseSearchService.findSubPaths(path);
    }
}
