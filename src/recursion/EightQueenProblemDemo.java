package recursion;

/**
 * 八皇后问题————递归
 * 
 * @author LDQ
 *
 */
public class EightQueenProblemDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EightQueenProblem e = new EightQueenProblem();
		// e.show();
		e.solution(7, 0);
		// e.show();
		System.out.println("总共有解法：" + e.count + "种。");
		System.out.println("总共检测冲突次数："+e.total);//22980次
	}

}

class EightQueenProblem {

	int[][] project;// 一行记录一个解决方案
	int[][] eightQueen;// 八皇后棋盘
	int count;// 记录解决方案的总数
	
	int total=0;//记录检测冲突次数

	public EightQueenProblem() {
		super();
		project = new int[92][8];//总共有92种解法
		eightQueen = new int[8][8];
		count = 0;
		clear(eightQueen);// 初始化
		// TODO Auto-generated constructor stub
	}

	// 0：空白
	// 1：占有
	//穷举从棋盘右下角开始，也就是坐标（7，0）
	//逐层往上穷举
	//第一次调用时需要传入初始坐标(7,0)
	public boolean solution(int x, int y) {
		//先查看有没有记录过解决方案
		if(count==92) {
			//有则返回
			return true;
		}
		//因为是从底层往上，所有左、右和上面部分不用检测
		//先检测正下方，接着检测右下方，再检测左下方
		if(detectionDown(x, y)||detectionLowerRight(x, y)||detectionLowerLeft(x, y)) {
			//有冲突则换到下一个点
			if(nextPoint(x, y)) {
				return true;
			}else {
				return false;
			}
		}else {
			// 否则这个点没有与其它点冲突，则做上标记1
			eightQueen[x][y] = 1;
			if (x - 1 >= 0 && solution(x - 1, 0)) {
				return true;
			} else if (x - 1 < 0) {
				// 找到一个方案
				// 记录并回溯
				count++;//方案数+1
				for (int a = 0; a < 8; a++) {
					for (int b = 0; b < 8; b++) {
						if (eightQueen[a][b] == 1) {
							project[count-1][b]=a;
						}
					}
				}
				System.out.println("第"+count+"种解法：");
				show();//展示当前解法
				System.out.println("**********************");
				eightQueen[x][y]=0;//重置并进行下一个解法
				//为了穷举所有解法，记录这次解法，返回false，继续算出其它解法
				return false;
			} else {
				//下一层没有解法，所以重置这层位置，往后+1，继续解
				eightQueen[x][y] = 0;
				if (y + 1 < 8 && solution(x, y + 1)) {
					return true;
				} else {
					//这层没有解法，返回上一层
					return false;
				}
			}
		}
	}
	
	//检测正下方是否有冲突
	private boolean detectionDown(int x,int y) {
		total++;
		while (++x < 8) {
			if (eightQueen[x][y] == 1) {
				return true;
			}
		}
		return false;
	}
	
	//检测左下方是否有冲突
	private boolean detectionLowerLeft(int x,int y) {
		total++;
		while (++x < 8 && --y >= 0) {
			if (eightQueen[x][y] == 1) {
				return true;
			}
		}
		return false;
	}
		
	//检测右下方是否有冲突
	private boolean detectionLowerRight(int x,int y) {
		total++;
		while (++x < 8 && ++y <8) {
			if (eightQueen[x][y] == 1) {
				return true;
			}
		}
		return false;
	}
	
	//检测到冲突时换到右边一个点检测
	private boolean nextPoint(int x,int y) {
		if (y + 1 < 8 && solution(x, y + 1)) {
			return true;
		} else {
			// 一行的8个位置都不合适，则返回false
			return false;
		}
	}
	
	// 展示数据
	public void show() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(eightQueen[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 清空数据
	public void clear(int[][] chessBoard) {
		//清空棋盘
		for (int i = 0; i < chessBoard.length; i++) {
			for (int j = 0; j < chessBoard[0].length; j++) {
				chessBoard[i][j] = 0;
			}
		}
		//清空解决方案记录
		for (int i = 0; i < project.length; i++) {
			for (int j = 0; j < project[0].length; j++) {
				project[i][j] = 0;
			}
		}
		//清空记录数
		count=0;
	}

	
	//注意：该方法是从左到右，从上到下进行穷举
	//一次性判断,在斜线和横线上有无冲突
	//一个解法需用一个一维数组存储
	//传入已经摆放的皇后个数-1
	private boolean judge(int[] project,int n) {
		for(int i=0;i<n;i++) {
			//1、确保横线上无冲突
			//2、确保斜线无冲突(相减的横值=竖值，说明在同一个斜线)
			if(project[n]==project[i]||Math.abs(n-i)==Math.abs(project[n]-project[i])) {
				return true;
			}
		}
		return false;
	}
}
