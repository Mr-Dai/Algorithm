package com.mrdai.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test cases for {@link Bits}.
 */
public class BitsTest {

    @Test
    public void testToHex() {
        assertThat(new Bits(0).toHex(), is("0"));
        assertThat(new Bits(5).toHex(), is("5"));
        assertThat(new Bits(15).toHex(), is("F"));
        assertThat(new Bits(16).toHex(), is("10"));

        assertThat(new Bits(-1).toHex(), is("-1"));
        assertThat(new Bits(-5).toHex(), is("-5"));
        assertThat(new Bits(-15).toHex(), is("-F"));
        assertThat(new Bits(-16).toHex(), is("-10"));

        assertThat(new Bits(Integer.MAX_VALUE).toHex(), is("7FFFFFFF"));
        assertThat(new Bits(Integer.MIN_VALUE).toHex(), is("-80000000"));

        assertThat(new Bits(Long.MAX_VALUE).toHex(), is("7FFFFFFFFFFFFFFF"));
        assertThat(new Bits(Long.MIN_VALUE).toHex(), is("-8000000000000000"));
    }

    @Test
    public void testIncrement() {
        assertThat(new Bits(0).increment().toHex(), is("1"));
        assertThat(new Bits(-1).increment().toHex(), is("0"));
        assertThat(new Bits(15).increment().toHex(), is("10"));
        assertThat(new Bits(-15).increment().toHex(), is("-E"));

        assertThat(new Bits(Integer.MAX_VALUE).increment().toHex(), is("80000000"));
        assertThat(new Bits(Integer.MIN_VALUE).increment().toHex(), is("-7FFFFFFF"));

        assertThat(new Bits(Long.MAX_VALUE).increment().toHex(), is("8000000000000000"));
        assertThat(new Bits(Long.MIN_VALUE).increment().toHex(), is("-7FFFFFFFFFFFFFFF"));
    }

    @Test
    public void testShiftLeft() {
        assertThat(new Bits(0).shiftLeft(1).toHex(), is("0"));
        assertThat(new Bits(1).shiftLeft(1).toHex(), is("2"));

        assertThat(new Bits(Integer.MAX_VALUE).shiftLeft(1).toHex(), is("FFFFFFFE"));
        assertThat(new Bits(Long.MAX_VALUE).shiftLeft(1).toHex(), is("FFFFFFFFFFFFFFFE"));

        assertThat(new Bits(Integer.MIN_VALUE).shiftLeft(1).toHex(), is("-100000000"));
        assertThat(new Bits(Long.MIN_VALUE).shiftLeft(1).toHex(), is("-10000000000000000"));

        assertThat(new Bits(0b1).shiftLeft(2).toHex(), is("4"));
        assertThat(new Bits(0b1).shiftLeft(5).toHex(), is("20"));
    }

    @Test
    public void testShiftRight() {
        assertThat(new Bits(0).shiftRight(1).toHex(), is("0"));
        assertThat(new Bits(1).shiftRight(1).toHex(), is("0"));

        assertThat(new Bits(Integer.MAX_VALUE).shiftRight(1).toHex(), is("3FFFFFFF"));
        assertThat(new Bits(Long.MAX_VALUE).shiftRight(1).toHex(), is("3FFFFFFFFFFFFFFF"));

        assertThat(new Bits(Integer.MIN_VALUE).shiftRight(1).toHex(), is("-40000000"));
        assertThat(new Bits(Long.MIN_VALUE).shiftRight(1).toHex(), is("-4000000000000000"));

        assertThat(new Bits(0b100000).shiftRight(4).toHex(), is("2"));
        assertThat(new Bits(0b100000).shiftRight(3).toHex(), is("4"));
        assertThat(new Bits(0xdeadcafedeadbeefL).shiftRight(64).toHex(), is("0"));
    }
}