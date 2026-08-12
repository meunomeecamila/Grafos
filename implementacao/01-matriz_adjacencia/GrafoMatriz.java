//! Grafo Matriz

//Esse código discute a implementação básica de uma matriz de adjacência para representar
//um grafo, englobando as funções de consultar quantidade (V ou E), verificar se é válido, adicionar,
//remover e imprimir.

//* Conceitos

/* Uma das formas de representar grafos é pela matriz. Essa matriz guardará as associações
(ou arestas) do nosso grafo. 
Exemplo: matriz[1][2] = 1 significa que há uma aresta entre os vértices 1 e 2. 
matriz[3][2] = 0 significa que não há uma aresta entre os vértices 3 e 2. */

//? Grafos direcionados
/* Para grafos direcionados,  matriz[1][2] e matriz [2][1] podem ter valores diferentes, 
representando diferentes direções de setas: 
1 apontando pra 2 e 2 apontando pra 1, respectivamente.*/

//? Grafos não-direcionados
/* Para grafos não-direcionados, os dois devem ter o mesmo valor, uma vez que não há
sentido de seta. */

//Criar matriz
public class GrafoMatriz {

    private int[][] matriz; //nesse caso, colocamos int e não boolean pois podemos fazer
    //outras representações dentro da matriz, sendo peso ou arestas paralelas. 

    //Exemplo:
    // matriz[1][2] = 3 -> três arestas de 1 para 2
    // matriz[1][2] = 7 -> poderia representar peso 7

    //em um grafo simples (sem loops ou arestas paralelas), podemos fazer uma matriz booleana

    private int quantidadeVertices; //a matriz sempre será V x V sendo V a qtd de vértices

    //Os vértices são representados pelos índices da matriz. 
    //Para n vértices, temos V = {0,1,2,...,n-1}

    public GrafoMatriz(int quantidadeVertices) {
        this.quantidadeVertices = quantidadeVertices;
        this.matriz = new int[quantidadeVertices][quantidadeVertices];
    }

//Validar arestas
//Essa função garante que arestas apenas serão adicionadas em vértices válidos. 
//Ex: não é possível adicionar uma aresta de 0 a 7 se os vértices vão até 5 apenas. 

private boolean verticeValido(int vertice) { //função usada em todas as próximas operações
    return vertice >= 0 && vertice < quantidadeVertices;
}

//Agora sim, podemos adicionar arestas!
public void adicionarAresta(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    matriz[origem][destino]++;
}

//? Outras operações 

//Consultar qual a quantidade de arestas
public int quantidadeArestas(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    return matriz[origem][destino];
}

// Remover uma aresta
public void removerAresta(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    if (matriz[origem][destino] > 0) {
        matriz[origem][destino]--;
    }
}

// Verificar se existe uma aresta
public boolean existeAresta(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    return matriz[origem][destino] > 0;
}

// Consultar quantidade de vértices
public int quantidadeVertices() {
    return quantidadeVertices;
}

// Imprimir a matriz de adjacência
public void imprimirMatriz() {

    for (int i = 0; i < quantidadeVertices; i++) {

        for (int j = 0; j < quantidadeVertices; j++) {
            System.out.print(matriz[i][j] + " ");
        }

        System.out.println();
    }
}

}