/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MeusAlgoritmos implements AlgoritmosEmGrafos {

    private static LinkedList<Aresta> listaAresta = new LinkedList<>();
    private static LinkedList<Vertice> listaVertice = new LinkedList<>();
    Collection<Aresta> floresta;
    LinkedList<Aresta> classificacao = new LinkedList<>();
    int cor[]; //cada posição corresponde a cor de um vértice
    int tempo;
    int u[];
    int pai[];
    int tempoDescoberta[];
    int tempoFinalizacao[];
    int distancia[];

    @Override
    public Grafo carregarGrafo(String path, TipoDeRepresentacao t) throws Exception {
        Grafo grafo = null;
        String splitado = new String();

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        LinkedList<String> texto = new LinkedList<>();

        int origem, destino = 0;
        double peso = 0;

        String linha = buffRead.readLine();
        while (linha != null) {
            texto.add(linha);
            linha = buffRead.readLine();
        }

        buffRead.close();

        for (int i = 1; i < texto.size(); i++) {

            String[] arrayValores = texto.get(i).split(" ");
            String[] arrayValores2 = null;

            for (int j = 1; j < arrayValores.length; j++) {

                String[] arrayValores1 = arrayValores[j].split("-");
                if (arrayValores1.length > 1) {
                    for (int k = 0; k < arrayValores1.length; k++) {
                        arrayValores2 = arrayValores1[k].split(";");
                        if (k == 0) {
                            destino = Integer.parseInt(arrayValores2[0]);
                        } else {
                            peso = Double.parseDouble(arrayValores2[0]);
                        }
                    }
                }
                origem = Integer.parseInt(arrayValores[0]);

                Vertice verticeOrigem = new Vertice(origem);
                Vertice verticeDestino = new Vertice(destino);
                Aresta aresta = new Aresta(verticeOrigem, verticeDestino, peso);
                if (j == 1) {
                    listaVertice.add(verticeOrigem);
                }
                listaAresta.add(aresta);
            }

        }

        int numeroVertices = Integer.parseInt(texto.get(0));

        if (t == TipoDeRepresentacao.MATRIZ_DE_ADJACENCIA) {
            grafo = new MatrizAdj(numeroVertices);

            for (int i = 0; i < (getListaAresta().size()); i++) {
                grafo.adicionarAresta(getListaAresta().get(i).origem(), getListaAresta().get(i).destino(), getListaAresta().get(i).peso());
            }

        } else if (t == TipoDeRepresentacao.LISTA_DE_ADJACENCIA) {

            grafo = new ListaAdj(listaVertice.size());

            for (int i = 0; i < (getListaAresta().size()); i++) {
                grafo.adicionarAresta(getListaAresta().get(i).origem(), getListaAresta().get(i).destino(), getListaAresta().get(i).peso());
            }

        } else if (t == TipoDeRepresentacao.MATRIZ_DE_INCIDENCIA) {

            grafo = new MatrizInc(listaVertice.size(), listaAresta.size());

            for (int i = 0; i < (getListaAresta().size()); i++) {
                grafo.adicionarAresta(getListaAresta().get(i).origem(), getListaAresta().get(i).destino(), getListaAresta().get(i).peso());
            }

        } else {
            System.out.println("Tipo inexistente.");
        }

        return grafo;
    }

    @Override
    public Collection<Aresta> buscaEmLargura(Grafo g, Vertice v) {
        Collection<Aresta> floresta = new LinkedList<>();
        pai = new int[listaVertice.size()];
        distancia = new int[listaVertice.size()];
        cor = new int[listaVertice.size()];

        for (int i = 0; i < listaVertice.size(); i++) {
            if (i != v.id()) {
                cor[i] = 0;
            }
        }

        cor[v.id()] = 1;
        distancia[v.id()] = 0;
        pai[v.id()] = Integer.MAX_VALUE;

        LinkedList<Vertice> fila = new LinkedList<>();

        fila.add(v);

        while (fila.size() != 0) {
            Vertice primeiroFila = fila.get(0);
            fila.remove();

            LinkedList<Vertice> adj = new LinkedList<>();
            try {
                adj.addAll(g.adjacentesDe(primeiroFila));
            } catch (Exception ex) {
                Logger.getLogger(MeusAlgoritmos.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < adj.size(); i++) {
                if (cor[adj.get(i).id()] == 0) {
                    cor[adj.get(i).id()] = 1;
                    distancia[adj.get(i).id()] = distancia[primeiroFila.id()] + 1;
                    pai[adj.get(i).id()] = primeiroFila.id();
                    fila.add(adj.get(i));
                }
            }
            cor[primeiroFila.id()] = 2;
        }

        return floresta;
    }

    @Override
    public Collection<Aresta> buscaEmProfundidade(Grafo g, Vertice v) {

//        Busca em Profundidade (DFS): algoritmo de busca em profundidade no
//grafo. O usuário deverá informar o vértice inicial da busca. Os resultados
//deste algoritmo serão os tempos de descoberta (d) e finalização (f) de cada
//vértice. Além disso, o algoritmo deverá classificar as arestas de acordo com a
//busca (arestas de árvores, aresta de retorno, arestas de avanço e arestas de
//cruzamento);
        floresta = new LinkedList<>();
        cor = new int[listaVertice.size()];
        u = new int[listaVertice.size()];
        tempoDescoberta = new int[listaVertice.size()];
        tempoFinalizacao = new int[listaVertice.size()];

        int numeroDoVertice = 0;
        u[0] = v.id();

        for (int i = 0; i < listaVertice.size(); i++) {
            cor[i] = 0;
        }

        for (int i = 1; i < g.numeroDeVertices(); i++) {
            if (numeroDoVertice == v.id()) {
                numeroDoVertice++;
                u[i] = numeroDoVertice;
            } else {
                u[i] = numeroDoVertice;
            }
            numeroDoVertice++;
        }

        for (int i = 0; i < g.numeroDeVertices(); i++) {
            if (cor[i] == 0) {
                try {
                    System.out.println("chamando pelo principal");
                    DFS_Visit(u[i], g);
                } catch (Exception ex) {
                    Logger.getLogger(MeusAlgoritmos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        floresta.addAll(classificacao);

        return floresta;

    }

    public void DFS_Visit(int u, Grafo g) throws Exception {
        double peso = 0;
        cor[u] = 1;
        tempo = tempo + 1;
        tempoDescoberta[u] = tempo;
        System.out.println("O valor de u é: " + u);

        LinkedList<Vertice> adj = new LinkedList<>();
        adj.addAll(g.adjacentesDe(listaVertice.get(u)));

        for (int i = 0; i < adj.size(); i++) {

            if (cor[adj.get(i).id()] == 0) {

                for (int j = 0; j < listaAresta.size(); j++) {
                    if (listaAresta.get(j).origem().id() == u && listaAresta.get(j).destino().id() == adj.get(i).id()) {
                        listaAresta.get(j).setClassificacao("Árvore");
                        classificacao.add(listaAresta.get(j));

                    }

                }

                DFS_Visit(listaVertice.get(adj.get(i).id()).id(), g);
            } else if (cor[adj.get(i).id()] == 1) {
                for (int j = 0; j < listaAresta.size(); j++) {
                    if (listaAresta.get(j).origem().id() == u && listaAresta.get(j).destino().id() == adj.get(i).id()) {
                        listaAresta.get(j).setClassificacao("Retorno");
                        classificacao.add(listaAresta.get(j));
                    }
                }
            } else if (cor[adj.get(i).id()] == 2) {

                if (tempoDescoberta[u] < tempoFinalizacao[adj.get(i).id()]) {
                    for (int j = 0; j < listaAresta.size(); j++) {
                        if (listaAresta.get(j).origem().id() == u && listaAresta.get(j).destino().id() == adj.get(i).id()) {
                            listaAresta.get(j).setClassificacao("Avanço");
                            classificacao.add(listaAresta.get(j));
                        }
                    }

                } else if (tempoDescoberta[u] > tempoFinalizacao[adj.get(i).id()]) {
                    for (int j = 0; j < listaAresta.size(); j++) {
                        if (listaAresta.get(j).origem().id() == u && listaAresta.get(j).destino().id() == adj.get(i).id()) {
                            listaAresta.get(j).setClassificacao("Cruzamento");
                            classificacao.add(listaAresta.get(j));
                        }
                    }
                }
            }

        }

        cor[u] = 2;
        tempo++;
        tempoFinalizacao[u] = tempo;

    }

    @Override
    public boolean existeCiclo(Grafo g) {

        for (int i = 0; i < g.numeroDeArestas(); i++) {
            if (listaAresta.get(i).destino().id() == listaAresta.get(i).origem().id()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Collection<Aresta> agmUsandoKruskall(Grafo g) {
        LinkedList<LinkedList<Vertice>> conjunto = new LinkedList<LinkedList<Vertice>>();
        LinkedList<Aresta> arestasOrdenadas = new LinkedList<>();
        LinkedList<Integer> aux = new LinkedList<>();
        LinkedList<Aresta> agm = new LinkedList<>();

        for (int i = 0; i < g.numeroDeVertices(); i++) {
            conjunto.add(new LinkedList<Vertice>());
            conjunto.get(i).add(listaVertice.get(i));
            aux.add(listaVertice.get(i).id());
        }

        arestasOrdenadas.addAll(listaAresta);

        Comparador comparador = new Comparador();

        Collections.sort(arestasOrdenadas, comparador);

        for (int i = 0; i < arestasOrdenadas.size(); i++) {

            if (aux.get(arestasOrdenadas.get(i).origem().id()) != aux.get(arestasOrdenadas.get(i).destino().id())) {

                int u = aux.get(arestasOrdenadas.get(i).origem().id());

                int v = aux.get(arestasOrdenadas.get(i).destino().id());

                for (int j = 0; j < conjunto.get(v).size(); j++) {

                    conjunto.get(u).add(conjunto.get(v).get(j));

                    aux.set(conjunto.get(v).get(j).id(), u);

                }
                conjunto.get(v).clear();

                agm.add(arestasOrdenadas.get(i));

            }
        }

        return agm;
    }

    @Override
    public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception {
        LinkedList<Aresta> lista = new LinkedList<Aresta>();
        lista.addAll(arestas);
        double tamanho = 0;
        for (int i = 0; i < lista.size(); i++) {
            tamanho = tamanho + lista.get(i).peso();
        }

        return tamanho;
    }

    @Override
    public boolean ehArvoreGeradora(Grafo g, Collection<Aresta> arestas) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Aresta> caminhoMaisCurto(Grafo g, Vertice origem, Vertice destino) {

        LinkedList<Vertice> p = new LinkedList<>(); //pai
        LinkedList<Double> d = new LinkedList<>(); //distância
        LinkedList<Vertice> s; ////nós com distancia definitiva
        LinkedList<Vertice> q; //nós com distancia provisória
        ArrayList<Aresta> resposta = new ArrayList<>();

        Vertice u = null;
        double aux = Double.MAX_VALUE;

        //Função inicializa do Dijkstra
        for (int i = 0; i < listaVertice.size(); i++) {
            d.add(Double.MAX_VALUE);
            p.add(null);
        }
        d.set(origem.id(), (double) 0);

        //iniciliza S com nada
        s = new LinkedList<>();

        //iniciliza q com os vértices
        q = new LinkedList<>();
        q.addAll(listaVertice);

        //enquanto Q não estiver vazio
        while (q.size() > 0) {

            for (int i = 0; i < q.size(); i++) {

                if (d.get(q.get(i).id()) <= aux) {
                    aux = d.get(q.get(i).id());

                    u = q.get(i);
                }
            }

            aux = Double.MAX_VALUE;

            q.remove(u);
            s.add(u); //S <- S união {u}

            //para cada V adjacente de [u]
            LinkedList<Vertice> adjacentes = new LinkedList<>();
            adjacentes.addAll(g.vertices());

            double peso = 0;
            //função relaxa
            for (int i = 0; i < adjacentes.size(); i++) {
                //verifico o peso da aresta entre u e v pra usar no w(u,v)
                for (int j = 0; j < listaAresta.size(); j++) {
                    if (listaAresta.get(j).origem().id() == u.id() && listaAresta.get(j).destino().id() == adjacentes.get(i).id()) {
                        peso = listaAresta.get(j).peso();
                    }
                }

                if (d.get(adjacentes.get(i).id()) > (d.get(u.id()) + peso)) {
                    d.set(adjacentes.get(i).id(), (d.get(u.id()) + peso));
                    p.set(adjacentes.get(i).id(), u);
                }

            }
        }

        Vertice destinoAuxiliar = destino;
        Vertice paiAuxiliar = p.get(destino.id());

        System.out.println("Peso: " + d.get(destino.id()));

        while (origem.id() != destinoAuxiliar.id()) {
            paiAuxiliar = p.get(destino.id());
            resposta.add(new Aresta(paiAuxiliar, destinoAuxiliar));
            destinoAuxiliar = paiAuxiliar;
        }

        System.out.println("respota size " + resposta.size());
        for (int i = 0; i < resposta.size(); i++) {
            System.out.println(" " + resposta.get(i).origem() + " " + resposta.get(i).destino());
        }

        return resposta;
    }

    @Override
    public Collection<Aresta> arestasDeArvore(Grafo g, Vertice v) {
        LinkedList<Aresta> arvore = new LinkedList<>();
        LinkedList<Aresta> aux = new LinkedList<>();
        aux.addAll(buscaEmProfundidade(g, v));

        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getClassificacao() == "Árvore") {
                arvore.add(aux.get(i));
            }
        }
        System.out.println("\nArestas encontradas:");
        for (int i = 0; i < arvore.size(); i++) {
            System.out.println("Origem: " + arvore.get(i).origem().id() + " Destino: " + arvore.get(i).destino().id() + " Peso: " + arvore.get(i).peso() + " Classificação: " + arvore.get(i).getClassificacao());
        }

        return arvore;
    }

    @Override
    public Collection<Aresta> arestasDeRetorno(Grafo g, Vertice v) {
        LinkedList<Aresta> arvore = new LinkedList<>();
        LinkedList<Aresta> aux = new LinkedList<>();
        aux.addAll(buscaEmProfundidade(g, v));

        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getClassificacao() == "Retorno") {
                arvore.add(aux.get(i));
            }
        }
        System.out.println("\nArestas encontradas:");
        for (int i = 0; i < arvore.size(); i++) {
            System.out.println("Origem: " + arvore.get(i).origem().id() + " Destino: " + arvore.get(i).destino().id() + " Peso: " + arvore.get(i).peso() + " Classificação: " + arvore.get(i).getClassificacao());
        }

        return arvore;
    }

    @Override
    public Collection<Aresta> arestasDeAvanco(Grafo g, Vertice v) {
        LinkedList<Aresta> arvore = new LinkedList<>();
        LinkedList<Aresta> aux = new LinkedList<>();
        aux.addAll(buscaEmProfundidade(g, v));

        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getClassificacao() == "Avanço") {
                arvore.add(aux.get(i));
            }
        }
        System.out.println("\nArestas encontradas:");
        for (int i = 0; i < arvore.size(); i++) {
            System.out.println("Origem: " + arvore.get(i).origem().id() + " Destino: " + arvore.get(i).destino().id() + " Peso: " + arvore.get(i).peso() + " Classificação: " + arvore.get(i).getClassificacao());
        }

        return arvore;
    }

    @Override
    public Collection<Aresta> arestasDeCruzamento(Grafo g, Vertice v) {
        LinkedList<Aresta> arvore = new LinkedList<>();
        LinkedList<Aresta> aux = new LinkedList<>();
        aux.addAll(buscaEmProfundidade(g, v));

        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getClassificacao() == "Cruzamento") {
                arvore.add(aux.get(i));
            }
        }
        System.out.println("\nArestas encontradas:");
        for (int i = 0; i < arvore.size(); i++) {
            System.out.println("Origem: " + arvore.get(i).origem().id() + " Destino: " + arvore.get(i).destino().id() + " Peso: " + arvore.get(i).peso() + " Classificação: " + arvore.get(i).getClassificacao());
        }

        return arvore;
    }

    public LinkedList<Aresta> getListaAresta() {
        return listaAresta;
    }

    public void setListaAresta(LinkedList<Aresta> listaAresta) {
        this.listaAresta = listaAresta;
    }

    public LinkedList<Vertice> getListaVertice() {
        return listaVertice;

    }

    public void setListaVertice(LinkedList<Vertice> listaVertice) {
        this.listaVertice = listaVertice;
    }

}
