

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B 
{

	private static int numGrafos;
	private static int numNodos;
	private static int numArcos;
	private static ArrayList<String> nodoInic, nodoFin, conjuntoX, conjuntoY, conexiones;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String lineain,data[];
	
		int contador = 0;
		

		conjuntoX = new ArrayList<>();
		conjuntoY = new ArrayList<>();
		conexiones = new ArrayList<>();

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
				conexiones = new ArrayList<>();
				conjuntoX = new ArrayList<>();
				conjuntoY = new ArrayList<>();
				numGrafos = -1;
				numGrafos = Integer.parseInt(data[0]);	
			}
			
			if(data.length > 1)
			{
				numNodos =  Integer.parseInt(data[0]);
				numArcos =  Integer.parseInt(data[1]);
				for (int i = 2; i < data.length; i = i+2) 
				{
					nodoInic.add(data[i]+"."+contador);

					nodoFin.add(data[i+1]+"."+contador);

					conexiones.add(data[i]+"."+contador + " a " + data[i+1]+"."+contador);
					conexiones.add(data[i+1]+"."+contador + " a " + data[i]+"."+contador);
					
					if(estaEnConjunto(conjuntoX, data[i]+"."+contador) == false && cumpleCondicion(data[i]+"."+contador, "x"))
					{
						conjuntoX.add(data[i]+"."+contador);
					}
					if(estaEnConjunto(conjuntoX, data[i+1]+"."+contador) == false && cumpleCondicion(data[i+1]+"."+contador, "x"))
					{
						conjuntoX.add(data[i+1]+"."+contador);
					}
					if(estaEnConjunto(conjuntoY, data[i+1]+"."+contador) == false && cumpleCondicion(data[i+1]+"."+contador, "y")) 
					{
						conjuntoY.add(data[i+1]+"."+contador);
					}
					if(estaEnConjunto(conjuntoY, data[i]+"."+contador) == false && cumpleCondicion(data[i]+"."+contador, "y")) 
					{
						conjuntoY.add(data[i]+"."+contador);
					}
				}
			}
			
			System.out.println("conjunto X:");
			for (int i = 0; i < conjuntoX.size(); i++) 
			{
				System.out.println(conjuntoX.get(i));
			}
			System.out.println("conjunto Y:");
			for (int i = 0; i < conjuntoY.size(); i++) 
			{
				System.out.println(conjuntoY.get(i));
			}
			
			contador++;
			
			if(contador >= numGrafos)
			{
				if(contador == numGrafos)
					System.out.println(respuestaB());
					/*System.out.println("numero de Grafos: " + numGrafos);
				;
				System.out.println("numero de Nodos: " + numNodos);
				System.out.println("numero de Arcos: " + numArcos);
				for (int i = 0; i < conexiones.size(); i++) 
				{
					System.out.println(conexiones.get(i));
				}
				*/
				//crearGrafo(numNodos, numArcos, nodoInic, nodoFin);
			}
		}
	}

	private static boolean cumpleCondicion(String nodo, String c) 
	{
		boolean cumpleCondicion = true;
		if(c.equals("x"))
		{
			for (int i = 0; i < conjuntoX.size() && cumpleCondicion; i++) 
			{
				if(hayConexion(nodo, conjuntoX.get(i)))
					cumpleCondicion = false;
			}
		}
		else if(c.equals("y"))
		{
			for (int i = 0; i < conjuntoY.size() && cumpleCondicion; i++) 
			{
				if(hayConexion(nodo, conjuntoY.get(i)))
					cumpleCondicion = false;
			}
		}
		
		return cumpleCondicion;
	}

	private static boolean hayConexion(String nodo1, String nodo2) 
	{
		boolean resp = false;
		for (int i = 0; i < conexiones.size() && !resp; i++) 
		{
			String inicio = conexiones.get(i).substring(0, 3);
			String fin = conexiones.get(i).substring(6, 9);
			
			if((inicio.equals(nodo1) && fin.equals(nodo2)) || (inicio.equals(nodo2) && fin.equals(nodo1)))
			{
				resp = true;
			}
		}
		
		return resp;
	}

	private static boolean estaEnConjunto(ArrayList<String> conjunto, String nodo) 
	{
		boolean encontro = false;
		
		for (int i = 0; i < conjunto.size() && !encontro; i++) 
		{
			if(conjunto.get(i).equals(nodo))
				encontro = true;
		}
		
		return encontro;
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
