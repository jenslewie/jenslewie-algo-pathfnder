package org.example.leetcode.global;

import java.util.HashMap;
import java.util.Map;

public class LeetCode0014 {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int m = strs.length;
        String commonPrefix = strs[0];
        for (int i = 1; i < m; i++) {
            commonPrefix = longestCommonPrefix(commonPrefix, strs[i]);
            if (commonPrefix.isEmpty()) {
                break;
            }
        }
        return commonPrefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int m = strs.length;
        int n = strs[0].length();
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m - 1; i++) {
                String currentStr = strs[i], nextStr = strs[i+1];
                if (currentStr.length() <= j || nextStr.length() <= j || currentStr.charAt(j) != nextStr.charAt(j)) {
                    return currentStr.substring(0, j);
                }
            }
        }
        return strs[0];
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            Map<Character, Integer> map = new HashMap<>();
            char c = ' ';
            for (String str : strs) {
                if (str.length() <= i) {
                    return sb.toString();
                }
                c = str.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            if (map.get(c) == strs.length) {
                sb.append(c);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

}
