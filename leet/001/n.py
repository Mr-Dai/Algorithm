class Solution(object):
    def twoSum(self, nums, target):
        table = dict()
        for index, num in enumerate(nums):
            if table.has_key(target - num):
                return [table[target - num], index]
            table[num] = index

if __name__ == '__main__':
    solution = Solution()
    print solution.twoSum([2, 7, 11, 15], 9)
    print solution.twoSum([2, 3, 4], 6)
