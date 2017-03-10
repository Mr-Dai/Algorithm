import unittest

class Solution(object):
    def findOrder(self, numCourses, prerequisites):
        self.edges = []
        self.marked = []
        self.onStack = []
        self.stack = []
        self.hasCycle = False
        for i in xrange(numCourses):
            self.marked.append(False)
            self.onStack.append(False)
            self.edges.append([])
        for pair in prerequisites:
            self.edges[pair[1]].append(pair[0])
        for i in xrange(numCourses):
            if not self.hasCycle and not self.marked[i]:
                self.dfs(i)
        if self.hasCycle:
            return []
        return self.stack

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
        self.stack.insert(0, source)


class TestCases(unittest.TestCase):
    solution = Solution()

    def test(self):
        self.assertEqual(self.solution.findOrder(1, []), [0])
        self.assertEqual(self.solution.findOrder(2, [[1, 0]]), [0, 1])
        self.assertEqual(self.solution.findOrder(2, [[1, 0], [0, 1]]), [])

if __name__ == '__main__':
    unittest.main()
