class Solution(object):
    def twoSum(self, nums, target):
        length = len(nums)
        for i in xrange(0, length - 1):
            a = nums[i]
            for j in xrange(i + 1, length):
                b = nums[j]
                if a + b == target:
                    return [i, j]

if __name__ == '__main__':
    solution = Solution()
    print solution.twoSum([2, 7, 11, 15], 9)
    print solution.twoSum([2, 3, 4], 6)
    print solution.twoSum([0, 4, 3, 0], 0)
