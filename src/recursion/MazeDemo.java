package recursion;

import java.util.List;
import java.util.Map;

//迷宫小游戏，递归解法
public class MazeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze m = new Maze();
		int[][] maze = m.setMaze();
		System.out.println("初始迷宫：");
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("迷宫解密后：");
		m.unlockMaze(maze, 1, 0, 7, 8);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}

}

class Maze {
	public Maze() {
	}

	// 创建一个迷宫
	// 9x9
	// 0是还没走过
	// 1是围墙
	// 2是走过，能走的通道
	// 3是走过，但是这条路走不通
	public int[][] setMaze() {
		int[][] maze = new int[9][9];
		// 四周留一个入口和出口，其它都是墙
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (i == 0 || i == 8 || j == 0 || j == 8) {
					maze[i][j] = 1;
				} else {
					maze[i][j] = 0;
				}
			}
		}
		maze[1][0] = 0;// 入口
		maze[7][8] = 0;// 出口
		// 设置围墙
		int i = 2;
		int j = 1;
		while (i < 9 && j < 9) {
			maze[i++][j++] = 1;
		}
		maze[2][3]=1;
		return maze;
	}

	// 走迷宫算法
	// 递归调用
	// 起始坐标（beginX,beginY）
	// 出口坐标（exitX,exitY）
	public boolean unlockMaze(int[][] maze, int beginX, int beginY, int exitX, int exitY) {
		// Map<Integer,Integer> path=new Map<K, V>
		if (beginX == exitX && beginY == exitY) {
			// 等于2说明迷宫路线走通了
			maze[exitX][exitY] = 2;
			return true;
		} else if (maze[beginX][beginY] == 0) {
			// 还没到出口,所以还在摸索路线
			maze[beginX][beginY] = 2;// 到达某个点就将值设为2
			// 向下、右、上、左尝试寻找出口
			if (unlockMaze(maze, beginX + 1, beginY, exitX, exitY)) {
				// 尝试向下走通
				return true;
			} else if (unlockMaze(maze, beginX, beginY + 1, exitX, exitY)) {
				// 否则尝试向右
				return true;
			} else if (unlockMaze(maze, beginX - 1, beginY, exitX, exitY)) {
				// 否则尝试向上
				return true;
			} else if (unlockMaze(maze, beginX, beginY - 1, exitX, exitY)) {
				// 否则尝试向左
				return true;
			} else {
				// 上下左右都走过走不通了
				// 标记为3，表示这条路是堵住的
				maze[beginX][beginY] = 3;
				return false;
			}
		} else {
			// 走不通返回false
			return false;
		}
	}
}