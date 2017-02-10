# Add Two Numbers

You are given two __non-empty__ linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: `(2 -> 4 -> 3) + (5 -> 6 -> 4)`
Output: `7 -> 0 -> 8`

Check it on [LeetCode](https://leetcode.com/problems/add-two-numbers/)

## Solution

Easy enough for anyone who knows how to iterate a linked list. Programming novices should learn to finish this problem in C or C++.

```go
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	prev := new(ListNode)
	head := prev
	carry := 0
	for l1 != nil || l2 != nil || carry != 0 {
		cur := new(ListNode)
		sum := carry
		if l1 != nil {
			sum += l1.Val
		}
		if l2 != nil {
			sum += l2.Val
		}
		cur.Val = sum % 10
		carry = sum / 10
		prev.Next = cur
		prev = cur

		if l1 != nil {
			l1 = l1.Next
		}
		if l2 != nil {
			l2 = l2.Next
		}
	}
	return head.Next
}
```
