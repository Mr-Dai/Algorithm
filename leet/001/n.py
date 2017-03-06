import unittest

class Solution(object):
    def twoSum(self, nums, target):
        table = dict()
        for index, num in enumerate(nums):
            if table.has_key(target - num):
                return [table[target - num], index]
            table[num] = index

class TestCases(unittest.TestCase):
    solution = Solution()

    def test(self):
        self.assertEqual(self.solution.twoSum([2, 7, 11, 15], 9), [0, 1])
        self.assertEqual(self.solution.twoSum([2, 3, 4], 6), [0, 2])
        self.assertEqual(self.solution.twoSum([0, 4, 3, 0], 0), [0, 3])

if __name__ == '__main__':
    unittest.main()
