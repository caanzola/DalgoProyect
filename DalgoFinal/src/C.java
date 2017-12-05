import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class C 
{
	private static int n;
	private static int k;
	private static ArrayList<String> cadenas;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String lineain,data[];

		cadenas = new ArrayList<>();
		
		int contador = 0;

		while(true)
		{
			lineain = br.readLine();

			if (lineain.isEmpty()) 
				return;

			data = lineain.split(" ");

			if(data.length > 1)
			{
				contador = 0;
				cadenas.clear();
				n =Integer.parseInt(data[0]);
				k =Integer.parseInt(data[1]);
				System.out.println("n: " + n);
				System.out.println("k: " + k);
			}
			else
			{
				cadenas.add(data[0]);
			}
			
			contador++;
			
			if(contador > n)
			{
				for (int i = 0; i < cadenas.size(); i++) 
				{
					System.out.println(cadenas.get(i));
				}
			}
			
			System.out.println(respuestaC());
		}
	}

	private static String respuestaC() 
	{
		String answer = "Hello World";
		return answer;
	}

}
