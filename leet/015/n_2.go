package main

import (
	"fmt"
	"sort"
)

// What a dirty name!!!
func threeSum(nums []int) (result [][]int) {
	sort.Ints(nums)
	size := len(nums)
	for i := 0; i < size-2; i++ {
		if i != 0 && nums[i] == nums[i-1] { // Ignore duplicate elements
			continue
		}
		target := -nums[i]
		if target < 0 {
			return
		}
		a, b := i+1, size-1
		for a < b {
			switch {
			case nums[a]+nums[b] < target:
				a++
			case nums[a]+nums[b] > target:
				b--
			default:
				result = append(result, []int{nums[i], nums[a], nums[b]})
				for a < b && nums[a] == nums[a+1] {
					a++
				}
				for a < b && nums[b] == nums[b-1] {
					b--
				}
				a++
				b--
			}
		}
	}
	return
}

func main() {
	fmt.Println(threeSum([]int{-1, 0, 1, 2, -1, -4}))
}
