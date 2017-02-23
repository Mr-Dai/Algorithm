package com.mrdai.algorithm.string;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * Test cases for {@link KnuthMorrisPratt}.
 */
public class KnuthMorrisPrattTest {

    @Test
    public void testOnSingleCharTarget() {
        int[] results = KnuthMorrisPratt.search("abababb", "a");
        assertArrayEquals(new int[]{ 0, 2, 4 }, results);
    }

    @Test
    public void testOnMultipleCharTarget() {
        int[] results = KnuthMorrisPratt.search("abcbabcbabcbb", "abc");
        assertArrayEquals(new int[]{ 0, 4, 8 }, results);
        results = KnuthMorrisPratt.search("ABC ABCDAB ABCDABCDABDE", "ABCDABD");
        assertArrayEquals(new int[]{ 15 }, results);
        results = KnuthMorrisPratt.search("ABACABABC ABAC ABACABABC ABAB ABACABABC C", "ABACABABC");
        assertArrayEquals(new int[]{ 0, 15, 30 }, results);
        results = KnuthMorrisPratt.search("ABC PARTICIPATE IN PARACHUTE B PARTICIPATE IN PARACHUTE", "PARTICIPATE IN PARACHUTE");
        assertArrayEquals(new int[]{ 4, 31 }, results);
    }

    @Test
    public void testOnNoMatchForMultipleCharTarget() {
        int[] results = KnuthMorrisPratt.search("abcdef", "zfc");
        assertThat(results.length, is(0));
    }

    @Test
    public void testOnNoMatchForSingleCharTarget() {
        int[] results = KnuthMorrisPratt.search("abcdef", "z");
        assertThat(results.length, is(0));
    }

    @Test
    public void testOnEmptyTarget() {
        int[] results = KnuthMorrisPratt.search("abcdef", "");
        assertThat(results.length, is(0));
    }

    @Test
    public void testOnTextShortedThanTarget() {
        int[] results = KnuthMorrisPratt.search("abcdef", "abcdefghi");
        assertThat(results.length, is(0));
    }

}