import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	public static final int INFINITO = -1;
	private Grafo grafo;
	private Nodo nodoInicial;
	private Nodo nodoFinal;
	private int cantNodos;
	private boolean[] nodoTerminado;
	private ArrayList<CostoAlNodo> costos;
	
	public Dijkstra(Grafo grafo) {
		this.costos = new ArrayList<CostoAlNodo>();
		this.grafo = grafo;
		this.nodoInicial = grafo.getNodoInicial();
		this.nodoFinal = grafo.getNodoFinal();
		this.cantNodos = grafo.getCantNodos();
		this.nodoTerminado = new boolean[cantNodos];
	}
	
	@SuppressWarnings("unchecked")
	public void algoritmo() {
		Queue<CostoAlNodo> cola = new PriorityQueue<CostoAlNodo>();
		CostoAlNodo nodoActual = new CostoAlNodo(nodoInicial.getNroNodo(), 0);
		CostoAlNodo actualizado;
		cola.add(nodoActual);
		int nodo = nodoActual.getNodo();
		
		for(int i=0; i<cantNodos; i++) {
			if( i != nodo) {
				costos.add(new CostoAlNodo(i, INFINITO));
			}else {
				costos.add(new CostoAlNodo(i, 0));
				costos.get(i).agregarNodoAlCamino(nodo);
			}
		}
		
		while(!cola.isEmpty()) {
			nodoActual = cola.poll();
			nodo = nodoActual.getNodo();
			for(int i=0; i<cantNodos; i++) {
				if(nodo != i && !nodoTerminado[i]) {
					if(grafo.hayArista(nodo, i)) {
						if(costos.get(i).getCostoMinimo() == INFINITO || costos.get(nodo).getCostoMinimo() +
								grafo.getPesoArista(nodo, i) < costos.get(i).getCostoMinimo()) {
							actualizado = costos.get(i);
							actualizado.setCostoMinimo(costos.get(nodo).getCostoMinimo() + grafo.getPesoArista(nodo, i));
							actualizado.setCaminoMasCorto((ArrayList<Integer>)costos.get(nodo).getCaminoMasCorto().clone());
							actualizado.agregarNodoAlCamino(i);
							if(!cola.contains(actualizado))
								cola.add(actualizado);
						}
					}
				}
			}
			nodoTerminado[nodo] = true;
		}
		mostrarSolucion();
	}
	

	private void mostrarSolucion() 
	{
		int costo;
		costo = costos.get(this.nodoFinal.getNroNodo()).getCostoMinimo();
		if (costo == INFINITO) {
			System.out.println("NO HAY RUTA");
			System.out.println(grafo.getNodoInicial()+" "+grafo.getNodoFinal());
		}
		else
		{
			ArrayList<Integer> camino = costos.get(this.nodoFinal.getNroNodo()).getCaminoMasCorto();
			for(int j = 0; j < camino.size(); j++)
			{
				Nodo n = this.grafo.getNodos().get(camino.get(j)); ///CUIDADO CON LOS INDICES ACA
				System.out.println(n.getX() + " "+ n.getY());
			}
		}
				
	}
}
