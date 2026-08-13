# Grafos

## Observações 
- A definição utilizada nesse documento é a do professor Silvio.

## Definição
> Grafos são conjuntos de vértices V e arestas E (edges).    
> Por definição, um grafo G = (V,E), onde V não pode ser 0, mas E sim.

Isso significa que um grafo não pode ter 0 vértices, mas pode ter 0 arestas.

```java
//implementação de um grafo por matriz
public GrafoMatriz(int quantidadeVertices) {
    this.quantidadeVertices = quantidadeVertices;
    this.matriz = new int[quantidadeVertices][quantidadeVertices];
}
```

---

## Conceitos básicos 

- Loops: arestas saindo de um vértice e voltando para ele mesmo.
- Arestas paralelas: duas ou mais arestas que possuem a mesma origem e destino.
- Cardinalidade de vértices: quantidade de vértices de um grafo
- Grafos nulos são aqueles que não possuem arestas. 
- Grafos simples são aqueles que não possuem loops e nem arestas paralelas
- Grafos completos são aqueles que possuem todas as relações possíveis entre vértices e arestas. Por definição, eles também devem ser simples.   

Dois grafos A e B são considerados iguais se A está contido (ou é subconjunto) de B e B 
está contido (ou é subconjunto) de A. Isso significa que mesmo que um grafo tenha arestas paralelas e outro não, eles são considerados iguais. Os dois subconjuntos têm a mesma cardinalidade de vértices, mesmo tendo quantidade de elementos diferentes. 

---

## Direcionado vs Não-direcionado
Grafos podem ser direcionados ou não.    
Para grafos direcionados, a direção das arestas importa e usamos parêntesis na sua representação.   
Para grafos não-direcionados, a direção e ordem dos elementos não faz diferença. Para eles, representamos usando chaves.    

Obs: Em grafos direcionados, (a,b) é diferente de (b,a) porque a apontar pra b e b apontar pra a não são a mesma coisa.   

Para calcular a cardinalidade das arestas, fazemos assim:    
- Direcionado: 0 <= |E| <= 2x fórmula    
- Não-direcionado: 0 <= |E| <= fórmula   

A fórmula pode ser n! / p!(n-p)! ou n(n-1)/2 (combinação de elementos 2 a 2)   
No direcionado, a fórmula é multiplicada por 2 pois nesse grafo a ordem dos elementos importa (a setinha possui dois sentidos). No não-direcionado, apenas uma aresta conecta os dois sentidos de um vértice.    

Matematicamente falando:   
(a,b) e (b,a) não são a mesma coisa! (direcionado)    
{a,b} e {b,a} são a mesma coisa! (não-direcionado)   

**obs:** Na hora de representar isso em código, é importante estar atento ao tipo de grafo. Caso ele seja não-direcionado, quando adicionarmos uma aresta (1,2), também devemos adicionar a aresta (2,1).    

---

## Denso vs Esparso
Grafos podem ser densos ou esparsos.     
Grafos densos possuem muitas arestas, sendo mais próximos do grafo completo.     
Grafos esparsos possuem poucas, sendo mais próximos do grafo nulo.   

---

## Pesos
Além de vértices e arestas, grafos também podem ter pesos. 

### Pesos em arestas
G = (V,E)   
E = {u,v}, u pertence a V e v pertence a V   

Podemos representar o peso com (G,W), em que G é o grafo constante e W (weight) é uma 
função que mapeia os pesos (W:E -> R)   
Logo, ficaria assim:    
(G,W) = ({u,v},W)   

### Pesos em vértices
Funcionam da mesma forma, porém W:V -> R (função mapeia vértices ao invés de arestas)   

Os pesos podem significar várias coisas, como nome do vértice, nome da aresta (como em ruas).   
Podemos ter um grafo com peso ponderado em arestas e vértices (G, W, Wlinha).    

**obs:** Na implementação de grafos por matriz de adjacência, é importante decidir o tipo de matriz baseado no que será representado (peso, label, arestas paralelas, etc). Caso seja representado apenas a existência de arestas ou não, a matriz pode ser booleana. Caso o peso ou a quantidade de arestas seja representada, a matriz deve ser inteira.

---

## Armazenamento de grafos
No código, os vértices são armazenados em forma de uma lista sequencial, que pode começar de 0 ou 1. Logo, se tivermos 5 vértices, eles poderão ser uma lista de {0,1,2,3,4} ou de {1,2,3,4,5}.    
Para as arestas, temos duas formas de armazenamento:        

### Matriz de Adjacência: 
Matriz de arestas [1,n] x [1,n]. É sempre uma matriz quadrada de n colunas e n linhas, sendo
n a quantidade de vértices.    

### Lista de Adjacência: 
Arestas formam listas vaseadas nos vértices que indicam as relações.    

Obs: ADJACENTES são vizinhos.    

A seguir, temos alguns exemplos de prós e contras de cada uma.    
**Obs:** de acordo com o professor, é necessário analisar o que é pedido na questão antes de decidir qual das duas estruturas será usada. 

| Estrutura de Dados | Prós | Contras |
| :--- | :--- | :--- |
| **Lista de Adjacência** | • União e inclusão de vértices é comum<br>• Bom para grafos esparsos (ou nulos)<br>• Boa para pesquisar, remover e incluir vértices<br>• Melhor para fusão de vértices | • Ruim para grafos completos<br>• Para pesos, tem que fazer um objeto<br>• Custo adicional de ponteiro<br>• Ruim para pesquisar, remover e incluir arestas |
| **Matriz de Adjacência** | • Boa para grafos completos<br>• Bom para pesquisar, remover e incluir arestas<br>• Fácil de representar grafos direcionados, pesos, labels | • Ruim para grafos nulos ou esparsos (espaço atoa)<br>• Ruim para pesquisar, remover e incluir vértices (caso matriz não tenha espaço para aumentar, seria necessário realocar)<br>• Ruim para fusão de vértices |

obs: Fusão de vértices

Matrizes podem auxiliar na representação de outras características dos grafos, podendo
ser de vários tipos: 
- Booleana (representar true se tiver aresta e false se não tiver aresta entre 2 vértices)
- Inteira (podendo representar quantidade de arestas paralelas ou pesos)
- String (podendo representar labels)

## Grau

O grau de um vértice pe definido por quantas arestas estão conectadas a ele. 
Para grafos direcionados, existe grau de entrada e de saída. 

## Conectividade 

Grafos conexos são grafos nos quais é possível chegar de b até c mesmo sem ter uma aresta entre eles. Se há uma sequência de vértice-aresta-vértice... entre dois vértices quaisquer, 
há um caminho entre eles. 

Obs: Para ser conexo, todos devem estar ligados de alguma forma, mas não é necessário
ter todas as conexões. 

Um caminho apenas é válido se o primeiro vértice de P(a,b) for o primeiro do caminho e o último vértice for o último do caminho. Caminhos são considerados simples se não há repetição dos vértices, a não ser a origem. 

Caminhos que saem de um vértice e chegam nele mesmo são chamados de ciclos. 
Para ser considerado um ciclo, o número de arestas percorridas deve ser maior que zero. 
Logo, loops são ciclos. 

Um caminho que contém ciclos não é um caminho simples, porque para formar um ciclo necessariamente algum vértice é repetido. 

## Subgrafos
Subgrafos são partes de um grafo, onde seus vértices e arestas estão contidos no grafo inicial. O próprio grafo e o conjunto vazio são considerados subgrafos dele mesmo. 

Componentes conexos são subgrafos conexos que possuem o maior número de vértices e arestas mantendo a conectividade. Um grafo pode ter vários componentes conexos. 