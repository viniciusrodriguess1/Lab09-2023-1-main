package tree;

import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements Tree {
    private class Node {
        int valor;
        Node esquerda, direita;

        public Node(int valor) {
            this.valor = valor;
            esquerda = direita = null;
        }
    }

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
        if (valor < raiz.valor)
            raiz.esquerda = insereRecursivo(raiz.esquerda, valor);
        else if (valor > raiz.valor)
            raiz.direita = insereRecursivo(raiz.direita, valor);
        return raiz;
    }

    // Busca elemento
    public boolean buscaElemento(int valor) {
        return buscaRecursivo(raiz, valor);
    }

    private boolean buscaRecursivo(Node raiz, int valor) {
        if (raiz == null) {
            return false;
        }
        if (valor == raiz.valor) {
            return true;
        }
        return valor < raiz.valor ? buscaRecursivo(raiz.esquerda, valor) : buscaRecursivo(raiz.direita, valor);
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

            raiz.valor = minimo(raiz.direita);
            raiz.direita = removeRecursivo(raiz.direita, raiz.valor);
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