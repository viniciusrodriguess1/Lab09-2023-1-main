package tree;

import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class Node {
  
	Node esquerda;
	Node direita;
	private Node pai;
	int valor;
	private int balanceamento;

	public Node(int k) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setvalor(k);
	}

	public String toString() {
		return Integer.toString(getvalor());
	}

	public int getvalor() {
		return valor;
	}

	public void setvalor(int valor) {
		this.valor = valor;
	}

	public int getChave() {
		return valor;
	}

	public void setChave(int valor) {
		this.valor = valor;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public Node getPai() {
		return pai;
	}

	public Node setPai(Node pai) {
		this.pai = pai;
		return pai;
	}

	public Node getDireita() {
		return direita;
	}

	public Node setDireita(Node direita) {
		this.direita = direita;
		return direita;
	}

	public Node getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(Node esquerda) {
		this.esquerda = esquerda;
	}
}