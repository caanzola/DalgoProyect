

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B 
{

	private static int numGrafos;
	private static int numNodos;
	private static int numArcos;
	private static ArrayList<Integer> nodoInic;
	private static ArrayList<Integer> nodoFin;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String lineain,data[];
	
		int contador = 0;

		while(true)
		{
			lineain = br.readLine();

			if (lineain.isEmpty()) 
				return;

			data = lineain.split(" ");
			
			numNodos = -1;
			numArcos = -1;
			nodoInic = new ArrayList<>();
			nodoFin = new ArrayList<>();
			
			if(data.length == 1)
			{
				contador = 0;
				numGrafos = -1;
				numGrafos = Integer.parseInt(data[0]);	
			}
			
			if(data.length > 1)
			{
				numNodos =  Integer.parseInt(data[0]);
				numArcos =  Integer.parseInt(data[1]);
				for (int i = 2; i < data.length; i = i+2) 
				{
					nodoInic.add(Integer.parseInt(data[i]));
					nodoFin.add(Integer.parseInt(data[i+1]));	
				}
			}
			
			contador++;
			
			if(contador >= numGrafos)
			{
				if(contador == numGrafos)
				System.out.println("numero de Grafos: " + numGrafos);
				;
				System.out.println("numero de Nodos: " + numNodos);
				System.out.println("numero de Arcos: " + numArcos);
				for (int i = 0; i < nodoInic.size(); i++) 
				{
					System.out.println("Conexion de " + nodoInic.get(i) + " a " + nodoFin.get(i));
				}
				
				//crearGrafo(numNodos, numArcos, nodoInic, nodoFin);
				System.out.println(respuestaB());
			}
		}
	}

	private static String respuestaB() 
	{
		String answer = "Hello World";
		return answer;
	}

	/*private static void crearGrafo(int numNodos2, int numArcos2, ArrayList<Integer> nodoInic2, ArrayList<Integer> nodoFin2) 
	{
		Grafo grafo = new Grafo(numArcos2, numNodos2);
		grafo.llenarNodos();
		grafo.llenarArcos(nodoInic2, nodoFin2);
	}*/

}
