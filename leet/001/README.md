# Two Sum

Given an array of integers, return __indices__ of the two numbers such that they add up to a specific target.

You may assume that each input would have <b><i>exactly</i></b> one solution, and you may not use the same element twice.

__Example:__

<pre>
Given nums = [2, 7, 11, 15], target = 9,

Because nums[<b>0</b>] + nums[<b>1</b>] = 2 + 7 = 9,
return [<b>0</b>, <b>1</b>].
</pre>

Check it on [LeetCode](https://leetcode.com/problems/two-sum/)

## Solution

### Step 1: O(n^2) Brute Force Searching

As it is guaranteed to have only one solution for the given array, one can easily come up with a brute-force searching solution
with a two-level nested loop, whose time complexity is O(n^2):

```go
func twoSum(nums []int, target int) []int {
	length := len(nums)
	for i := 0; i < length-1; i++ {
		for j := i + 1; j < length; j++ {
			if nums[i]+nums[j] == target {
				return []int{i, j}
			}
		}
	}
	return []int{}
}
```

### Step 2: O(n) Hash Searching

Consider the array as an incoming stream of integers, we can modify the upper solution to search potential matching each time
when a new integer arrives:

```go
func twoSum(nums []int, target int) []int {
	length := len(nums)
	for i := 1; i < length; i++ {
		for j := 0; j < i; j++ {
			if nums[j]+nums[i] == target {
				return []int{j, i}
			}
		}
	}
	return []int{}
}
```

The inner searching loop can be replaced with a searching on a hash table, whose time complexity would be O(1) instead of O(n).
In details, we can record every integer that fails to find its match in a Go `map` from its value to its index for future search:

```go
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
```

And the time complexity reduces from O(n^2) to O(n).
