import java.util.function.IntFunction;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Warmup {

    public static void main(String[] args) {
        System.out.println("Hello World");

    }

    @Test
    public void test() {
        String[] expected = { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14",
                "FizzBuzz" };
        Assertions.assertArrayEquals(expected, fizzBuzz(15));
    }

    public String[] fizzBuzz(int n) {
        IntFunction<String> fizzBuzz = i -> i % 15 == 0 ? "FizzBuzz"
                : i % 5 == 0 ? "Buzz"
                        : i % 3 == 0 ? "Fizz"
                                : String.valueOf(i);
        return IntStream.rangeClosed(1, n).mapToObj(fizzBuzz).toArray(String[]::new);
    }

}