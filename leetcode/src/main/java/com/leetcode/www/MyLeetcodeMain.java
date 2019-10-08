package com.leetcode.www;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/8 17:34
 * @Version 1.0
 * @Description MyLeetcodeMain
 */
public class MyLeetcodeMain {

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{3, 2, 4}, 6);

        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
