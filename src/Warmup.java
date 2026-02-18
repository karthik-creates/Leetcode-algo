import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public int fib(int n) {
        return Stream.iterate(new int[] { 0, 1 }, arr -> new int[] { arr[1], arr[0] + arr[1] })
                .skip(n).map(arr -> arr[0]).findFirst().get();
    }

    @Test
    public void testFib() {
        Assertions.assertEquals(3, fib(4));
    }

    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    @Test
    public void testDefangIPaddr() {
        Assertions.assertEquals("1[.]1[.]1[.]1", defangIPaddr("1.1.1.1"));
    }

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];
        return nums;
    }

    @Test
    public void testRunningSum() {
        int[] nums = { 1, 2, 3, 4 };
        int[] expected = { 1, 3, 6, 10 };
        Assertions.assertArrayEquals(expected, runningSum(nums));
    }

    public int numIdenticalPairs(int[] nums) {
        return Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream().mapToInt(Long::intValue).filter(count -> count > 1)
                .map(count -> count * (count - 1) / 2)
                .sum();
    }

    @Test
    public void testNumIdenticalPairs() {
        int[] nums = { 1, 2, 3, 1, 1, 3 };
        Assertions.assertEquals(4, numIdenticalPairs(nums));
    }

    public int maximumWealth(int[][] accounts) {
        return Arrays.stream(accounts).mapToInt(arr -> Arrays.stream(arr).sum()).max().getAsInt();
    }

    @Test
    public void testMaximumWealth() {
        int[][] accounts = { { 1, 2, 3 }, { 4, 5, 6 } };
        Assertions.assertEquals(15, maximumWealth(accounts));
    }

    public int[] buildArray(int[] nums) {
        return IntStream.range(0, nums.length).map(i -> nums[nums[i]]).toArray();
    }

    @Test
    public void testBuildArray() {
        int[] nums = { 0, 2, 1, 3 };
        int[] expected = { 0, 1, 2, 3 };
        Assertions.assertArrayEquals(expected, buildArray(nums));
    }

    public int[] getConcatenation(int[] nums) {
        return Stream.of(nums, nums).flatMapToInt(Arrays::stream).toArray();
    }

    @Test
    public void testGetConcatenation() {
        int[] nums = { 1, 2, 3, 4 };
        int[] expected = { 1, 2, 3, 4, 1, 2, 3, 4 };
        Assertions.assertArrayEquals(expected, getConcatenation(nums));
    }

    public double findMaxAverage(int[] nums, int k) {
        IntStream.range(0, nums.length - k + 1)
                .mapToDouble(i -> IntStream.range(i, i + k).map(j -> nums[j]).sum() / (double) k)
                .max().getAsDouble();

        int windowSum = IntStream.range(0, k).map(j -> nums[j]).sum();
        double maximumAverage = windowSum / (double) k;
        for (int i = 0; i < nums.length - k; i++) {
            windowSum = windowSum - nums[i] + nums[i + k];// window slides
            maximumAverage = Math.max(maximumAverage, windowSum / (double) k);
        }
        return maximumAverage;
    }

    @Test
    public void testFindMaxAverage() {
        int[] nums = { 1, 12, -5, -6, 50, 3 };
        int k = 4;
        Assertions.assertEquals(12.75, findMaxAverage(nums, k));
    }

    public int numKLenSubstrNoRepeats(String s, int k) {
        return IntStream.range(0, s.length() - k + 1)
                .mapToObj(i -> s.substring(i, i + k))
                .filter(substr -> substr.chars().distinct().count() == k)
                .toList().size();
    }

    @Test
    public void testNumKLenSubstrNoRepeats() {
        String s = "havefunonleetcode";
        int k = 5;
        Assertions.assertEquals(6, numKLenSubstrNoRepeats(s, k));
    }

    public List<Integer> findAnagrams(String s, String p) {
        // they used map approach.comparing smap and pmap
        Function<String, List<Integer>> stringToList = str -> str.chars().sorted().boxed().toList();
        var pList = stringToList.apply(p);
        return IntStream.range(0, s.length() - p.length() + 1)
                .filter(i -> stringToList.apply(s.substring(i, i + p.length())).equals(pList))
                .boxed().toList();
    }

    @Test
    public void testFindAnagrams() {
        String s = "cbaebabacd", p = "abc";
        List<Integer> expected = List.of(0, 6);
        Assertions.assertEquals(expected, findAnagrams(s, p));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length())
            return false;
        Function<String, List<Integer>> stringToList = str -> str.chars().sorted().boxed().toList();
        var pList = stringToList.apply(s1);
        return IntStream.rangeClosed(0, s2.length() - s1.length())
                .filter(i -> stringToList.apply(s2.substring(i, i + s1.length())).equals(pList))
                .boxed().toList().size() > 0;
    }

    @Test
    public void testCheckInclusion() {
        String s1 = "ab", s2 = "eidbaooo";
        Assertions.assertTrue(checkInclusion(s1, s2));
    }

    public int binarySearch(int[] nums, int target) {
        for (int start = 0, end = nums.length - 1, mid = 0; end >= start; mid = (start + end) / 2) {
            switch (Integer.compare(nums[mid], target)) {
                case 0:
                    return mid;
                case 1:
                    end = mid - 1;
                    break;
                case -1:
                    start = mid + 1;
                    break;
            }
        }
        return -1;
    }

    @Test
    public void testBinarySearch() {
        Assertions.assertEquals(0, binarySearch(new int[] { 5 }, 5));
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        int target = 9;
        Assertions.assertEquals(4, binarySearch(nums, target));
        target = 2;
        Assertions.assertEquals(-1, binarySearch(nums, target));
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        if (target < letters[start] || target >= letters[end])
            return letters[start];
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] <= target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return letters[start];
    }

    @Test
    public void testNextGreatestLetter() {
        char[] letters = { 'c', 'f', 'j' };
        char target = 'a';
        Assertions.assertEquals('c', nextGreatestLetter(letters, target));
    }

    public int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[mid + 1]) {
                if ((mid - start) % 2 == 0)
                    start = mid + 2;
                else
                    end = mid - 1;
            } else if (nums[mid] == nums[mid - 1]) {
                if ((end - mid) % 2 == 0)
                    end = mid - 2;
                else
                    start = mid + 1;
            } else
                return nums[mid];
        }
        return nums[start];
    }

    @Test
    public void testSingleNonDuplicate() {
        Assertions.assertEquals(2, singleNonDuplicate(new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 }));
    }

    public int peakIndexInMountainArray(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[mid + 1])
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    @Test
    public void testPeakIndexInMountainArray() {
        int[] arr = { 0, 1, 0 };
        Assertions.assertEquals(1, peakIndexInMountainArray(arr));
    }
}