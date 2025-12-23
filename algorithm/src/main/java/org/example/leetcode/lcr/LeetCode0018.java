package org.example.leetcode.lcr;

/**
 * <a href="https://leetcode.cn/problems/XltzEq">...</a>
 */
public class LeetCode0018 {

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        return true;
    }

}
