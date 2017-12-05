import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A 
{

	private static int n;
	private static double A, B, C, D;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String lineain,data[];

		while(true)
		{
			lineain = br.readLine();

			if (lineain.isEmpty()) 
				return;

			data = lineain.split(" ");

			n = -1;
			A = -1;
			B = -1;
			C = -1;
			D = -1;

			n = Integer.parseInt(data[0]);
			A = Double.parseDouble(data[1]);
			B = Double.parseDouble(data[2]);
			C = Double.parseDouble(data[3]);
			D = Double.parseDouble(data[4]);


			System.out.println("n: " + n);
			System.out.println("A: " + A);
			System.out.println("B: " + B);
			System.out.println("C: " + C);
			System.out.println("D: " + D);

			System.out.println(respuestaA());
		}
	}

	private static String respuestaA() 
	{
		String answer = "Hello World";
		return answer;
	}

}
