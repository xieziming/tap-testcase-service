/*
 * Copyright (c) 2016. SUNY XIE, All rights reserved.
 * Inbox@xieziming.com
 */
package com.xieziming.tap.testcase;

import com.xieziming.tap.testcase.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Suny on 9/10/16.
 */
public class MockData {
    public static TestCase createFullFilledTestCase(){
        TestCase testCase = new TestCase();
        testCase.setUid("TestCase_01");
        testCase.setName("fake testcase case "+Calendar.getInstance().getTimeInMillis());
        testCase.setDescription("testcase description");
        testCase.setStatus("Ready");
        testCase.setCreatedTime(Calendar.getInstance().getTime());

        TestCaseMeta testCaseMeta = new TestCaseMeta();
        testCaseMeta.setMetaType("General");
        testCaseMeta.setMetaKey("meta_1");
        testCaseMeta.setMetaValue("meta value");
        testCaseMeta.setTestCase(testCase);
        List<TestCaseMeta> testCaseMetas = new ArrayList<>();
        testCaseMetas.add(testCaseMeta);
        testCase.setTestCaseMetas(testCaseMetas);

        TestDataDefinition testDataDefinition = new TestDataDefinition();
        testDataDefinition.setType("Local");
        testDataDefinition.setField("User");
        testDataDefinition.setValue("Suny");
        testDataDefinition.setRemark("testcase remark");
        TestData testData = new TestData();
        testData.setTestCase(testCase);
        testData.setTestDataDefinition(testDataDefinition);
        List<TestData> testDatas = new ArrayList<>();
        testDatas.add(testData);
        testCase.setTestDatas(testDatas);

        TestStep testStep = new TestStep();
        TestStepOperation testOperation = new TestStepOperation();
        testOperation.setOperation("operation 1");
        testOperation.setRemark("operation remark");
        testOperation.setOperator("operator 1");
        testStep.setTestStepOperation(testOperation);
        testStep.setStepOrder(1);
        testStep.setTestCase(testCase);
        testStep.setParameter("operation parameter");
        List<TestStep> testSteps = new ArrayList<>();
        testSteps.add(testStep);
        testCase.setTestSteps(testSteps);

        TestCaseRelation testCaseRelation = new TestCaseRelation();
        testCaseRelation.setTestCase(testCase);
        testCaseRelation.setRelatedTestCase(testCase);
        testCaseRelation.setRelation("the same case");
        List<TestCaseRelation> testCaseRelations = new ArrayList<>();
        testCaseRelations.add(testCaseRelation);
        testCase.setTestCaseRelations(testCaseRelations);

        return testCase;
    }
}
