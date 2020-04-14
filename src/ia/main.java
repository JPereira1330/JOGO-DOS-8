package ia;

public class main {

	public static void main(String[] args) {

		No raiz = new No();
		JogoDos8 jd8 = new JogoDos8();
		GerenciadorArvore ga = new GerenciadorArvore();
		int[][] valor = {{1,2,4},{5,0,6},{8,3,7}};
		int[][] resultado = {{1,2,3},{4,5,6},{7,8,0}};
		
		raiz.setPai(null);
		raiz.setValor(valor);
		raiz.setUltimoMovimmento(ga.MOV_NENHUM);
		
		ga.setRaiz(raiz);
		ga.setResultado(resultado);
		ga.setProfundidade(3);
		ga.executarJogo();
		
		System.out.println("fim");
		
		return;
	}

}
