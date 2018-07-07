/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

import java.util.ArrayList;
import java.util.Collection;

public interface AlgoritmosEmGrafos {

    /**
     * Carrega grafo do arquivo texto. O formato será definido do site da disciplina
     * @param path
     * @param t
     * @return um objeto grafo com as informações representadas no arquivo.
     * @throws java.lang.Exception Caminho inválido ou árquivo fora do padrão.
     */
    public Grafo carregarGrafo(String path, TipoDeRepresentacao t) throws Exception;

    /**
     * Realiza busca em largura no grafo 
     * @param g Grafo
     * @return as arestas da árvore resultante
     */
    public Collection<Aresta> buscaEmLargura (Grafo g, Vertice v);
    
    /**
     * Realiza a busca em profundidade no grafo
     * @param g Grafo
     * @return as arestas da floresta resultante
     */
    public Collection<Aresta> buscaEmProfundidade (Grafo g, Vertice v);


    /**
     * Verifica se existe ciclo no grafo.
     * @param g Grafo.
     * @return True, se existe ciclo, False, em caso contrário.
     */
    public boolean existeCiclo(Grafo g);

    
    /**
     * Retorna a árvore geradora mínima utilizando o algoritmo Kruskall.
     * @param g O grafo.
     * @return Retorna a árvore geradora mínima utilizando o algoritmo Kruscall.
     */
    public Collection<Aresta> agmUsandoKruskall(Grafo g);
    
    /**
     * Calcula o custo de uma árvore geradora.
     * @param arestas As arestas que compoem a árvore geradora.
     * @param g O grafo.
     * @return O custo da árvore geradora.
     * @throws java.lang.Exception Se a árvore apresentada não é geradora do grafo.
     */
    public double custoDaArvoreGeradora(Grafo g, Collection<Aresta> arestas) throws Exception;
    
    /**
     * Testa se a árvore é geradora.
     * @param g
     * @param arestas
     * @return True, se a árvore é árvore geradora, False, caso contrário.
     */
    public boolean ehArvoreGeradora( Grafo g, Collection<Aresta> arestas );
    
    /**
     * Retorna (em ordem) as arestas que compoem o caminho mais curto 
     * entre um par de vértices. Esta função considera o pesa das arestas
     * para composição do caminho mais curto.
     * @param g
     * @param origem
     * @param destino
     * @return As arestas (em ordem) do caminho mais curto.
     */
    public ArrayList<Aresta> caminhoMaisCurto( Grafo g, Vertice origem, Vertice destino );


    
    /**
     * Arestas de arvore.
     * @param g
     * @return As arestas de arvore do grafo g.
     */
    public Collection<Aresta> arestasDeArvore(Grafo g, Vertice v);
    
    /**
     * Arestas de retorno.
     * @param g
     * @return As arestas de retorno do grafo g.
     */
    public Collection<Aresta> arestasDeRetorno(Grafo g, Vertice v);
    
    /**
     * Arestas de avanço.
     * @param g
     * @return As arestas de avanço do grafo g.
     */
    public Collection<Aresta> arestasDeAvanco(Grafo g, Vertice v);
    
    /**
     * Arestas de cruzamento.
     * @param g
     * @return As arestas de cruzamento do grafo g.
     */
    public Collection<Aresta> arestasDeCruzamento(Grafo g, Vertice v);

    
}
