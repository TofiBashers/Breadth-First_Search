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
public class BFS {

    /**
     * @param args the command line arguments
     */
    private static int[] Searcher(HashSet<Integer> AdjList[], int V, int E) {
        int lengths[] = new int[V + 1];
        boolean marked[] = new boolean[V + 1];
        Queue<Integer> q = new LinkedList();
        q.offer(1);
        marked[1] = true;
        while (!q.isEmpty()) {
            int s = q.poll();
            Iterator<Integer> it = AdjList[s].iterator();
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
            int V = Integer.parseInt(inp[0]);
            int E = Integer.parseInt(inp[1]);
            HashSet<Integer> AdjList[] = new HashSet[V + 1];
            for (int i = 0; i < V + 1; i++) {
                AdjList[i] = new HashSet();
            }
            while (reader.ready()) {
                String str[] = reader.readLine().split(" ");
                AdjList[Integer.parseInt(str[0])].add(Integer.parseInt(str[1]));
            }
            int res[] = Searcher(AdjList, V, E);
            for (int i = 1; i < V + 1; i++) {
                if (i != 1 && res[i] == 0) {
                    writer.write("-1 ");
                    writer.flush();
                } else {
                    writer.write(Integer.toString(res[i]) + " ");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
