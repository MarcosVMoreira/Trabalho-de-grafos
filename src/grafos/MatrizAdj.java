/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.Collection;
import java.util.LinkedList;

public class MatrizAdj implements Grafo {

    private double matrizAdj[][];
    private MeusAlgoritmos objeto = new MeusAlgoritmos();

    public MatrizAdj(int numeroVertices) {
        this.matrizAdj = new double[numeroVertices][numeroVertices];

        for (int i = 0; i < matrizAdj.length; i++) {
            for (int j = 0; j < matrizAdj.length; j++) {
                this.matrizAdj[i][j] = Double.MAX_VALUE;
            }
        }
    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino) throws Exception {
        if (origem != null && destino != null) {
            this.matrizAdj[origem.id()][destino.id()] = 1;

        } else {
            throw new Exception("Origem ou destino vazios.");
        }

    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception {
        if (origem != null && destino != null) {
            if (this.matrizAdj[origem.id()][destino.id()] == Double.MAX_VALUE) {
                this.matrizAdj[origem.id()][destino.id()] = peso;
            } else {
                throw new Exception("Aresta já existente entre os vértices dados.");
            }

        } else {
            throw new Exception("Origem ou destino vazios.");
        }
    }

    @Override
    public boolean existeAresta(Vertice origem, Vertice destino) {

        if (this.matrizAdj[origem.id()][destino.id()] != Double.MAX_VALUE) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int grauDoVertice(Vertice vertice) throws Exception {
        if (vertice == null) {
            throw new Exception("Vértice nulo.");
        } else {
            int cont = 0;
            for (int i = 0; i < matrizAdj.length; i++) {
                if (matrizAdj[vertice.id()][i] != Double.MAX_VALUE || matrizAdj[i][vertice.id()] != Double.MAX_VALUE) {
                    cont++;
                }
            }
            return cont;
        }
    }

    @Override
    public int numeroDeVertices() {
        return matrizAdj.length;
    }

    @Override
    public int numeroDeArestas() {
        int cont = 0;
        for (int i = 0; i < matrizAdj.length; i++) {
            for (int j = 0; j < matrizAdj.length; j++) {
                if (matrizAdj[i][j] != Double.MAX_VALUE) {
                    cont++;
                }
            }
        }
        return cont;
    }

    @Override
    public Collection<Vertice> adjacentesDe(Vertice vertice) throws Exception {
        Collection<Vertice> colecao = new LinkedList<>();

        if (vertice.id() > matrizAdj.length) {
            throw new Exception("Vértice não existe.");
        } else {
            for (int i = 0; i < matrizAdj.length; i++) {
                if (matrizAdj[vertice.id()][i] != Double.MAX_VALUE) {
                    colecao.add(objeto.getListaVertice().get(i));
                }
            }
            return colecao;
        }
    }

    @Override
    public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {
        if (origem.id() < matrizAdj.length && destino.id() < matrizAdj.length) {
            this.matrizAdj[origem.id()][destino.id()] = peso;
        } else {
            throw new Exception("O(s) vértice(s) origem e/ou destino não existe(m)");
        }
    }

    @Override
    public Collection<Vertice> vertices() {
        Collection<Vertice> colecao = new LinkedList<>();
        for (int i = 0; i < matrizAdj.length; i++) {
            colecao.add(objeto.getListaVertice().get(i));
        }
        return colecao;

    }

    public double[][] matrizAdj() {
        return matrizAdj;
    }

    public void setMatrizAdj(double[][] matrizAdj) {
        this.matrizAdj = matrizAdj;
    }

}
