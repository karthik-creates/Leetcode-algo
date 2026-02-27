public class ReproduceBinarySearch {
    public static void main(String[] args) {
        ReproduceBinarySearch solver = new ReproduceBinarySearch();

        // Test case 1: From user's test
        int[] nums1 = { -1, 0, 3, 5, 9, 12 };
        int target1 = 9;
        int result1 = solver.binarySearch(nums1, target1);
        System.out.println("Test 1 (Target 9): Expected 4, Got " + result1);

        // Test case 2: Target not found
        int target2 = 2;
        int result2 = solver.binarySearch(nums1, target2);
        System.out.println("Test 2 (Target 2): Expected -1, Got " + result2);

        // Test case 3: Single element match
        int[] nums3 = { 5 };
        int target3 = 5;
        int result3 = solver.binarySearch(nums3, target3);
        System.out.println("Test 3 (Single Match): Expected 0, Got " + result3);

        // Test case 4: Single element no match
        int target4 = 1;
        int result4 = solver.binarySearch(nums3, target4);
        System.out.println("Test 4 (Single No Match): Expected -1, Got " + result4);

        // Test case 5: Empty array
        int[] nums5 = {};
        int target5 = 1;
        int result5 = solver.binarySearch(nums5, target5);
        System.out.println("Test 5 (Empty): Expected -1, Got " + result5);
    }

    public int binarySearch(int[] nums, int target) {
        System.out.println("Debug: Search in " + java.util.Arrays.toString(nums) + " for " + target);
        int targetIndex = -1;
        int iter = 0;
        for (int start = 0, end = nums.length - 1, mid = 0; end > start;) {
            iter++;
            if (iter > 20) {
                System.out.println("Debug: Forced break (potential infinite loop)");
                break;
            }
            mid = (start + (end)) / 2;
            System.out.println(
                    "  Iter " + iter + ": start=" + start + ", end=" + end + ", mid=" + mid + ", val=" + nums[mid]);

            if (nums[mid] == target) {
                targetIndex = mid;
                break;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            }
        }
        return targetIndex;
    }
}
