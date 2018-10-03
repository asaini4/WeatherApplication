package com.hcl.androidweatherapp.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JUnitTestCaseClass {

    @BeforeClass
    public static void classSetup() {
        System.out.println(" In Class Setup - Run Before Class");
    }

    @AfterClass
    public static void classTearDown() {
        System.out.println(" In Class Tear Down - Run After Class");

    }

    @Before
    public void testSetup() {
        System.out.println(" In Test Setup - Run Before Test");

    }

    @After
    public void testTearDown() {
        System.out.println(" In Test Tear Down - Run After Test");

    }
}
