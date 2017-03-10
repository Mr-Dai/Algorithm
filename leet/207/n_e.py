import unittest

class Solution(object):
    def canFinish(self, numCourses, prerequisites):
        self.edges = []
        self.marked = []
        self.onStack = []
        self.hasCycle = False
        for i in xrange(numCourses):
            self.marked.append(False)
            self.onStack.append(False)
            self.edges.append([])
        for pair in prerequisites:
            self.edges[pair[0]].append(pair[1])
        for i in xrange(numCourses):
            if not self.hasCycle and not self.marked[i]:
                self.dfs(i)
        return not self.hasCycle

    def dfs(self, source):
        self.marked[source] = True
        self.onStack[source] = True
        for next in self.edges[source]:
            if self.hasCycle:
                return
            elif self.onStack[next]:
                self.hasCycle = True
                return
            elif not self.marked[next]:
                self.dfs(next)
        self.onStack[source] = False


class TestCases(unittest.TestCase):
    solution = Solution()

    def test(self):
        self.assertEqual(self.solution.canFinish(1, []), True)
        self.assertEqual(self.solution.canFinish(2, [[1, 0]]), True)
        self.assertEqual(self.solution.canFinish(2, [[1, 0], [0, 1]]), False)

if __name__ == '__main__':
    unittest.main()
