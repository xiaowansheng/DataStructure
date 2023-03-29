package stack;

import java.util.Scanner;

public class CalculatorDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator cal = new Calculator();
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("请输入要计算的式子（仅能计算加减乘除）:");
			String c = s.next();
			System.out.println(c + "=" + cal.calculator(c));
		}
	}
}

//根据自己实现的链栈（LinkedStack.java）实现计算器
class Calculator {

	// 用于计算整个表达式结果
	public int calculator(String expression) {
		// 创建数字栈
		LinkedStack numStack = new LinkedStack();
		// 创建符号栈
		LinkedStack operStack = new LinkedStack();
		// 转换成char字符串
		char[] express = expression.toCharArray();
		int sum = 0;// 接收计算结果
		int index = 0;// 用来遍历表达式
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		while (index < express.length) {
			char ch = express[index];
			// 判断是否是操作符
			if (isOperator(ch)) {
				if (operStack.isEmpty()) {
					// 压栈(记录的是字符的Ascill值)
					operStack.push(ch);
				} else {
					// 判断优先级
					if (priority(ch) <= priority((char) operStack.getTopData())) {
						num1 = numStack.pop().number;
						num2 = numStack.pop().number;
						oper = operStack.pop().number;
						numStack.push(cal(num1, num2, (char) oper));
					}
					operStack.push(ch);
				}
			} else {
				// 否则是数字则压栈
				// 判断完整数字
				String number = String.valueOf(ch);
				int count = index + 1;
				while (count < express.length && !isOperator(express[count])) {
					number = number + express[count++];
				}
				// 将数字压入数字栈
				numStack.push(Integer.valueOf(number));
			}
			index++;
		}
		// 计算最后的结果
		// 符号栈中有几个符号就要进行几次计算
		while (!operStack.isEmpty()) {
			numStack.push(cal(numStack.pop().number, numStack.pop().number, (char) operStack.pop().number));
		}
		// 数字栈中剩余的最后一个数字就是最终计算结果
		sum = numStack.pop().number;
		return sum;
	}

	// 判断是否是运算符
	private boolean isOperator(char oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}

	// 判断操作符优先级
	// 暂时只有“+ - * /”
	private int priority(char oper) {
		if (oper == '+' || oper == '-') {
			return 0;
		} else if (oper == '*' || oper == '/') {
			return 1;
		} else {
			return -1;
		}
	}

	// 计算两个数的结果
	private int cal(int num1, int num2, char oper) {
		int sum = 0;
		switch (oper) {
		case '+':
			sum = num1 + num2;
			break;
		case '-':
			sum = num2 - num1;
			break;
		case '*':
			sum = num1 * num2;
			break;
		case '/':
			sum = num2 / num2;
			break;
		default:
			break;
		}
		return sum;
	}
}
