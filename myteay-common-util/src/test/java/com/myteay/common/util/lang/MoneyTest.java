/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.common.util.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author danlley
 * @version $Id: MoneyTest.java, v 0.1 Apr 20, 2019 12:01:38 PM danlley Exp $
 */
public class MoneyTest {

    @Test
    public void tesIsSmallThanCase001() {
        Money money = new Money("3.00");
        assertEquals(false, money.isSmallThan(new Money(3, 0)));
        assertEquals(false, money.isSmallThan(new Money("3.0")));
        assertEquals(false, money.isSmallThan(new Money("3.00")));
        assertEquals(false, money.isSmallThan(new Money(3)));
        assertEquals(true, money.isSmallThan(new Money(3, 1)));
        assertEquals(true, money.isSmallThan(new Money("3.1")));
        assertEquals(true, money.isSmallThan(new Money("3.01")));
        assertEquals(true, money.isSmallThan(new Money(4)));
    }

    @Test
    public void tesIsEqualCase002() {
        Money money = new Money("3.00");
        assertEquals(true, money.isEqual(new Money(3, 0)));
        assertEquals(true, money.isEqual(new Money("3.0")));
        assertEquals(true, money.isEqual(new Money("3.00")));
        assertEquals(true, money.isEqual(new Money(3)));
        assertEquals(false, money.isEqual(new Money(3, 1)));
        assertEquals(false, money.isEqual(new Money("3.1")));
        assertEquals(false, money.isEqual(new Money("3.01")));
        assertEquals(false, money.isEqual(new Money(4)));
    }

    @Test
    public void tesIsEqualCase001() {
        Money money = new Money("3.14");
        assertEquals(true, money.isEqual(new Money(3, 14)));
        assertEquals(true, money.isEqual(new Money("3.14")));
        assertEquals(false, money.isEqual(new Money(3)));
    }

    @Test
    public void tesIsGratterThanCase001() {
        Money money = new Money("3.14");
        assertEquals(true, money.isBiggerThan(new Money(3)));
        assertEquals(true, money.isBiggerThan(new Money(3, 13)));
        assertEquals(false, money.isBiggerThan(new Money(3, 14)));
    }

    @Test
    public void testMultiplyCase007() {
        Money money = new Money("3.14");
        money.multiply(-2);
        assertEquals("-6.28", money.toString());
    }

    @Test
    public void testMultiplyCase006() {
        Money money = new Money("3.14");
        money.multiply(2);
        assertEquals("6.28", money.toString());
    }

    @Test
    public void testMultiplyCase005() {
        Money money = new Money("3.14");
        money.multiply(1);
        assertEquals("3.14", money.toString());
    }

    @Test
    public void testMultiplyCase004() {
        Money money = new Money("3.14");
        money.multiply(0);
        assertEquals("0.00", money.toString());
    }

    @Test
    public void testMultiplyCase003() {
        Money money = new Money("3.14");
        money.multiply(-1);
        assertEquals("-3.14", money.toString());
    }

    @Test
    public void testMultiplyCase002() {
        Money money = new Money();
        money.multiply(-1);
        assertEquals("0.00", money.toString());
    }

    @Test
    public void testMultiplyCase001() {
        Money money = new Money();
        money.multiply(999);
        assertEquals("0.00", money.toString());
    }

    @Test
    public void testNewCase005() {
        Money money = new Money();
        assertEquals("0.00", money.toString());
    }

    @Test
    public void testReduceCase004() {
        Money money = new Money("13.74");
        money.reduce(new Money("13.75"));
        assertEquals("-0.01", money.toString());
    }

    @Test
    public void testReduceCase003() {
        Money money = new Money("13.74");
        money.reduce(new Money("12.75"));
        assertEquals("0.99", money.toString());
    }

    @Test
    public void testReduceCase002() {
        Money money = new Money("13.74");
        money.reduce(new Money("0.25"));
        assertEquals("13.49", money.toString());
    }

    @Test
    public void testReduceCase001() {
        Money money = new Money("13.74");
        money.reduce(new Money("0.75"));
        assertEquals("12.99", money.toString());
    }

    @Test
    public void testAddCase005() {
        Money money = new Money("13.74");
        money.add(new Money("0.25"));
        assertEquals("13.99", money.toString());
    }

    @Test
    public void testAddCase004() {
        Money money = new Money("13.75");
        money.add(new Money("0.25"));
        assertEquals("14.00", money.toString());
    }

    @Test
    public void testAddCase003() {
        Money money = new Money("13");
        money.add(new Money("0.25"));
        assertEquals("13.25", money.toString());
    }

    @Test
    public void testAddCase002() {
        Money money = new Money("13");
        money.add(null);
        assertEquals("13.00", money.toString());
    }

    @Test
    public void testAddCase001() {
        Money money = new Money("13");
        money.add(new Money());
        assertEquals("13.00", money.toString());
    }

    @Test
    public void testStringStyleCase001() {
        Money money = new Money("13");
        long yuan = money.getYuan();
        assertEquals(13, yuan);

        long cent = money.getCent();
        assertEquals(0, cent);
    }

    @Test
    public void testStringStyleCase010() {
        assertEquals("13.00", new Money("13").toString());
    }

    @Test
    public void testStringStyleCase014() {
        try {
            new Money(0, 100).toString();
        } catch (NumberFormatException e) {
            assertEquals("金额的小数点后尾数不合法，金额小数点后不允许出现2位以上的小数", e.getMessage());
        }
    }

    @Test
    public void testStringStyleCase013() {
        try {
            new Money("0.333").toString();
        } catch (NumberFormatException e) {
            assertEquals("金额的小数点后尾数不合法，金额小数点后不允许出现2位以上的小数", e.getMessage());
        }
    }

    @Test
    public void testStringStyleCase012() {
        assertEquals("0.30", new Money("0.3").toString());
    }

    @Test
    public void testStringStyleCase011() {
        assertEquals("0.00", new Money("0").toString());
    }

    @Test
    public void testStringStyleCase005() {
        Money money = new Money("13.45");
        long yuan = money.getYuan();
        assertEquals(13, yuan);

        long cent = money.getCent();
        assertEquals(45, cent);
    }

    @Test
    public void testStringStyleCase002() {
        Money money = new Money(null);
        long yuan = money.getYuan();
        assertEquals(0, yuan);

        long cent = money.getCent();
        assertEquals(0, cent);
    }

    @Test
    public void testStringStyleCase003() {
        try {
            new Money(".0");
        } catch (NumberFormatException e) {
            assertEquals("金额格式异常，无法完成Money类的初始化", e.getMessage());
        }
    }

    @Test
    public void testStringStyleCase004() {
        try {
            new Money(".0.");
        } catch (NumberFormatException e) {
            assertEquals("金额格式异常，无法完成Money类的初始化", e.getMessage());
        }
    }

    @Test
    public void testStringStyleCase006() {
        try {
            new Money(".0.0");
        } catch (NumberFormatException e) {
            assertEquals("金额格式异常，无法完成Money类的初始化", e.getMessage());
        }
    }

    @Test
    public void testStringStyleCase007() {
        Money money = new Money();
        money.setCent(3);
        money.setYuan(9);
        assertEquals(3, money.getCent());
        assertEquals(9, money.getYuan());
    }

    @Test
    public void testStringStyleCase008() {
        Money money = new Money(9, 8);
        assertEquals(8, money.getCent());
        assertEquals(9, money.getYuan());
    }

    @Test
    public void testStringStyleCase009() {
        Money money = new Money(9);
        assertEquals(9, money.getYuan());
    }

}
