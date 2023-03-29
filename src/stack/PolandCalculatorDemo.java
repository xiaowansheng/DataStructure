package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PolandCalculatorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PolandCalculator cal = new PolandCalculator();
		Scanner s=new Scanner(System.in);
		String expression = null;
		while(true) {
			System.out.println("请输入需要计算的数学式子(支持加减乘除小括号)：");
			expression=s.next();
			System.out.println("计算结果："+cal.calculator(expression));
		}
//		expression="30 4 + 5 * 6 -";
//		expression="4 5 * 8 - 60 + 8 2 / +";
//		expression="3 4 + 5 * 6 -";
//		System.out.println("后缀表达式：["+expression+"]的运算结果="+cal.calculator(expression));
		//expression="1+2+ (3-4)*5 ";//-2
		//expression="(1+2-3*4)/(5-4+3-2+1)+3";//0
		//expression="1-1+5*5+(2-1)*2-1";//26
		//System.out.println("计算结果："+cal.calculator(expression));
	}

}

//波兰计算器
//运用后缀表达式计算
class PolandCalculator {
	public PolandCalculator() {
	}

	// 传入字符串表达式，转换为标准格式的中缀表达式，又转换为标准格式后缀表达式，然后计算得出结果
	public double calculator(String expression) {
		//List<String> list = getList(expression);
		//输出原式子
		System.out.println("原式：["+expression+"]");
		//表达式转换成中缀表达式
		List<String> list=getNifixExpression(expression);
		//输出中缀表达式
		System.out.println("中缀表达式："+list);
		//中缀表达式转换成后缀表达式
		list=getSuffixExpression(list);
		//输出后缀表达式
		System.out.println("后缀表达式："+list);	
		Stack<String> stack = new Stack<String>();
		double num1 = 0;
		double num2 = 0;
		char oper=0;
		int index = 0;
		int count = list.size();
		while (index < count) {
			if (!isOperator(list.get(index).charAt(0))) {
				stack.push(list.get(index));
			} else {
				try {
					num1 = Double.parseDouble(stack.pop());
					num2 = Double.parseDouble(stack.pop());
					oper = list.get(index).charAt(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("输入的表达式有误！");
				}
				switch (oper) {
				case '+':
					stack.push(String.valueOf(num1 + num2));
					break;
				case '-':
					stack.push(String.valueOf(num2 - num1));
					break;
				case '*':
					stack.push(String.valueOf(num1 * num2));
					break;
				case '/':
					stack.push(String.valueOf(num2 / num1));
					break;
				default:
					throw new RuntimeException("输入的表达式有误！");
				}
			}
			index++;
		}
		return Double.parseDouble(stack.pop());
	}

	// 中缀表达式将表达式放入List中返回
	private List<String> getNifixExpression(String expression) {
		List<String> list = new ArrayList<String>();
		// 去除字符串包含的所有空格，并转换为字符数组
		char[] exp = expression.replaceAll(" ", "").toCharArray();
		// 用于遍历数组
		int index = 0;
		// 记录遍历值
		char ch;
		// 遍历每一个数据，并添加到List中
		while (index < exp.length) {
			ch = exp[index];
			if (isOperator(ch)) {
				list.add(exp[index] + "");
				index++;
			} else if (isNumber(exp[index])) {
				String number = ch + "";
				while (++index < exp.length && isNumber(exp[index])) {
					number += exp[index];
				}
				list.add(number);
			} else {
				// 检测到不是我们需要的符号
				throw new RuntimeException("算式中的符号无法鉴别！");
			}
		}
		return list;
	}

	// 将中缀表达式转换为后缀表达式
	private List<String> getSuffixExpression(List<String> nifixExpression) {
		List<String> suffixExString = new ArrayList<String>();// 存后缀表达式
		Stack<String> stack = new Stack<String>();
		int index = 0;// 用来遍历数据
		int total = nifixExpression.size();
		while (index < total) {
			String ch = nifixExpression.get(index);
			// 是数字则直接添加进List
			if (!isOperator(ch.charAt(0))) {
				suffixExString.add(ch);
			} else {
				if (stack.size() == 0) {
					stack.push(ch);
				} else {
					if (")".equals(ch)) {
						String s = stack.pop();
						while (!"(".equals(s)) {
							suffixExString.add(s);
							try {
								s = stack.pop();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								throw new RuntimeException("输入的式子有误！");
							}
						}
					}else {
						// 与栈顶元素比较优先级，大于则压栈
						char oper=stack.peek().charAt(0);
						if(priority(ch.charAt(0)) <= priority(oper)&&oper!='(') {
							suffixExString.add(stack.pop());
						}
						stack.push(ch);
					}
				}
			}
			index++;
		}
		while (stack.size() > 0) {
			suffixExString.add(stack.pop());
		}
		return suffixExString;
	}

	// 判断操作符优先级
	// 判断“+ - * / （ ）”
	private int priority(char oper) {
		if (oper == '+' || oper == '-') {
			return 0;
		} else if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '(' || oper == ')') {
			return 2;
		} else {
			return -1;
		}
	}

	//无用
	// 将一串字符串表达式的每个数据存放在栈中,并返回
	private List<String> getList(String expression) {
		// Stack<String> stack=new Stack<String>();
		List<String> list = new ArrayList<String>();
		// String express = expression.strip();
		String[] data = expression.split(" ");
		// System.out.println(data);
//		for(int i=0;i<data.length;i++) {
//			System.out.print(data[i]+"\t");
//		}
		// String[] data = express.toCharArray();
		int index = 0;// 用于遍历数组
		while (index < data.length) {
			if (isOperator(data[index].charAt(0))) {
				// stack.push(data[index]+"");
				list.add(data[index]);
			} else {
//				String number = data[index]+"";
//				while (++index<data.length&&!isOperator((data[index]).charAt(0))) {
//					number += data[index];
//				}
				// stack.push(number);
				list.add(data[index]);
			}
			index++;
		}
		return list;
	}

	// 判断是否是运算符
	private boolean isOperator(char oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/' || oper == '(' || oper == ')';
	}

	// 判断是否是运输数字相关
	private boolean isNumber(char number) {
		return number >= 48 && number <= 57 || number == 46;
	}
}
