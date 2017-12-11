import java.util.*;
public class PruebaC {
    public static void main( String[] args ) {
        // se definen los paremetros
//        String[] patterns = {"ctta","acca","tacc","ggct","gctt","ttac"};
//        String[] patterns = {"aab","baa","aaa","bbb"};
    	ArrayList<String> parametros = new ArrayList<String>();
    	
    	
//    	parametros.add("aab");
//    	parametros.add("baa");
//    	parametros.add("aaa");
//    	parametros.add("bbb");
    	
    	parametros.add("nfid");
    	parametros.add("conf");
    	parametros.add("cial");
    	parametros.add("denc");
    	parametros.add("onfi");
    	parametros.add("enci");
    	
//    	parametros.add("ctta");
//    	parametros.add("acca");
//    	parametros.add("tacc");
//    	parametros.add("ggct");
//    	parametros.add("gctt");
//    	parametros.add("ttac");
    	
        // DEFINE "patterns"!!!
        System.out.println(stringReconstruction(parametros));
    }
    
    public static String stringReconstruction( ArrayList<String> parametros ) {
        // crea la secuancia de parametros dividiendo cada subcadena de texto en 2 cadenas
    	
        ArrayList<String> secuencia = new ArrayList<String>();
        
        
        secuenciaParametros(parametros, secuencia);
        // crea un uniones entre las 2 sub cadenas que se generaron entre cada parametro
        boolean fin=false;
        ArrayList<String> nodes = new ArrayList<>();
        ArrayList<String> nodoInicio = new ArrayList<>();
        ArrayList<String> nodoOjetivo = new ArrayList<>();
        while(!fin) {
	        nodes = new ArrayList<>();
	        nodoInicio = new ArrayList<>();
	        nodoOjetivo = new ArrayList<>();
	        
	        for(int i = 0; i < secuencia.size(); ++i) {
	            String currNode = secuencia.get(i);
	            String[] parts = currNode.split(" -> ");
	            String source = parts[0];
	            nodoInicio.add(source);
	            if(!nodes.contains(source)) {
	                nodes.add(source);
	            }
	            String targets = parts[1];
	            String curr = targets;
	            if(!nodes.contains(curr)) {
	                nodes.add(curr);
	            }
	            if(!nodoOjetivo.contains(targets)) {
	            	nodoOjetivo.add(targets);
	            }
	        }
		    // encuentra los nodos que no tienen objetivo o no son un objetivo 
		    
		    ArrayList<String> sueltos = new ArrayList<>();
		    for (String node : nodes) {
				if(!nodoInicio.contains(node)||!nodoOjetivo.contains(node))
				{
					sueltos.add(node);
				}
		    }
		    // solo deberia un nodo sin destino (el fin de la plabra) y un nodo que no es destino (el inicio de la palabra)
		    if(sueltos.size()>2) {
			    ArrayList<String> secuenciaNueva = new ArrayList<>();
			    secuenciaParametros(sueltos, secuenciaNueva);
			    for (int i = 0; i < sueltos.size(); i++)
			    {
			    	String[] nodoNuevo = secuenciaNueva.get(i).split(" -> ");
			    	String suelto= sueltos.get(i);
					for(int j = 0; j < secuencia.size(); j++)
					{
						
						String[] parte = secuencia.get(j).split(" -> ");
						if(suelto.equals(parte[0])) {
							secuencia.set(j, nodoNuevo[1]+" -> "+parte[1]);
							if(nodoNuevo[1].equals(parte[1])) {
								secuencia.remove(j);
								j--;
							}
						}
						else if(suelto.equals(parte[1])) {
							secuencia.set(j, parte[0]+" -> "+nodoNuevo[0]);
							if(nodoNuevo[0].equals(parte[0])) {
								secuencia.remove(j);
								j--;
							}
						}
						
					}
			    }
			    secuencia.addAll(secuenciaNueva);
		    }
		    else {
		    	fin=true;
		    }	
        }
        
        
        
            
        
        Collections.sort(nodes);
        
        // convierte los nodos en numeros
        String[] graph = new String[secuencia.size()];
        for(int i = 0; i < secuencia.size(); ++i) {
            String[] parts = secuencia.get(i).split(" -> ");
            String[] targets = parts[1].split("\\,");
            String wip = nodes.indexOf(parts[0]) + " -> ";
            for(int j = 0; j < targets.length; ++j) {
                String currT = targets[j];
                wip += (nodes.indexOf(currT) + ",");
            }
            graph[i] = wip.substring(0,wip.length()-1);
        }
        
        // Find eulerian path
        String pathNums = preEuler(graph);
        String[] nums = pathNums.split("->");
        
        // Convert path to single string
        String[] steps = new String[nums.length];
        for(int i = 0; i < nums.length; ++i) {
            steps[i] = nodes.get(Integer.parseInt(nums[i]));
        }
        String out = steps[0];
        for(int i = 1; i < steps.length; ++i) {
        	if(!(steps[i].length()<steps[i-1].length()))
            out += steps[i].charAt(steps[i].length()-1);
            
        }
        
        return out;
    }
    
