package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }
    
    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testAverage() {
        System.out.println("average");
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, result, 0.0001);
    }

    @Test
    public void testMax() {
        System.out.println("max");
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testMin() {
        System.out.println("min");
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        System.out.println("count");
        int expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        System.out.println("sum");
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testFilter() {
        System.out.println("filter");
        IntStream expResult = AsIntStream.of(1, 2, 3);
        IntStream result = intStream.filter(x -> x > 0);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test
    public void testMap() {
        System.out.println("map");
        IntStream expResult = AsIntStream.of(-2, 0, 2, 4, 6);
        IntStream result = intStream.map(x -> x * 2);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test
    public void testFlatMap() {
        System.out.println("flatMap");
        IntStream expResult = AsIntStream.of(-1, -2, 0, 0, 1, 2, 2, 4, 3, 6);
        IntStream result = intStream.flatMap(x -> AsIntStream.of(x, x * 2));
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test
    public void testReduce() {
        System.out.println("reduce");
        int expResult = 5;
        int result = intStream.reduce(0, (sum, x) -> sum += x);
        assertEquals(expResult, result);
    }
}
