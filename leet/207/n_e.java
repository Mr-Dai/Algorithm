import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;

public class Solution {
    private boolean[] visited;
    private boolean[] onStack;
    private boolean hasCycle = false;
    private Map<Integer, List<Integer>> edges;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Initialize graph edges
        edges = new HashMap<>();
        for (int i = 0; i < numCourses; i++)
            edges.put(i, new LinkedList<>());
        for (int i = 0; i < prerequisites.length; i++)
            edges.get(prerequisites[i][0]).add(prerequisites[i][1]);
        // Initialize markers
        visited = new boolean[numCourses];
        onStack = new boolean[numCourses];
        // Start depth-first iteration
        for (int i = 0; i < visited.length; i++)
            if (!hasCycle && !visited[i])
                dfs(i);
        return !hasCycle;
    }

    private void dfs(int source) {
        onStack[source] = true;
        visited[source] = true;
        for (int next : edges.get(source)) {
            if (hasCycle) return;
            else if (onStack[next]) {
                hasCycle = true;
                return;
            }
            else if (!visited[next]) dfs(next); 
        }
        onStack[source] = false;
    }
}