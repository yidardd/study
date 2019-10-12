package www;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/10 17:31
 * @Version 1.0
 * @Description LengthOfLongestSubstring
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {

        String s = "ohomm";
        int i = lengthOfLongestSubstring(s);
        int i2 = lengthOfLongestSubstring2(s);

        System.out.println(i);

    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int leftPon = 0;
        int maxLen = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = leftPon; j < i; j++) {
                if (chars[i] == chars[j]) {
                    maxLen = maxLen > (i - leftPon) ? maxLen : i - leftPon;
                    leftPon = j + 1;
                    break;
                }
            }

        }
        if (maxLen < chars.length - leftPon) {
            maxLen = chars.length - leftPon;
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        char[] chars = s.toCharArray();
        int leftIndex = 0;
        for (int j = 0; j < chars.length; j++) {
            for (int innerIndex = leftIndex; innerIndex < j; innerIndex++) {
                if (chars[innerIndex] == chars[j]) {
                    maxLength = Math.max(maxLength, j - leftIndex);
                    leftIndex = innerIndex + 1;
                    break;
                }
            }
        }
        return Math.max(chars.length - leftIndex, maxLength);
    }

    public static int lengthOfLongestSubstring3(String s) {
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        while (i < s.length()) {
            int pos = s.indexOf(s.charAt(i), flag);
            if (pos < i) {
                if (length > result) {
                    result = length;
                }
                if (result >= s.length() - pos - 1) {
                    return result;
                }
                length = i - pos - 1;
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return length;
    }

}
