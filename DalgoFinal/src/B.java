import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B 
{

	private static int numGrafos, numNodos, numArcos;
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
			
			String anterior = null;
			
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
				if(numNodos == 1 && numArcos == 0)
				{
					if(contador>1)
					anterior = 0+"."+(contador-1);
					
					conectar(anterior, 0+"."+contador);
					
					if(estaEnConjunto(conjuntoX, 0+"."+contador) == false && cumpleCondicion(0+"."+contador, "x"))
					{
						conjuntoX.add(0+"."+contador);
					}
					else if(estaEnConjunto(conjuntoY, 0+"."+contador) == false && cumpleCondicion(0+"."+contador, "y")) 
					{
						conjuntoY.add(0+"."+contador);
					}
				}
				else
				{
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
			}
			
			contador++;
			
			if(contador > numGrafos)
			{
				System.out.println(respuestaB());
			}
		}
	}

	private static void conectar(String anterior, String actual) 
	{
		conexiones.add(anterior + " a " + actual);
		conexiones.add(actual + " a " + anterior);
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
		if(conjuntoX.size() - conjuntoY.size() >= 0)
			answer = (conjuntoX.size() - conjuntoY.size())+"";
		else
			answer = (conjuntoY.size() - conjuntoX.size())+"";
		
		return answer;
	}
}
