package ru.antonovcode.java.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Test for {@link com.noveogroup.java.util.ValueGenerator}.
 *
 * @author Mikhail Khorkov
 */
public class ValueGeneratorTest {
    private ValueGenerator instance;

    @Before
    public void init() {
        instance = new ValueGenerator();
    }

    @Test
    public void testNextWord() throws Exception {
        final int len = 28;
        final String randString = instance.nextWord(len);
        Assert.assertTrue(randString != null);
        Assert.assertEquals(28, randString.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextWordFail() throws Exception {
        final int len = -1;
        instance.nextWord(len);
    }

    @Test
    public void testNextEmail() throws Exception {
        final String randEmail = instance.nextEmail();
        Assert.assertTrue(randEmail.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+"));
    }

    @Test
    public void testNextDate() throws Exception {
        final Date randDate = instance.nextDate();
        Assert.assertTrue(randDate != null);
    }
}
