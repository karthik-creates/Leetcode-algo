import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
        String[] expectedForFizzBuzz = { "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz",
                "13", "14",
                "FizzBuzz" };
        Assertions.assertArrayEquals(expectedForFizzBuzz, fizzBuzz(15));
    }

    public String[] fizzBuzz(int n) {
        IntFunction<String> fizzBuzz = i -> i % 15 == 0 ? "FizzBuzz"
                : i % 5 == 0 ? "Buzz"
                        : i % 3 == 0 ? "Fizz"
                                : String.valueOf(i);
        return IntStream.rangeClosed(1, n).mapToObj(fizzBuzz).toArray(String[]::new);
    }

    public boolean isPalindrome(int num) {
        if (num < 0)
            return false;
        int reversed = 0;
        for (int x = num; x > 0; x = x / 10)
            reversed = reversed * 10 + x % 10;
        return reversed == num;
    }

    @Test
    public void testIsPalindrome() {
        Assertions.assertTrue(isPalindrome(121));
        Assertions.assertFalse(isPalindrome(-121));
        Assertions.assertFalse(isPalindrome(10));
    }

    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        for (int num : map.keySet())
            if (map.get(num) == 1)
                return num;
        return -1;
    }

    @Test
    public void testSingleNumber() {
        int[] nums = { 2, 2, 1 };
        Assertions.assertEquals(1, singleNumber(nums));
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums)
            if (set.add(num) == false)
                return true;
        return false;
    }

    @Test
    public void testContainsDuplicate() {
        int[] nums = { 1, 2, 3, 4, 1, 1 };
        Assertions.assertTrue(containsDuplicate(nums));
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }
        for (int i : map.values())
            if (i != 0)
                return false;
        return true;
    }

    @Test
    public void testIsAnagram() {
        String s = "anagram", t = "nagaram";
        Assertions.assertTrue(isAnagram(s, t));
    }
}