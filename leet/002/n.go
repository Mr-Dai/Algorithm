package main

import (
	"fmt"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

/* The answer */
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

func numToList(num int) (head *ListNode) {
	head = new(ListNode)
	head.Val = num % 10
	current := head
	for num > 9 {
		num /= 10
		current.Next = new(ListNode)
		current = current.Next
		current.Val = num % 10
	}
	return
}

func printList(head *ListNode) {
	for head != nil {
		fmt.Print(head.Val)
		if head.Next != nil {
			fmt.Print(" -> ")
		}
		head = head.Next
	}
	fmt.Println()
}

func main() {
	printList(numToList(342))
	printList(addTwoNumbers(numToList(342), numToList(465)))
}
