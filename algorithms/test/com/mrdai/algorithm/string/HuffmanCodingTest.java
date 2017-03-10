package com.mrdai.algorithm.string;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test cases for {@link HuffmanCoding}.
 */
public class HuffmanCodingTest {

    @Test
    public void testHuffmanCoding() {
        assertThat(new HuffmanCoding("beep boop beer!").toBits().length(), is("3ED12ACF89"));
    }

}