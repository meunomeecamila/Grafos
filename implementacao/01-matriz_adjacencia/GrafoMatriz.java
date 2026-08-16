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

//---------------------------------------------------------

//? Criar matriz
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

//? Validar arestas
//Essa função garante que arestas apenas serão adicionadas em vértices válidos. 
//Ex: não é possível adicionar uma aresta de 0 a 7 se os vértices vão até 5 apenas. 

private boolean verticeValido(int vertice) { //função usada em todas as próximas operações
    return vertice >= 0 && vertice < quantidadeVertices;
}

//Agora sim, podemos adicionar arestas!
//? Inserir arestas
public void adicionarAresta(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    matriz[origem][destino]++; //incrementa em um a qtd de arestas
}

//---------------------------------------------------------

//? Outras operações 

//? Consultar qual a quantidade de arestas
public int quantidadeArestas(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    return matriz[origem][destino];
}

//? Remover uma aresta
public void removerAresta(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    if (matriz[origem][destino] > 0) { //apenas retira se tiverem arestas
        matriz[origem][destino]--;
    }
}

//? Verificar se existe uma aresta
public boolean existeAresta(int origem, int destino) {

    if (!verticeValido(origem) || !verticeValido(destino)) {
        throw new IllegalArgumentException("Vértice inválido.");
    }

    return matriz[origem][destino] > 0; //retorna true se existirem arestas
}

//? Consultar quantidade de vértices (cardinalidade)
public int quantidadeVertices() {
    return quantidadeVertices;
}

//? Imprimir a matriz de adjacência
public void imprimirMatriz() {

    for (int i = 0; i < quantidadeVertices; i++) {

        for (int j = 0; j < quantidadeVertices; j++) {
            System.out.print(matriz[i][j] + " ");
        }

        System.out.println();
    }
}

//? Verificar se um grafo é válido
// Um grafo pode ter zero arestas, mas não pode ter zero vértices.
// Logo, a quantidade de vértices tem que ser maior que 0.

// Exemplo:
// V = {0, 1, 2} e E = ∅ -> grafo válido e nulo
// V = ∅ -> grafo inválido nesta implementação

public boolean ehValido() {
    return quantidadeVertices > 0;
}

//? Verificar se possui loop
//Um loop é uma aresta que possui a mesma origem e destino. Isso
//significa que ela sai de um vértice e volta pra ele mesmo. 
//Em uma matriz, loops ficam na diagonal principal! Logo, 
//se quisermos conferir se há um loop, podemos olhar a diagonal.

public boolean possuiLoop() {

    for (int i = 0; i < quantidadeVertices; i++) {

        if (matriz[i][i] > 0) {
            return true;
        }
    }

    return false;
}

//!obs: é possível alterar esse código para retornar a quantidade
//! real de loops ou em quais vértices eles estão

//? Verificar se possui arestas paralelas
//Arestas paralelas são duas ou mais arestas que possuem um destino
//e uma origem iguais se comparadas. Elas ligam o mesmo par de vértices. 

//Para conferir isso em uma matriz, devemos conferir se o valor 
//é maior que 1. Isso significa que há mais de 1 aresta. 
//Se for 0 ou 1, não há arestas paralelas. 

//!obs: A nossa implementação permite a adição de arestas paralelas
//!por ser uma matriz inteira. Matrizes de string, booleanas ou de labels
//!não funcionam da mesma forma. 

public boolean possuiArestasParalelas() {
    for (int i = 0; i < quantidadeVertices; i++) {
        for (int j = 0; j < quantidadeVertices; j++) {

            if (matriz[i][j] > 1) return true;
        }
    }
    return false;
}

//? Cardinalidade das arestas
//Como vimos acima, a cardinalidade dos vértices é tranquila, pois
//é apenas um atributo. 

//A cardinalidade das arestas é um pouco diferente. 

//* Para grafos direcionados, podemos apenas somar a matriz. 
//! Para grafos não-direcionados, não podemos fazer isso. 

