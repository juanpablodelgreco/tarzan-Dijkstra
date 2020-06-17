
public class App {

	public static void main(String[] args) {
		Grafo g = new Grafo("input");
		Dijkstra algoritmo = new Dijkstra(g);
		algoritmo.algoritmo();
	}

}
