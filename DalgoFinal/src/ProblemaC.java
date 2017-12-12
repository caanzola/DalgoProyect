/*
 * Autores: Camilo Anzola y Santiago Rodriguez
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProblemaC 
{	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// parametros ingresados
	private static int n;
	private static int k;
	private static String[] parametros;
	// respuesta posible
	private static int respuestaMin;
	private static String respuesta;
	

	public static void main(String[] args) throws Exception{
		try {
			String ac;
			while((ac = br.readLine())!= null && !ac.equals("")){
				String[] pre = ac.split(" ");
				n = Integer.parseInt(pre[0]);
				k = Integer.parseInt(pre[1]);
				parametros = new String[n];
				for(int i=0;i<n;i++){
					String actual = br.readLine();
					parametros[i] = actual;
				}
				respuestaMin = (n*k)+2;
				permutacion(parametros, 0);
				System.out.println(respuesta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// realiza una permutacion de todos los parametros
		// formando todas las posibles cadenas de texto a partir de los parametros
	private static void permutacion(String[] arr, int index){
		// hace la permutacion con el parametro actual
		if(index >= n - 1)
		{ 
			String[] posibleResp = new String[n];
			for(int i = 0; i < n - 1; i++){
				posibleResp[i] = arr[i];
			}
			if(n > 0) 
			{
				posibleResp[n-1]= arr[n - 1];
			}
			// calcula la cadena mas larga segun el orden de los parametros de posibleResp
			String respActual = cadenaMasCorta(posibleResp);
			int act = respActual.length();
			if(act <= respuestaMin) {
				respuestaMin = act;
				respuesta = respActual;
			}
		}
		else
		{
		// cambia el orden de los parametros
			for(int i = index; i < arr.length; i++)
			{
				String temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;
				permutacion(arr, index+1);
				temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;
			}
		}
	}
	// encuentra la palabra mas corta posible de un arreglo de Strings uniendolos consecutivamente
	public static String cadenaMasCorta(String[] s) {
		String result = s[0];		
		String anterior = s[0];
		for(int i = 1; i < s.length;i++) 
		{
			String actual = s[i];
			
			String[] letras = actual.split("");
			String[] letrasAnterior = result.substring(result.length()-k, result.length()).split("");
			
			String add = actual;
			if(!result.contains(s[i])) 
			{
				String dif = "";
				for(int j = 0; j < k;j++) 
				{
					String letraActual = letras[j];
					if(letrasAnterior[letrasAnterior.length-1].equals(letraActual))
					{
						dif = actual.substring(0, j+1);
						String difAnterior = anterior.substring(anterior.length()-dif.length(),anterior.length());
						if(difAnterior.equals(dif)) 
						{
							add = actual.substring(j+1,actual.length());
						}
					}
				}
				result+= add;
			}
			anterior = s[i];
		}
		return result;
	}
}
