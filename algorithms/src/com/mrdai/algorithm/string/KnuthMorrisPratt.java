package com.mrdai.algorithm.string;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Knuth-Morris-Pratt string searching algorithm
 */
public abstract class KnuthMorrisPratt {

    public static int[] search(String text, String word) {
        return search(text.toCharArray(), word.toCharArray());
    }

    public static int[] search(char[] text, char[] word) {
        if (word.length == 0 || text.length < word.length)
            return new int[0];
        if (word.length == 1)
            return searchSingleChar(text, word[0]);

        List<Integer> results = new ArrayList<>();
        int[] backtrack = computeBacktrack(word);

        int i, j;
        for (i = 0; i < text.length - word.length + 1;) {
            for (j = 0; j < word.length; j++) {
                if (text[i + j] != word[j]) {
                    i += j - backtrack[j];
                    break;
                }
            }
            if (j == word.length) { // Full match is found
                results.add(i);
                i += j - backtrack[word.length - 1] - 1;
            }
        }

        return toIntArray(results);
    }

    private static int[] searchSingleChar(char[] text, char target) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < text.length; i++)
            if (text[i] == target)
                results.add(i);
        return toIntArray(results);
    }

    private static int[] toIntArray(List<Integer> list) {
        int[] result = new int[list.size()];
        Iterator<Integer> iter = list.iterator();
        for (int i = 0; i < result.length; i++)
            result[i] = iter.next();
        return result;
    }

    private static int[] computeBacktrack(char[] word) {
        int[] result = new int[word.length];
        result[0] = -1;
        int k = 0; // the zero-based index in W of the next character of the current candidate substring
        for (int i = 2; i < word.length; i++) {
            while (k > 0 && word[k] != word[i - 1]) k = result[k];
            if (word[k] == word[i - 1]) k++;
            result[i] = k;
        }
        return result;
    }

    private KnuthMorrisPratt() {
        throw new AssertionError("This class should not be instantiated.");
    }
}
