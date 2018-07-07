package grafos;

import java.util.Collection;
import java.util.LinkedList;

public class ListaAdj implements Grafo {

    LinkedList<LinkedList<Aresta>> vetor = new LinkedList<LinkedList<Aresta>>();
    private MeusAlgoritmos objeto = new MeusAlgoritmos();

    public ListaAdj(int numVertice) {
        for (int i = 0; i < numVertice; i++) {
            vetor.add(new LinkedList<Aresta>());
        }
    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino) throws Exception {
        Aresta arestaNova = new Aresta(origem, destino);

        if (origem != null && destino != null) {
            this.vetor.get(origem.id()).add(arestaNova);
        } else {
            throw new Exception("Origem ou destino vazios.");
        }
    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception {
        Aresta arestaNova = new Aresta(origem, destino, peso);

        if (origem != null && destino != null) {
            this.vetor.get(origem.id()).add(arestaNova);

        } else {
            throw new Exception("Origem ou destino vazios.");
        }
    }

    @Override
    public boolean existeAresta(Vertice origem, Vertice destino) {
        if (vetor.get(origem.id()).size() != 0) {
            for (int i = 0; i < vetor.get(origem.id()).size(); i++) {
                if (vetor.get(origem.id()).get(i).destino().id() == destino.id()) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public int grauDoVertice(Vertice vertice) throws Exception {
        int cont = 0;
        if (vertice == null) {
            throw new Exception("Vértice nulo.");
        } else {
            for (int i = 0; i < vetor.size(); i++) {
                for (int j = 0; j < vetor.get(i).size(); j++) {
                    if (i != vertice.id()) {
                        if (vertice.id() == vetor.get(i).get(j).destino().id()) {
                            cont++;
                        }
                    }
                }
            }
            cont = cont + vetor.get(vertice.id()).size();
            return cont;
        }
    }

    @Override
    public int numeroDeVertices() {
        return vetor.size();
    }

    @Override
    public int numeroDeArestas() {
        int cont = 0;
        for (int i = 0; i < vetor.size(); i++) {
            cont = cont + vetor.get(i).size();
        }
        return cont;
    }

    @Override
    public Collection<Vertice> adjacentesDe(Vertice vertice) throws Exception {
        Collection<Vertice> colecao = new LinkedList<>();
        Aresta aresta;

        if (vertice != null) {
            for (int i = 0; i < vetor.get(vertice.id()).size(); i++) {
                colecao.add(new Vertice(vetor.get(vertice.id()).get(i).destino().id()));
            }

        } else {
            throw new Exception("Vértice vazio.");
        }

        return colecao;

    }

    @Override
    public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {
        if (origem != null && destino != null) {
            for (int i = 0; i < vetor.get(destino.id()).size(); i++) {
                if (vetor.get(destino.id()).get(i).destino() == destino) {
                    vetor.get(destino.id()).get(i).setarPeso(peso);
                }
            }
        } else {
            throw new Exception("Origem ou destino vazios.");
        }
    }

    @Override
    public Collection<Vertice> vertices() {
        Collection<Vertice> colecao = new LinkedList<>();
        for (int i = 0; i < vetor.size(); i++) {
            colecao.add(objeto.getListaVertice().get(i));
        }
        return colecao;
    }

}
