
public class Nodo {
	private int x;
	private int y;
	private int nroNodo;

	public Nodo(int x, int y, int nroNodo) {
		this.x = x;
		this.y = y;
		this.nroNodo = nroNodo;
	}

	public Nodo(int nroNodo) {
		this.nroNodo = nroNodo;
	}

	public boolean esAdyacente(Nodo nodo) {
		return this.calcularDistancia(nodo) <= 50;
	}

	public int getNroNodo() {
		return nroNodo;
	}

	private double calcularDistancia(Nodo nodo) {
		double primer = Math.pow((nodo.x - this.x), 2);
		double segundo = Math.pow((nodo.y - this.y), 2);
		double resultado = Math.sqrt(primer + segundo);
		return resultado;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Nodo [x=" + x + ", y=" + y + ", nroNodo=" + nroNodo + "]";
	}
	
	
}
