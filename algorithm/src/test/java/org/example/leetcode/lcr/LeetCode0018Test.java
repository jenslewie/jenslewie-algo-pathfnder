package org.example.leetcode.lcr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Valid Palindrome - LCR 018")
public class LeetCode0018Test {

    private final LeetCode0018 leetCode = new LeetCode0018();

    @ParameterizedTest(name = "[{index}] {0} | input: \"{1}\"")
    @MethodSource("palindromeTestCases")
    void testIsPalindrome(String caseDescription, String input, boolean expected) {
        assertEquals(expected, leetCode.isPalindrome(input),
                () -> "Case '%s': input '%s'".formatted(caseDescription, input));
    }

    private static Stream<Arguments> palindromeTestCases() {
        return Stream.of(
                Arguments.of("alphanumeric_palindrome", "A man, a plan, a canal: Panama", true),
                Arguments.of("non_palindrome_with_spaces", "race a car", false),
                Arguments.of("whitespace_only", " ", true),
                Arguments.of("empty_string", "", true),
                Arguments.of("single_character", "a", true),
                Arguments.of("case_insensitive_palindrome", "Madam", true),
                Arguments.of("palindrome_with_numbers", "12321", true),
                Arguments.of("alphanumeric_non_palindrome", "0P", false)
        );
    }

}
