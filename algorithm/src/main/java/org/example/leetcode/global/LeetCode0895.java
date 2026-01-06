package org.example.leetcode.global;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class LeetCode0895 {

    int maxFreq;
    Map<Integer, Integer> valToFreq;
    Map<Integer, Stack<Integer>> freqToValsMap;
    LinkedList<Integer>[] freqToValsArray;

    public LeetCode0895() {
        maxFreq = 0;
        valToFreq = new HashMap<>();
        freqToValsMap = new HashMap<>();
        freqToValsArray = new LinkedList[2 * (int) 1e4];
    }

    public void push(int val) {
        int freq = valToFreq.getOrDefault(val, 0) + 1;
        maxFreq = Math.max(maxFreq, freq);
        valToFreq.put(val, freq);
        if (freqToValsArray[freq] == null) {
            freqToValsArray[freq] = new LinkedList<>();
        }
        freqToValsArray[freq].add(val);
    }

    public int pop() {
        LinkedList<Integer> list = freqToValsArray[maxFreq];
        int value = list.removeLast();
        if (list.isEmpty()) {
            maxFreq--;
        }
        valToFreq.put(value, valToFreq.get(value) - 1);
        return value;
    }

    public void push2(int val) {
        int freq = valToFreq.getOrDefault(val, 0) + 1;
        maxFreq = Math.max(maxFreq, freq);
        valToFreq.put(val, freq);
        freqToValsMap.computeIfAbsent(freq, k -> new Stack<>()).add(val);
    }

    public int pop2() {
        int value = freqToValsMap.get(maxFreq).pop();
        if (freqToValsMap.get(maxFreq).isEmpty()) {
            freqToValsMap.remove(maxFreq);
            maxFreq--;
        }
        valToFreq.put(value, valToFreq.get(value) - 1);
        return value;
    }

}
