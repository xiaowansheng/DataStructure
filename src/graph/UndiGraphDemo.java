package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UndiGraphDemo {

	public static void main(String[] args) {
		/*
		UndiGraph graph = new UndiGraph(5);
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		// A-C A-B C-B B-D B-E
		// A:0 B:1 C:2 D:3 E:4
		graph.insertSide(0, 1, 1);
		graph.insertSide(0, 2, 1);
		graph.insertSide(1, 2, 1);
		graph.insertSide(1, 3, 1);
		graph.insertSide(1, 4, 1);
		*/
		UndiGraph graph = new UndiGraph(8);
		graph.insertVertex("1");
		graph.insertVertex("2");
		graph.insertVertex("3");
		graph.insertVertex("4");
		graph.insertVertex("5");
		graph.insertVertex("6");
		graph.insertVertex("7");
		graph.insertVertex("8");
		graph.insertSide(0, 1, 1);
		graph.insertSide(0, 2, 1);
		graph.insertSide(1, 3, 1);
		graph.insertSide(1, 4, 1);
		graph.insertSide(3, 7, 1);
		graph.insertSide(4, 7, 1);
		graph.insertSide(2, 5, 1);
		graph.insertSide(2, 6, 1);
		graph.insertSide(5, 6, 1);
		graph.showGraph();
		System.out.println("深度优先：");
		graph.dfs();
		System.out.println();
		System.out.println("广度优先：");
		graph.bfs();
	}
}

//无向图
class UndiGraph {
	private List<String> vertexs;// 用来记录顶点
	private int[][] graph;// 用来记录无向图的二维数组
	private int sideOfNumber;// 边的数量
	private boolean[] isTraversal;// 记录遍历情况，已经遍历则为true

	public UndiGraph(int vertexOfnumber) {
		super();
		vertexs = new ArrayList<String>(vertexOfnumber);
		graph = new int[vertexOfnumber][vertexOfnumber];
		sideOfNumber = 0;
		// isTraversal = new boolean[vertexOfnumber];
	}

	public void bfs() {
		isTraversal = new boolean[vertexs.size()];
		LinkedList<Integer> link = new LinkedList<Integer>();
		link.add(0);
		System.out.print(vertexs.get(0));
		isTraversal[0]=true;
		int i = 0;
		while (link.size() != 0) {
			i = link.removeFirst();
			for (int j = 0; j < vertexs.size(); j++) {
				if (i != j && graph[i][j] > 0) {
					if (!isTraversal[j]) {
						link.add(j);
						System.out.print("=>"+vertexs.get(j));
						isTraversal[j] = true;
					}
				}
			}
		}
	}

	// 深度优先遍历
	// 思路：从第一个顶点开始遍历，若有顶点指向的其它顶点都是已遍历或者没有路径指向，
	// 则递归方法回溯到上一个顶点，继续遍历
	public void dfs() {
		isTraversal = new boolean[vertexs.size()];
		if (vertexs.size() == 0) {
			return;
		}
		System.out.print(vertexs.get(0));
		isTraversal[0] = true;// 标记该顶点已经被遍历
		int preVertex = 0;// 上一个遍历的顶点
		dfs(preVertex);// 调用递归
	}

	/**
	 * 深度优先遍历
	 * 
	 * @param preVertex 上一个遍历的顶点
	 * @param count     记录以及遍历的顶点数量
	 */
	private void dfs(int preVertex) {
		try {
			// while (count != isTraversal.length) {
			for (int i = 1; i < isTraversal.length; i++) {
				if (!isTraversal[i]) {
					if (graph[preVertex][i] != 0) {
						System.out.print("=>"+vertexs.get(i));
						isTraversal[i] = true;
						// preVertex = i;
						dfs(i);
						// 当前顶点i没有指向任何没有被遍历的顶点，则回溯到这里，再接着循环
					}
				}
			}
			// }
			// preVertex--;
		} catch (Exception e) {
			// 捕获异常，因为构建的图可能不完整
			// 即：有某个顶点没有其它任何顶点与他相连，造成遍历时数组越界到-1，出现异常。
			throw new RuntimeException("该图不完整。");
		}
	}

	/**
	 * 添加顶点
	 * 
	 * @param vertex 顶点数据
	 */
	public void insertVertex(String vertex) {
		vertexs.add(vertex);
	}

	/**
	 * 
	 * @return 返回顶点的数量
	 */
	public int getVertexOfNumber() {
		return vertexs.size();
	}

	/**
	 * 
	 * @return 图的边数
	 */
	public int getSideOfNumber() {
		return sideOfNumber;
	}

	/**
	 * 
	 * @param index 下标
	 * @return 返回该下标对应的顶点
	 */
	public String getVertex(int index) {
		return vertexs.get(index);
	}

	/**
	 * 连接两个顶点，设置连接的边的权重
	 * 
	 * @param vertex1 顶点1
	 * @param vertex2 顶点2
	 * @param weight  边的权重
	 */
	public void insertSide(int vertex1, int vertex2, int weight) {
		// 无向图，两条边相连，对应二维数组上的值为权重，互相连通。
		graph[vertex1][vertex2] = weight;
		graph[vertex2][vertex1] = weight;
		// 边数+1
		sideOfNumber++;
	}

	/**
	 * 
	 * @param vertex1 顶点1
	 * @param vertex2 顶点2
	 * @return 返回是否联通
	 */
	public boolean isLink(int vertex1, int vertex2) {
		// 不为0，则两顶点联通
		return graph[vertex1][vertex2] != 0;
	}

	public void showGraph() {
		for (int[] link : graph) {
			System.out.println(Arrays.toString(link));
		}
	}
}