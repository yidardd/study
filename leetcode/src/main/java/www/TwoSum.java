package www;

import java.util.HashMap;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/8 17:34
 * @Version 1.0
 * @Description MyLeetcodeMain
 *
 *      * 示例:
 *      *
 *      * 给定 nums = [2, 7, 11, 15], target = 9
 *      *
 *      * 因为 nums[0] + nums[1] = 2 + 7 = 9
 *      * 所以返回 [0, 1]
 *      *
 *      * 来源：力扣（LeetCode）
 *      * 链接：https://leetcode-cn.com/problems/two-sum
 *      * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] ints = twoSum1(new int[]{3, 2, 4}, 6);
        int[] ints2 = twoSum2(new int[]{3, 2, 4}, 6);

        System.out.println(1);

    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            if (map.containsKey(i1)) {
                return new int[]{i,map.get(i1)};
            }
            map.put(nums[i], i);
        }

        return null;
    }

}
