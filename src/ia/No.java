package ia;

import java.util.ArrayList;

public class No implements Cloneable{

	private No pai;
	private int[][] valor = new int[3][3];
	private ArrayList<No> filhos = new ArrayList<No>();
	
	public No() {}
	public No(No pai, int[][] valor) {
		setPai(pai);
		setValor(valor);
	}
	
	public void addFilho(No filho) {
		filhos.add(filho);
	}
	
	public void addFilhos(ArrayList<No> f) {
		filhos.addAll(f);
	}
	
	public No getPai() {
		return pai;
	}
	
	public void setPai(No pai) {
		this.pai = pai;
	}
		
	public int[][] getValor() {
		return valor;
	}
	
	public void setValor(int[][] valor) {
		this.valor = valor;
	}
	
	public ArrayList<No> getFilhos() {
		return filhos;
	}
	
	public void setFilhos(ArrayList<No> filhos) {
		this.filhos = filhos;
	}
	
}
