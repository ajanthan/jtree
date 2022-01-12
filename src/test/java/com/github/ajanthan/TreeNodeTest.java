package com.github.ajanthan;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TreeNodeTest {
    @Nested
    class TestCase {
        String name;
        Integer[] input;

        public TestCase(String name, Integer[] input) {
            this.name = name;
            this.input = input;
        }
    }

    @TestFactory
    public Stream<DynamicNode> testGetTree() {
        return Stream.of(
                new TestCase("small input", new Integer[]{1, 2, null, null, 3}),
                new TestCase("large input", new Integer[]{1, 2, null, 3, null, null, 3, 4, null, null, 5})
        ).map(testCase ->
                dynamicTest(testCase.name, () -> assertArrayEquals(testCase.input, TreeNode.getTree(testCase.input).toArray())));
    }
}