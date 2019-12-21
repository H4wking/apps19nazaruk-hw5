package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import java.util.ArrayList;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> stream;

    private AsIntStream() {
        stream = new ArrayList<>();
    }

    private AsIntStream(int... numbers) {
        stream = new ArrayList<>();
        for (Integer num : numbers) {
            stream.add(num);
        }
    }

    private AsIntStream(ArrayList<Integer> numbers) {
        stream = new ArrayList<>(numbers);
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return (double) sum() / count();
    }

    @Override
    public Integer max() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Integer m = Integer.MIN_VALUE;
        for (Integer num : stream) {
            if (num > m) {
                m = num;
            }
        }
        return m;
    }

    @Override
    public Integer min() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Integer m = Integer.MAX_VALUE;
        for (Integer num : stream) {
            if (num < m) {
                m = num;
            }
        }
        return m;
    }

    @Override
    public long count() {
        return stream.size();
    }

    @Override
    public Integer sum() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int s = 0;
        for (Integer num : stream) {
            s += num;
        }
        return s;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream newStream = new AsIntStream();
        for (int num : stream) {
            if (predicate.test(num)) {
                newStream.stream.add(num);
            }
        }
        return newStream;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int num : stream) {
            action.accept(num);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        AsIntStream newStream = new AsIntStream();
        for (int num : stream) {
            newStream.stream.add(mapper.apply(num));
        }
        return newStream;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<IntStream> arr = new ArrayList<>();
        for (int num : stream) {
            arr.add(func.applyAsIntStream(num));
        }

        AsIntStream newStream = new AsIntStream();
        for (IntStream st : arr) {
            for (int num : st.toArray()) {
                newStream.stream.add(num);
            }
        }
        return newStream;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int num : stream) {
            result = op.apply(result, num);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int s = stream.size();
        int[] arr = new int[s];
        for (int i = 0; i < s; i++) {
            arr[i] = stream.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        AsIntStream a = new AsIntStream(1, 2, 3, 4);
        System.out.println(a.average());
    }
}
