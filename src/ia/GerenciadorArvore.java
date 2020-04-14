package ia;

import java.util.ArrayList;

public class GerenciadorArvore {

	private No raiz;
	private No encontrado;
	private int profundidade;
	private int[][] resultado;
	final int TAM_MATRIZ = 3;

	// REGRAS DE MOVIMENTAÇÃO
	public final int MOV_NENHUM = 0;
	public final int MOV_X_MAIS_UM = 1;
	public final int MOV_X_MENOS_UM = 2;
	public final int MOV_Y_MAIS_UM = 3;
	public final int MOV_Y_MENOS_UM = 4;

	public GerenciadorArvore() {
		setEncontrado(null);
		setRaiz(null);
		setProfundidade(2);
	}

	public GerenciadorArvore(No raiz, int valor) {
		setEncontrado(null);
		setRaiz(raiz);
		setProfundidade(valor);
	}

	private void debugMatriz(No atual) {
		debugMatriz(atual, "0");
	}

	private void debugMatriz(No atual, String filho) {

		int x, y;
		int nFilho = 0;

		if (atual.getFilhos().size() <= nFilho) {
			return;
		}

		do {
			debugMatriz(atual.getFilhos().get(nFilho), (filho + "->" + nFilho));
			System.out.println("FILHO: " + filho + "->" + nFilho);
			for (x = 0; x < TAM_MATRIZ; x++) {
				for (y = 0; y < TAM_MATRIZ; y++) {
					System.out.print(atual.getFilhos().get(nFilho).getValor()[x][y] + " ");
				}
				System.out.println("");
			}
			nFilho++;
		} while (atual.getFilhos().size() > nFilho);

	}

	private int[][] capturarValoesAtuais(No atual) {

		int x, y;
		int[][] valores = new int[3][3];

		for (x = 0; x < TAM_MATRIZ; x++) {
			for (y = 0; y < TAM_MATRIZ; y++) {
				valores[x][y] = atual.getValor()[x][y];
			}
		}

		return valores;
	}

	private No geraNo(No atual) {

		No novoNo;
		int x, y;
		int[][] valor = new int[3][3];

		boolean add = true;
		boolean[][] matrizSequencial = new boolean[3][3];

		ArrayList<No> filhos = new ArrayList<No>();

		for (x = 0; x < TAM_MATRIZ; x++) {
			for (y = 0; y < TAM_MATRIZ; y++) {

				if (add && capturarValoesAtuais(atual)[x][y] == getResultado()[x][y]) {
					matrizSequencial[x][y] = true;
				} else {
					add = false;
				}

				// Caso não seja 0 continua
				if (capturarValoesAtuais(atual)[x][y] != 0) {
					continue;
				}

				if (x - 1 >= 0 && atual.getUltimoMovimmento() != MOV_X_MAIS_UM) {
					if (!matrizSequencial[x - 1][y]) {
						novoNo = new No();
						valor = capturarValoesAtuais(atual);

						valor[x][y] = valor[x - 1][y];
						valor[x - 1][y] = 0;

						novoNo.setPai(atual);
						novoNo.setValor(valor);
						novoNo.setUltimoMovimmento(MOV_X_MENOS_UM);
						filhos.add(novoNo);
					}
				}

				if (x + 1 < TAM_MATRIZ && atual.getUltimoMovimmento() != MOV_X_MENOS_UM) {
					if (!matrizSequencial[x + 1][y]) {
						novoNo = new No();
						valor = capturarValoesAtuais(atual);

						valor[x][y] = valor[x + 1][y];
						valor[x + 1][y] = 0;

						novoNo.setPai(atual);
						novoNo.setValor(valor);
						novoNo.setUltimoMovimmento(MOV_X_MAIS_UM);
						filhos.add(novoNo);
					}
				}

				if (y - 1 >= 0 && atual.getUltimoMovimmento() != MOV_Y_MAIS_UM) {
					if (!matrizSequencial[x][y - 1]) {
						novoNo = new No();
						valor = capturarValoesAtuais(atual);

						valor[x][y] = valor[x][y - 1];
						valor[x][y - 1] = 0;

						novoNo.setPai(atual);
						novoNo.setValor(valor);
						novoNo.setUltimoMovimmento(MOV_Y_MENOS_UM);
						filhos.add(novoNo);
					}
				}

				if (y + 1 < TAM_MATRIZ && atual.getUltimoMovimmento() != MOV_Y_MENOS_UM) {
					if (!matrizSequencial[x][y + 1]) {
						novoNo = new No();
						valor = capturarValoesAtuais(atual);

						valor[x][y] = valor[x][y + 1];
						valor[x][y + 1] = 0;

						novoNo.setPai(atual);
						novoNo.setValor(valor);
						novoNo.setUltimoMovimmento(MOV_Y_MAIS_UM);
						filhos.add(novoNo);
					}
				}

				break;
			}
		}

		atual.addFilhos(filhos);
		return atual;
	}

	private No percorreArvore(No atual, int profundidade) {

		int nFilho = 0;
		No gerado = new No();

		if (atual.getFilhos().size() <= 0) {
			gerado = geraNo(atual);
			atual.setFilhos(gerado.getFilhos());
			return atual;
		}

		if (getProfundidade() <= profundidade) {
			return null;
		}

		do {

			gerado = percorreArvore(atual.getFilhos().get(nFilho), profundidade + 1);
			if (gerado == null) {
				return atual;
			}

			atual.getFilhos().get(nFilho).addFilhos(gerado.getFilhos());

			nFilho++;
		} while (atual.getFilhos().size() > nFilho);

		return atual;
	}

	public int executarJogo() {
		No atual;

		int i = 0;
		atual = getRaiz();

		do {
			atual = percorreArvore(atual, 0);
			i++;
		} while (i <= 3);
		debugMatriz(atual);

		return 0;
	}

	/*
	 * public No buscar(int valor) {
	 * 
	 * int quantia; int quantiaAnterior;
	 * 
	 * quantiaAnterior = 0;
	 * 
	 * do { quantia = buscar(valor, getRaiz(), 0,0);
	 * System.out.println("Quantia: "+quantia); if(quantia == quantiaAnterior) {
	 * return null; } quantiaAnterior = quantia;
	 * setProfundidade(getProfundidade()+2); }while(quantia != -1);
	 * 
	 * return getEncontrado(); }
	 * 
	 * private int buscar(int valor, No atual, int profundidadeAtual, int quantiaNo)
	 * {
	 * 
	 * int nFilho;
	 * 
	 * nFilho = 0; quantiaNo++;
	 * 
	 * if(atual.getValor() == valor){ setEncontrado(atual); return -1; }
	 * 
	 * System.out.println(quantiaNo+" - "+profundidadeAtual+" / "+getProfundidade())
	 * ; if(profundidadeAtual >= getProfundidade()) { return quantiaNo; }
	 * 
	 * do { if(atual.getFilhos().size() <= nFilho) { return quantiaNo; } quantiaNo =
	 * buscar(valor, atual.getFilhos().get(nFilho), profundidadeAtual+1, quantiaNo);
	 * nFilho++; }while(quantiaNo != -1);
	 * 
	 * return quantiaNo; }
	 */

	public No getRaiz() {
		return raiz;
	}

	public int[][] getResultado() {
		return resultado;
	}

	public void setResultado(int[][] resultado) {
		this.resultado = resultado;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public int getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}

	public No getEncontrado() {
		return encontrado;
	}

	public void setEncontrado(No encontrado) {
		this.encontrado = encontrado;
	}

}
