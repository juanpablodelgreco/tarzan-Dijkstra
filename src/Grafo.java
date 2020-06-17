import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {
	private static final int MAXLINEAS = 1000;
	private int[][]matriz;
	private int cantNodos;
	private int cantAristas;
	private ArrayList<Nodo> nodos;
	private Nodo nodoInicial;
	private Nodo nodoFinal;
	private String path;
	public Grafo(String path) {
		this.path = path;
		inicializarMatriz();
		nodos = new ArrayList<Nodo>();
		cargarGrafo();
	}
	
	private void cargarGrafo() {
		try {
			Scanner sc = new Scanner(new File(path + ".in"));
			int j = 0;
			while (sc.hasNextInt()) {
				Nodo n = new Nodo(sc.nextInt(), sc.nextInt(), j);
				for (Nodo nodo : nodos) {
					if (n.esAdyacente(nodo)) {
						ponerAristaNoDirigido(n.getNroNodo(), nodo.getNroNodo());
						cantAristas++;
					}
				}
				nodos.add(n);
				j++;
			}
			cantNodos = nodos.size();
			nodoInicial = nodos.get(0);
			nodoFinal = nodos.get(nodos.size() - 1);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void ponerAristaNoDirigido(int f, int c) {
		matriz[f][c] = 1;
		matriz[c][f] = 1;
	}
	
	public boolean hayArista(int f, int c) {
		return matriz[f][c] != 0;
	}
	
	public int getPesoArista(int f, int c) {
		return matriz[f][c];
	}
	
	private void inicializarMatriz() {
		int n = Grafo.MAXLINEAS;
		matriz = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				matriz[i][j] = 0;
		}
	}

	public int getCantNodos() {
		return cantNodos;
	}

	public Nodo getNodoInicial() {
		return nodoInicial;
	}

	public Nodo getNodoFinal() {
		return nodoFinal;
	}

	public ArrayList<Nodo> getNodos() {
		return nodos;
	}
	
}
