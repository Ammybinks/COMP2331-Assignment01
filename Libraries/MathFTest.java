    package Libraries;

    import java.util.ArrayList;

    import static org.junit.Assert.*;
    import org.junit.After;
    import org.junit.Before;
    import org.junit.Test;

    /**
    * The test class MathFTest.
    *
    * @author  Nye Blythe
    * @version 1.12
    */
    public class MathFTest
    {
    /**
     * Default constructor for test class MathFTest
     */
    public MathFTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void clampMin_0_1_Expect1()
    {
        assertEquals(1, MathF.clampMin(0, 1), 0.1);
    }

    @Test
    public void clampMin_1_0_Expect1()
    {
        assertEquals(1, MathF.clampMin(1, 0), 0.1);
    }

    @Test
    public void clampMin_1_2_Expect2()
    {
        assertEquals(2, MathF.clampMin(1, 2), 0.1);
    }

    @Test
    public void clampMin_2_1_Expect2()
    {
        assertEquals(2, MathF.clampMin(2, 1), 0.1);
    }

    @Test
    public void clampMin_1_2_Negative_Expect1()
    {
        assertEquals(-1, MathF.clampMin(-1, -2), 0.1);
    }

    @Test
    public void clampMin_2_1_Negative_Expect1()
    {
        assertEquals(-1, MathF.clampMin(-2, -1), 0.1);
    }



    @Test
    public void clampMax_0_1_Expect0()
    {
        assertEquals(0, MathF.clampMax(0, 1), 0.1);
    }

    @Test
    public void clampMax_1_0_Expect0()
    {
        assertEquals(0, MathF.clampMax(1, 0), 0.1);
    }

    @Test
    public void clampMax_1_2_Expect1()
    {
        assertEquals(1, MathF.clampMax(1, 2), 0.1);
    }

    @Test
    public void clampMax_2_1_Expect1()
    {
        assertEquals(1, MathF.clampMax(2, 1), 0.1);
    }

    @Test
    public void clampMax_1_2_Negative_Expect2()
    {
        assertEquals(-2, MathF.clampMax(-1, -2), 0.1);
    }

    @Test
    public void clampMax_2_1_Negative_Expect2()
    {
        assertEquals(-2, MathF.clampMax(-2, -1), 0.1);
    }



    @Test
    public void clamp_1_2_Expect1()
    {
        assertEquals(1, MathF.clamp(1, 2), 0.1);
    }

    @Test
    public void clamp_2_1_Expect1()
    {
        assertEquals(1, MathF.clamp(2, 1), 0.1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void clamp_1_2_Negative_ExpectException()
    {
        MathF.clamp(-1, -2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void clamp_2_1_Negative_ExpectException()
    {
        MathF.clamp(-2, -1);
    }



    @Test
    public void clamp_1_3_0_Expect1()
    {
        assertEquals(1, MathF.clamp(1, 3, 0), 0.1);
    }

    @Test
    public void clamp_1_3_1_Expect1()
    {
        assertEquals(1, MathF.clamp(1, 3, 1), 0.1);
    }

    @Test
    public void clamp_1_3_2_Expect2()
    {
        assertEquals(2, MathF.clamp(1, 3, 2), 0.1);
    }

    @Test
    public void clamp_1_3_3_Expect3()
    {
        assertEquals(3, MathF.clamp(1, 3, 3), 0.1);
    }

    @Test
    public void clamp_1_3_4_Expect3()
    {
        assertEquals(3, MathF.clamp(1, 3, 4), 0.1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void clamp_2_1_0_ExpectException()
    {
        assertEquals(0, MathF.clamp(2, 1, 0), 0.1);
    }

    @Test
    public void rLerp__0_1_2_3_4__1_Expect4()
    {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0d);
        list.add(1d);
        list.add(2d);
        list.add(3d);
        list.add(4d);

        assertEquals(4, MathF.rLerp(list, 1), 0.1);
    }

    @Test
    public void rLerp__0_1_2_3_4__0_25_Expect1()
    {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0d);
        list.add(1d);
        list.add(2d);
        list.add(3d);
        list.add(4d);

        assertEquals(1, MathF.rLerp(list, 0.25), 0.1);
    }

    @Test
    public void rLerp__0_1_2_3_4__0_5_Expect2()
    {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0d);
        list.add(1d);
        list.add(2d);
        list.add(3d);
        list.add(4d);

        assertEquals(2, MathF.rLerp(list, 0.5), 0.1);
    }

    @Test
    public void rLerp__0_1_2_3_4__0_Expect0()
    {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0d);
        list.add(1d);
        list.add(2d);
        list.add(3d);
        list.add(4d);

        assertEquals(0, MathF.rLerp(list, 0), 0.1);
    }

    @Test
    public void rLerp__17_20_7_13_15__0_5_Expect12_875()
    {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(17d);
        list.add(20d);
        list.add(7d);
        list.add(13d);
        list.add(15d);

        assertEquals(12.875, MathF.rLerp(list, 0.5), 0.1);
    }

    @Test
    public void lerp__17_20_7_13_15__0_5_Expect12_875()
    {
        double val = MathF.lerp(
                        MathF.lerp(
                            MathF.lerp(
                                MathF.lerp(17, 20, 0.5),
                                MathF.lerp(20, 7, 0.5),
                                0.5),
                            MathF.lerp(
                                MathF.lerp(20, 7, 0.5),
                                MathF.lerp(7, 13, 0.5),
                                0.5),
                            0.5),
                        MathF.lerp(
                            MathF.lerp(
                                MathF.lerp(7, 13, 0.5),
                                MathF.lerp(13, 15, 0.5),
                                0.5),
                            MathF.lerp(
                                MathF.lerp(7, 13, 0.5),
                                MathF.lerp(13, 15, 0.5),
                                0.5),
                            0.5),
                        0.5);

        assertEquals(12.875, val, 0.1);
    }
}
