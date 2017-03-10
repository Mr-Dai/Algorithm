# Course Schedule II

There are a total of _n_ courses you have to take, labeled from `0` to `n - 1`.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
which is expressed as a pair: `[0,1]`

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you
should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish
all courses, return an empty array.

For example:

```
2, [[1,0]]
```

There are a total of 2 courses to take. To take course 1 you should have finished course 0.
So the correct course order is `[0,1]`

```
4, [[1,0],[2,0],[3,1],[3,2]]
```

There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is `[0,1,2,3]`.
Another correct ordering is `[0,2,1,3]`.

Check it on [LeetCode](https://leetcode.com/problems/course-schedule-ii/)

## Solutions

__TL;DR__ Represent the input as a directed graph and sort the nodes in topological order.

Similar to problem [#207](https://leetcode.com/problems/course-schedule/), the input can be converted into a directed graph.
By applying topological sort on the nodes, we guarantee that all edges point from a node earlier in the order to a node
later in the order.

Important to note that we must output an empty array if there is no such way to finish all the courses, which means the
input is not guaranteed to be able to be sorted in topological order, which means there may be cycle in the directed graph.
Hence, we must also check if there is cycle when we try to sort the nodes. Both functions can be achieved with one round of
depth-first search, given that a reverse postorder in a DAG provides a topological order.

```python
class Solution(object):
    def findOrder(self, numCourses, prerequisites):
        # Initialize
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

        # Start depth-first search
        for i in xrange(numCourses):
            if not self.hasCycle and not self.marked[i]:
                self.dfs(i)

        # Return
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
```

