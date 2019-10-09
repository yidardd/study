package www;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/9 17:36
 * @Version 1.0
 * @Description AddTwoNumbers
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 执行用时 :
 * 2 ms
 * , 在所有 Java 提交中击败了
 * 99.99%
 * 的用户
 * 内存消耗 :
 * 42.2 MB
 * , 在所有 Java 提交中击败了
 * 92.64%
 * 的用户
 *
 *
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(9);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(9);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(9);
        listNode3.next = listNode4;


        ListNode listNode11 = new ListNode(9);
        ListNode listNode = addTwoNumbers(listNode1, listNode11);
        ListNode listNode22 = addTwoNumbers2(listNode1, listNode11);

        System.out.println(1);

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        boolean liHasNext = true;
        boolean l2HasNext = true;

        int sum = 0;
        ListNode listNode = new ListNode(0);
        ListNode endlistNode = new ListNode(0);
        listNode.next = endlistNode;
        while (liHasNext || l2HasNext || sum > 0) {
            int val = 0;
            int va2 = 0;
            if (liHasNext) {
                val = l1.val;
                l1 = l1.next;
                if (l1 == null) {
                    liHasNext = false;
                }
            }
            if (l2HasNext) {
                va2 = l2.val;
                l2 = l2.next;
                if (l2 == null) {
                    l2HasNext = false;
                }
            }

            sum += val + va2;
            ListNode listNode1 = new ListNode(sum % 10);
            endlistNode.next = listNode1;
            endlistNode = listNode1;
            sum /= 10;
        }
        return listNode.next.next;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return root.next;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
