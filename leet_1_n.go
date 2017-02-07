package main

import (
	"fmt"
)

func twoSum(nums []int, target int) []int {
	valueToIndex := make(map[int]int)
	length := len(nums)
	for i := 0; i < length; i++ {
		index, ok := valueToIndex[target-nums[i]]
		if ok {
			return []int{index, i}
		}
		valueToIndex[nums[i]] = i
	}
	return []int{}
}

func main() {
	fmt.Println(twoSum([]int{2, 7, 11, 15}, 9))
}