//Isso porque a representação possui matriz[0][1] = 1 e matriz[1][0] = 1,
//mesmo existindo entre elas apenas uma única aresta. 
//Para resolver isso, recebemos o parâmetro direcionado (booleano true or false)
//como parâmetro da função. 
//Caso a matriz seja não-direcionada, dividimos a soma por 2. 

//!obs: Também não podemos somar o valor de matriz[1][0], porque
//! caso tenham 3 arestas paralelas, isso iria atrapalhar na soma. 

//Logo, fazemos: 

public int cardinalidadeArestas(boolean direcionado) {
    int total = 0;

    if (direcionado) { //se for direcionado, conta tudo

        for (int i = 0; i < quantidadeVertices; i++) {
            for (int j = 0; j < quantidadeVertices; j++) {
                if (matriz[i][j] > 0) total++;
            }
        }

    } else { //se for não-direcionado, conta apenas a metade de cima + diagonal principal (loops)

        for (int i = 0; i < quantidadeVertices; i++) {
            for (int j = i; j < quantidadeVertices; j++) {

                if (matriz[i][j] > 0) total++;
            }
        }
    }

    return total;
}

//Essa função garante que loops possam ser contados. 
//Se fizéssemos uma função que divide por 2 quando for não-direcionado,
//seria menos custoso em questões de complexidade, mas daria errado com loops, pois
//eles apenas aparecem uma vez. Logo, 1 loop / 2 = 0,5, que não é uma cardinalidade válida. 

//? Verificar se um grafo é nulo
//Em grafos nulos, não existe nenhuma aresta. Logo, todos os itens
//da nossa matriz devem ser = 0.

public boolean ehNulo() {
    for (int i = 0; i < quantidadeVertices; i++) {
        for (int j = 0; j < quantidadeVertices; j++) {

            if (matriz[i][j] > 0) return false;
        }
    }
    return true;
}

//!obs: mesmo que existam vários vértices, a função pode retornar true. 
//! uma vez que grafos nulos não possuem ARESTAS

//? Verificar se um grafo é simples
//Grafos simples são aqueles que não possuem loops e nem arestas paralelas. 
//Para verificarmos se um grafo é simples, podemos reaproveitar o que fizemos anteriormente. 

public boolean ehSimples() {
    return !possuiLoop() && !possuiArestasParalelas();
}

//? Verificar se um grafo é completo
//Segundo a definição do nosso professor, grafos completos são aqueles em que existe
//um caminho/ligação entre todo par de vértices. 
//Todo grafo completo é simples por definição.

// Para um grafo NÃO DIRECIONADO:
// todo par de vértices distintos deve possuir uma aresta.
//
// Exemplo:
// 0 — 1
// 0 — 2
// 1 — 2
//
// Para um grafo DIRECIONADO:
// todo par de vértices distintos deve possuir os dois sentidos.
//
// Exemplo:
// 0 → 1
// 1 → 0
// 0 → 2
// 2 → 0
// 1 → 2
// 2 → 1

// Loops não são necessários para que o grafo seja completo,
// pois estamos verificando apenas pares de vértices distintos.
//
// Arestas paralelas também não são necessárias: basta existir
// pelo menos uma aresta entre os vértices.
// Logo, nenhum pode ser 0.

public boolean ehCompleto(boolean direcionado) {
    //percorre a matriz toda
    for (int i = 0; i < quantidadeVertices; i++) {
        for (int j = 0; j < quantidadeVertices; j++) {

            // Ignoramos a diagonal porque estamos analisando apenas pares de vértices distintos.
            if (i == j) continue;

            // No grafo direcionado, precisamos verificar cada sentido separadamente.
            // Como percorremos a matriz inteira, estamos verificando:
            // i → j e também, em outra iteração j → i
            if (direcionado) {
                if (matriz[i][j] == 0) {
                    return false;
                }
            } else {
                //No grafo não direcionado, basta existir a aresta entre i e j.
                // Como a representação deve ser simétrica:
                // matriz[i][j] e matriz[j][i] representam a mesma aresta.
                if (matriz[i][j] == 0) {
                    return false;
                }
            }
        }
    }

    return true;
}

//!obs: essa função não está otimizada pois acaba fazendo exatamente a mesma verificação nos dois casos.







}