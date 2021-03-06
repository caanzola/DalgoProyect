/*
 * Autores: Camilo Anzola y Santiago Rodriguez
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemaA
{

	private static int n, posPunto;
	private static double A, B, C, D;
	private static Double[] arr;

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

			n = Integer.parseInt(data[0]);
			A = Double.parseDouble(data[1]);
			B = Double.parseDouble(data[2]);
			C = Double.parseDouble(data[3]);
			D = Double.parseDouble(data[4]);
			
			llenarInvariante();
			
			System.out.println(respuestaA());
		}
	}

	private static void llenarInvariante() 
	{
		arr = new Double[n+1];
		
		for (int i = 0; i < arr.length; i++) 
		{
			if(i == 0)
				arr[i] = A;
			else if(i == 1)
				arr[i] = B;
			else if(i > 1)
				arr[i] = D*arr[i-1] +  C*arr[i-2];
		}
	}

	public static String respuestaA() 
	{
		String answer = "Hello World";
		Double cp = cp(n);
		answer = conDecimales(cp);
		return answer;
	}
	
	private static String conDecimales(Double cP) 
	{
		String dev = null;
		char[] arr = (cP+"").toCharArray();

		posPunto = 0;

		for (int i = arr.length-1; i >= 0; i--) 
		{
			if((arr[i]+"").equals("."))
				posPunto = i;
		}

		if(posPunto == arr.length-2)
		{
			dev = (cP+"") + "000";
		}
		else if(posPunto == arr.length-3)
		{
			dev = (cP+"") + "00";
		}
		else if(posPunto == arr.length-4)
		{
			dev = (cP+"") + "0";
		}
		else if(posPunto <= arr.length-6) //Hay cinco decimales o m�s
		{
			int interes = posPunto + 4;

			char[] redondeado = null;

			for (int i = 1; (i+interes) < arr.length; i++) 
			{
				redondeado = redondear(arr, interes);
			}

			dev = String.copyValueOf(redondeado);
		}

		return dev;
	}
	
	public static char[] redondear(char[] arr, int interes) 
	{
		char[] res = arr;
		
		if(posPunto > arr.length-11 && !hayE(res))
		{
			if((interes+2) < arr.length && res[interes+2]!=' ')
			{
				redondear(arr, (interes+2));
				redondear(arr, interes+1);
			}
			else if((interes+1) < arr.length && res[interes+1]!=' ')
			{
				if(Integer.parseInt(res[interes+1]+"") > 5)
				{
					if((Integer.parseInt(res[interes]+"")) != 9)
					{
						res[interes] = ((Integer.parseInt(res[interes]+"")+1)+"").charAt(0);
						res[interes+1] = ' ';
					}
					else
					{
						boolean termino = false;
						for (int i = 0; interes-i != posPunto && !termino; i++) 
						{
							if(Integer.parseInt(res[interes-i]+"") != 9)
							{
								res[interes-i] = ((Integer.parseInt(res[interes-i]+"")+1)+"").charAt(0);
								res[interes-i+1] = '0';
								termino = true;
							}
							else
							{
								res[interes-i+1] = ' ';
							}
						}
					}
				}
				else
				{
					res[interes+1] = ' ';
				}
			}
		}
		else
		{
			for (int i = posPunto + 5; i < res.length; i++) 
			{
				res[i] = ' ';
			}
		}

		return res;
	}

	private static boolean hayE(char[] r) 
	{
		boolean devo = false;
		for (int i = 0; i < r.length && !devo; i++) 
		{
			if((r[i]+"").equals("E"))
				devo = true;
		}
		return devo;
	}

	public static double r(int numero)
	{
		return arr[numero];
	}
	
	public static double cp(int ene)
	{
		double resp = 0;
		
		for (int k = 0; k <= ene; k++) 
		{
			resp += r(k)*r(ene-k);
		}
		
		return resp;
	}
}
