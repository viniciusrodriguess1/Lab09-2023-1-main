package tree;

import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements Tree {

    private Node raiz;

    public BinarySearchTree() {
        raiz = null;
    }

    // Inserir elemento
    public void insereElemento(int valor) {
        raiz = insereRecursivo(raiz, valor);
    }

    private Node insereRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            raiz = new Node(valor);
            return raiz;
        }
        if (valor < raiz.getvalor())
            raiz.setEsquerda(insereRecursivo(raiz.esquerda, valor));
        else if (valor > raiz.getvalor())
            raiz.direita = insereRecursivo(raiz.direita, valor);
        return raiz;

    }
    private int altura(Node atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

    private void setBalanceamento(Node Node) {
		Node.setBalanceamento(altura(Node.getDireita()) - altura(Node.getEsquerda()));
	}

    public Node rotacaoDireita(Node inicial) {

		Node esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

    public Node rotacaoEsquerda(Node inicial) {

		Node direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

    public void verificarBalanceamento(Node atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(int k) {
		removerAVL(this.raiz, k);
	}

    public void removerAVL(Node atual, int k) {
		if (atual == null) {
			return;

		} else {

			if (atual.getChave() > k) {
				removerAVL(atual.getEsquerda(), k);

			} else if (atual.getChave() < k) {
				removerAVL(atual.getDireita(), k);

			} else if (atual.getChave() == k) {
				removerNodeEncontrado(atual);
			}
		}
	}

    public Node duplaRotacaoEsquerdaDireita(Node inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public Node duplaRotacaoDireitaEsquerda(Node inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

    // Busca elemento
    public boolean buscaElemento(int valor) {
        return buscaRecursivo(raiz, valor);
    }

    private boolean buscaRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            return false;
        }
        if (valor == raiz.getvalor()) {
            return true;
        }
        return valor < raiz.getvalor() ? buscaRecursivo(raiz.esquerda, valor) : buscaRecursivo(raiz.direita, valor);
    }

    // Remover elemento
    public void remove(int valor) {
        raiz = removeRecursivo(raiz, valor);
    }

    private Node removeRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            return null;
        }
        if (valor < raiz.valor) {
            raiz.esquerda = removeRecursivo(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = removeRecursivo(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null)
                return raiz.direita;
            else if (raiz.direita == null)
                return raiz.esquerda;

            raiz.setvalor(valor); = minimo(raiz.direita);
            raiz.direita = removeRecursivo(raiz.direita, raiz.getvalor());
        }
        return raiz;
    }

    // Encontrar o mínimo
    public int minimo() {
        return minimo(raiz);
    }

    private int minimo(Node raiz) {
        int min = raiz.valor;
        while (raiz.esquerda != null) {
            min = raiz.esquerda.valor;
            raiz = raiz.esquerda;
        }
        return min;
    }

    // Encontrar o máximo
    public int maximo() {
        return maximo(raiz);
    }

    private int maximo(Node raiz) {
        int max = raiz.valor;
        while (raiz.direita != null) {
            max = raiz.direita.valor;
            raiz = raiz.direita;
        }
        return max;
    }

    // Percorrer em pré-ordem
    public int[] preOrdem() {
        List<Integer> valores = new ArrayList<>();
        preOrdemRecursivo(raiz, valores);
        return valores.stream().mapToInt(i -> i).toArray();
    }

    private void preOrdemRecursivo(Node raiz, List<Integer> valores) {
        if (raiz != null) {
            valores.add(raiz.valor);
            preOrdemRecursivo(raiz.esquerda, valores);
            preOrdemRecursivo(raiz.direita, valores);
        }
    }

    // Percorrer em ordem
    public int[] emOrdem() {
        List<Integer> valores = new ArrayList<>();
        emOrdemRecursivo(raiz, valores);
        return valores.stream().mapToInt(i -> i).toArray();
    }

    private void emOrdemRecursivo(Node raiz, List<Integer> valores) {
        if (raiz != null) {
            emOrdemRecursivo(raiz.esquerda, valores);
            valores.add(raiz.valor);
            emOrdemRecursivo(raiz.direita, valores);
        }
    }

    // Percorrer em pós-ordem
    public int[] posOrdem() {
        List<Integer> valores = new ArrayList<>();
        posOrdemRecursivo(raiz, valores);
        return valores.stream().mapToInt(i -> i).toArray();
    }

    private void posOrdemRecursivo(Node raiz, List<Integer> valores) {
        if (raiz != null) {
            posOrdemRecursivo(raiz.esquerda, valores);
            posOrdemRecursivo(raiz.direita, valores);
            valores.add(raiz.valor);
        }
    }
}