/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xieziming.tap.testcase.repository;

import com.xieziming.tap.testcase.model.TestCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * A repository to manage {@link TestCase} instances.
 *
 * @author Suny Xie
 */
@RepositoryRestResource(collectionResourceRel = "testCase", path = "testCase")
public interface TestCaseRepository extends PagingAndSortingRepository<TestCase, String>, CrudRepository<TestCase, String> {
    TestCase findByUid(@Param("uid") String uid);
    TestCase findByName(@Param("name") String name);
    List<TestCase> findByNameContaining(@Param("name") String name);
    List<TestCase> findByStatus(@Param("status") String status);
    List<TestCase> findByDescriptionContaining(@Param("description") String description);
}
