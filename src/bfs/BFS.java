/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author TofixXx
 */

/**Breadth-first search algorithm, for oriented graph.
 * Input file need to contain pairs of vertexes, that corresponds
 * edges in oriented graph, and information about number of edges and vertexes.
 * 
 * Time complexity is O(V+E).
 */
public class BFS {

    /**Find shortes paths from vertex 1 to other vertexes
     * 
     * @param adjList data structure, that contains information about
     * paths from every vertex
     * 
     * @param v total number of vertices
     * 
     * @return path array, that contains shortest path lengths from vertex 1 to 
     * all other vertices. If path does not exist, length set to 0
     */
    private static int[] findShortestPaths(HashSet<Integer>[] adjList, int v) {
        int lengths[] = new int[v + 1];
        boolean marked[] = new boolean[v + 1];
        Queue<Integer> q = new LinkedList();
        q.offer(1);
        marked[1] = true;
        while (!q.isEmpty()) {
            int s = q.poll();
            Iterator<Integer> it = adjList[s].iterator();
            while (it.hasNext()) {
                int w = it.next();
                if (!marked[w]) {
                    marked[w] = true;
                    lengths[w] = lengths[s] + 1;
                    q.offer(w);
                }
            }
        }
        return lengths;
    }

    public static void main(String[] args){
        try (BufferedReader reader = new BufferedReader(new FileReader("rosalind_bfs.txt"));
                FileWriter writer = new FileWriter(new File("output.txt"))) {
            String inp[] = reader.readLine().split(" ");
            int vNum = Integer.parseInt(inp[0]);
            int eNum = Integer.parseInt(inp[1]);
            HashSet<Integer>[] adjList = new HashSet[vNum + 1];
            for (int i = 0; i < vNum + 1; i++) {
                adjList[i] = new HashSet();
            }
            while (reader.ready()) {
                String str[] = reader.readLine().split(" ");
                adjList[Integer.parseInt(str[0])].add(Integer.parseInt(str[1]));
            }
            int res[] = findShortestPaths(adjList, vNum);
            for (int i = 1; i < vNum + 1; i++) {
                if (i != 1 && res[i] == 0) {
                    writer.write("-1 ");
                } else {
                    writer.write(Integer.toString(res[i]) + " ");
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
