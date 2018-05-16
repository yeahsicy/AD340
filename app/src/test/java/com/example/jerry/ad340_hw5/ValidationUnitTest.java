package com.example.jerry.ad340_hw5;

import org.junit.Test;

import static org.junit.Assert.*;


public class ValidationUnitTest {
    @Test
    public void Text_isEmpty() {
        String info = util.Text_entry_validation_info("");
        assertEquals("Text-entry field is empty", info);
    }

    @Test
    public void Text_isNotEmpty() {
        String info = util.Text_entry_validation_info("test");
        assertEquals("", info);
    }
}
