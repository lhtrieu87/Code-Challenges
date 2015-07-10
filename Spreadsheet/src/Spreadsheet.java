import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

// Name: Le Hong Trieu
// Email: hongtrieule@gmail.com
// Github: https://github.com/lhtrieu87

public class Spreadsheet {
	private static class Cell {
		public String expression;
		public boolean isEvaluated = false;
		public double value;
		public boolean visitedBefore = false;

		public String toString() {
			return "Expression: " + expression + ", value: " + value
					+ ", isEvaluated: " + isEvaluated;
		}
	}

	private static class CyclicDependenciesException extends Exception {
		private static final long serialVersionUID = 2675024777039945457L;
	}

	private static ArrayList<ArrayList<Double>> solve(
			ArrayList<ArrayList<Cell>> spreadsheet)
			throws CyclicDependenciesException {
		int rowCount = spreadsheet.size();
		int colCount = spreadsheet.get(0).size();

		ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();

		for (int i = 0; i < rowCount; i++) {
			ArrayList<Double> resultRow = new ArrayList<Double>();
			result.add(resultRow);
			for (int j = 0; j < colCount; j++) {
				resultRow.add(evaluateCell(spreadsheet, i, j));
			}
		}

		return result;
	}

	private static double evaluateCell(ArrayList<ArrayList<Cell>> spreadsheet,
			int row, int col) throws CyclicDependenciesException {
		Cell cell = spreadsheet.get(row).get(col);

		if (cell.isEvaluated)
			return cell.value;

		if (cell.visitedBefore) {
			throw new CyclicDependenciesException();
		}

		cell.visitedBefore = true;

		String[] tokens = cell.expression.split(" ");
		Stack<Double> stack = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			double v1, v2;
			switch (token) {
			case "+":
				v2 = stack.pop();
				v1 = stack.pop();
				stack.push(v1 + v2);
				break;
			case "-":
				v2 = stack.pop();
				v1 = stack.pop();
				stack.push(v1 - v2);
				break;
			case "*":
				v2 = stack.pop();
				v1 = stack.pop();
				stack.push(v1 * v2);
				break;
			case "/":
				v2 = stack.pop();
				v1 = stack.pop();
				stack.push(v1 / v2);
				break;
			default:
				if (isDouble(token)) {
					stack.add(Double.parseDouble(token));
				} else {
					int[] reference = parseReference(token);
					double value = evaluateCell(spreadsheet, reference[0],
							reference[1]);
					stack.add(value);
				}
				break;
			}
		}

		cell.value = stack.pop();
		cell.isEvaluated = true;

		return cell.value;
	}

	private static boolean isDouble(String s) {
		char firstChar = s.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z')
			return false;
		return true;
	}

	private static int[] parseReference(String s) {
		int[] rowCol = new int[2];
		int row = s.charAt(0) - 'A';
		int col = Integer.parseInt(s.substring(1)) - 1;
		rowCol[0] = row;
		rowCol[1] = col;

		return rowCol;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int colCount = scanner.nextInt();
		int rowCount = scanner.nextInt();
		scanner.nextLine();

		ArrayList<ArrayList<Cell>> spreadsheet = new ArrayList<ArrayList<Cell>>();
		for (int i = 0; i < rowCount; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			spreadsheet.add(row);
			for (int j = 0; j < colCount; j++) {
				Cell cell = new Cell();
				cell.expression = scanner.nextLine();

				row.add(cell);
			}
		}

		try {
			ArrayList<ArrayList<Double>> result = solve(spreadsheet);
			System.out.println(colCount + " " + rowCount);
			for (int i = 0; i < rowCount; i++) {
				ArrayList<Double> row = result.get(i);
				for (int j = 0; j < colCount; j++) {
					System.out.println(String.format("%.5f", row.get(j)));
				}
			}
		} catch (CyclicDependenciesException e) {
			System.out.println("Error!!! Cyclic dependencies!");
			System.exit(1);
		}

		scanner.close();
	}
}
