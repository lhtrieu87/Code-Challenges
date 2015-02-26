// http://www.codechef.com/problems/CIRCUITS

import java.io.*;

class Main {
	private static class Gate {
		public double p;
		public int type = 0;
		public int left = 0;
		public int right = 0;
	}

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter w = new PrintWriter(System.out, true);

	public static void main(String[] args) throws IOException {
		int caseCount = Integer.parseInt(r.readLine());

		for(int i = 0; i < caseCount; i++) {
			r.readLine();

			int gateCount = Integer.parseInt(r.readLine().trim());

			Gate[] gates = new Gate[gateCount];

			for(int j = 0; j < gateCount; j++) {
				String l = r.readLine().trim();
				String[] tokens = l.split(" ");
				int g = Integer.parseInt(tokens[0]);
				
				gates[j] = new Gate();
				gates[j].type = g;

				if(g > 0) {
					gates[j].left = Integer.parseInt(tokens[1]) - 1;
					gates[j].right = Integer.parseInt(tokens[2]) - 1;
				} 
			}

			double a = 0; 
			double b = 1;
			double of = 0.5;
			while((a + 1e-10) < b) {
				of = (a + b) * 0.5;
				int k = -1;
				while(++k < gateCount) {
					Gate g = gates[k];
					if(g.type == 0) g.p = of;
					else if(g.type == 1) g.p = 1 - (1 - gates[g.left].p) * (1 - gates[g.right].p);
					else if(g.type == 2) g.p = gates[g.left].p * gates[g.right].p;
				}

				if(gates[gateCount - 1].p < 0.5) a = of; else b = of;
			}

			w.printf("%.5f\n", of);
		}

		w.close();
	}
}