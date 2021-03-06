package ex1.tests;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
/**
 * This class represents a very simple (naive) JUnit test case
 * for Graph (Ex1)
 */
public class Ex1_GraphTest {
    private static Random _rand;
    private static long _seed;
    public static void initSeed(long seed) {
        _seed = seed;
        _rand = new Random(_seed);
    }
    public static void initSeed() {
        initSeed(_seed);
    }
    @Test
    public void graphTest_0() {
        int v=0, e=0;
        weighted_graph g = graph_creator(v,e,1);
        if (g==null) {
            fail("The graph g "+g+" should not ne null");
        }
    }
    @Test
    public void graphTest_1() {
        int v=10, e=30;
        weighted_graph g = graph_creator(v,e,1);
        assertEquals(g.edgeSize(),e);
        assertEquals(g.nodeSize(), v);
    }

    /**
     * Runtime test - if the testing method is still working after 5 seconds (5000 mili sec)
     * An exception is being thrown.
     */
    @Test(timeout = 5000)
    public void graphTest_runtime() {
        int v=1000*50, e=v*6;
        weighted_graph g = graph_creator(v,e,1);
        // while(true) {;}
    }
    /////////////////////////////////////////////////
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rand.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an  Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */
    private static int[] nodes(weighted_graph g) {
        int size = g.nodeSize();
        Collection<node_info> V = g.getV();
        node_info[] nodes = new node_info[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }

    /**
     * Generate a random graph with v_size nodes and e_size edges
     * @param v_size
     * @param e_size
     * @param seed
     * @return
     */
    private static weighted_graph graph_creator(int v_size, int e_size, int seed) {
        weighted_graph g = new WGraph_DS();
        initSeed(seed);
        for(int i=0;i<v_size;i++) {
                g.addNode(i);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        int[] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int i = nodes[a];
            int j = nodes[b];
            g.connect(i,j,Math.random()*10);
        }
        return g;
    }
}