    public static String preEuler( String[] list ) {
        // Find sources and targets
        ArrayList<Integer> sources = new ArrayList<>();
        ArrayList<Integer> targets = new ArrayList<>();
        int largest = -1;
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            String dests = edge[1];
            int source = Integer.parseInt(edge[0]);
            sources.add(source);
            if(source > largest) {
                largest = source;
            }
            
            int currDest = Integer.parseInt(dests);
            if(currDest > largest) {
                largest = currDest;
            }
            if(!targets.contains(currDest)) {
                targets.add(currDest);
            }
            
        }
        
        // Find sink node
        int sinkNode = -1;
        for(int t = 0; t < targets.size(); ++t) {
            if(!sources.contains(targets.get(t))) {
                sinkNode = targets.get(t);
            }
        }
        if(sinkNode == -1) {
            System.err.println("NO SINK NODE!");
            System.exit(-1);
        }
        
        // Find numIn and numOut
        int[] numIn = new int[largest+1];
        int[] numOut = new int[largest+1];
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            int currSource = Integer.parseInt(edge[0]);
            String[] dests = edge[1].split("\\,");
            numOut[currSource] += dests.length;
            for(int j = 0; j < dests.length; ++j) {
                int currDest = Integer.parseInt(dests[j]);
                ++numIn[currDest];
            }
        }
        int sinkDest = -1;
        for(int i = 0; i < sources.size(); ++i) {
            if(numOut[i] > numIn[i]) {
                sinkDest = i;
            }
        }
        
        String[] newList = new String[list.length+1];
        for(int i = 0; i < list.length; ++i) {
            newList[i] = list[i];
        }
        newList[list.length] = sinkNode + " -> " + sinkDest;
        
        String temp = cicloEuler(newList);
        String[] parts = temp.split("->");
        int[] numParts = new int[parts.length-1];
        for(int i = 0; i < numParts.length; ++i) {
            numParts[i] = Integer.parseInt(parts[i]);
        }
        int endInd = -1;
        for(int i = 0; i < numParts.length; ++i) {
            if(numParts[i] == sinkNode) {
                endInd = i;
            }
        }
        String out = "";
        for(int i = endInd+1; i < numParts.length; ++i) {
            out += (numParts[i] + "->");
        }
        for(int i = 0; i < endInd+1; ++i) {
            out += (numParts[i] + "->");
        }
        
        return out.substring(0,out.length()-2);
    }
    
    public static String cicloEuler( String[] list ) {
        // Find size of adjacency list
        int largest = -1;
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            int source = Integer.parseInt(edge[0]);
            if(source > largest) {
                largest = source;
            }
        }
        
        // Creade adjacency list (edges)
        int[][] edges = new int[largest+1][];
        for(int i = 0; i < list.length; ++i) {
            String[] edge = list[i].split(" -> ");
            int source = Integer.parseInt(edge[0]);
            String[] targets = edge[1].split("\\,");
            edges[source] = new int[targets.length];
            for(int j = 0; j < targets.length; ++j) {
                edges[source][j] = Integer.parseInt(targets[j]);
            }
        }
        
        // Create boolean adjacency list mirror to track visited
        boolean[][] explored = new boolean[edges.length][];
        for(int i = 0; i < edges.length; ++i) {
            if(edges[i] != null) {
                explored[i] = new boolean[edges[i].length];
            }
        }
        
        // Create initial Cycle
        int start = 0;
        int curr = start;
        ArrayList<Integer> cycle = new ArrayList<>();
        cycle.add(start);
        do {
            int next = -1;
            for(int i = 0; i < explored[curr].length; ++i) {
                if(!explored[curr][i]) {
                    next = edges[curr][i];
                    explored[curr][i] = true;
                    break;
                }
            }
            cycle.add(next);
            curr = next;
        } while(curr != start);
        
        // Create final Cycle
        while(true) {
            boolean stop = true;
            for(int i = 0; i < explored.length; ++i) {
                for(int j = 0; explored[i] != null && j < explored[i].length; ++j) {
                    if(!explored[i][j]) {
                        start = i;
                        curr = i;
                        stop = false;
                    }
                }
                if(!stop) {
                    break;
                }
            }
            if(stop) {
                break;
            }
            
            ArrayList<Integer> cycleP = new ArrayList<>();
            List<Integer> before = new ArrayList<>(cycle.subList(0,cycle.indexOf(curr)+1));
            List<Integer> after = new ArrayList<>(cycle.subList(cycle.indexOf(curr)+1,cycle.size()));
            
            do {
                int next = -1;
                for(int i = 0; i < explored[curr].length; ++i) {
                    if(!explored[curr][i]) {
                        next = edges[curr][i];
                        explored[curr][i] = true;
                        break;
                    }
                }
                cycleP.add(next);
                curr = next;
            } while(curr != start);
            
            cycle = new ArrayList<>();
            for(int i = 0; i < before.size(); ++i) {
                cycle.add(before.get(i));
            }
            for(int i = 0; i < cycleP.size(); ++i) {
                cycle.add(cycleP.get(i));
            }
            for(int i = 0; i < after.size(); ++i) {
                cycle.add(after.get(i));
            }
        }
        
        // Output final Cycle
        String out = "";
        for(int i = 0; i < cycle.size()-1; ++i) {
            out += (cycle.get(i) + "->");
        }
        out += cycle.get(cycle.size()-1);
        return out;
    }
    
    public static ArrayList<String> secuenciaParametros( ArrayList<String> kmers, ArrayList<String> out ) {
        String[] paths = new String[kmers.size()];
        for(int i = 0; i < paths.length; ++i) {
            String curr = kmers.get(i);
            if(curr.substring(curr.length()-1,curr.length()).equals(";")){
            	curr=curr.substring(0,curr.length()-1);
            	paths[i] = (curr.substring(0,curr.length()-1) + ";" + "." + curr.substring(1,curr.length()) + ";");
            }
            else{
            	paths[i] = (curr.substring(0,curr.length()-1) + "." + curr.substring(1,curr.length()));
            }
        }
        
        String start = "";
        String ends = "";
        for(int i = 0; i < paths.length; ++i) {
            String[] edge = paths[i].split("\\.");   
            start = edge[0];
            ends =edge[1]; 
            String add = start + " -> " + ends ;
            out.add(add);
                
        }
        for(int i = 0; i < paths.length; ++i) {
        	String[] edge = paths[i].split("\\.");  
        	start = edge[0];
            ends =edge[1]; 
        	if( start.equals(ends)){
            	String nodoIgual = ends+";";
            	for(int j = 0; j < out.size(); ++j) {
            		String[] parteAux = out.get(j).split(" -> ");
            		if(parteAux[0].equals(ends) && i!=j) {
            			out.set(j, nodoIgual+" -> "+parteAux[1]);
            		}
            	}
            	out.set(i, start+" -> "+nodoIgual);
            }
        }
        return out;
    }
}