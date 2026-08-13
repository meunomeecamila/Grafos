# Grafos

## Conceitos básicos
Grafos são conjuntos de vértices V e arestas E (edges).    
Por definição, um grafo G = (V,E), onde V não pode ser 0, mas E sim.   

Grafos nulos são aqueles que não possuem arestas. 
Grafos simples são aqueles que não possuem loops (arestas saindo de um vértice e voltando para ele mesmo) e arestas paralelas (duas ou mais arestas que tem a mesma origem e destino).  
Grafos completos são aqueles que possuem todas as relações possíveis entre vértices e arestas. Por definição, eles também devem ser simples.   

Dois grafos A e B são considerados iguais se A está contido (é subconjunto) de B e B 
está contido (ou é subconjunto) de A. Isso significa que mesmo que um grafo tenha arestas paralelas e outro não, eles são considerados iguais. Os dois subconjuntos têm a mesma cardinalidade de vértices, mesmo tendo quantidade de elementos diferentes. 

## Direcionado vs Não-direcionado
Grafos podem ser direcionados ou não.    
Para grafos direcionados, a direção das arestas importa e usamos parêntesis.
Para grafos não-direcionados, a direção e ordem dos elementos não faz diferença. Para eles, representamos usando chaves.  

Obs: Em grafos direcionados, (a,b) é diferente de (b,a) porque a apontar pra b e b apontar pra a não são a mesma coisa. 

Para calcular a cardinalidade das arestas, fazemos assim: 
- Direcionado: 0 <= |E| <= 2x fórmula 
- Não-direcionado: 0 <= |E| <= fórmula

A fórmula pode ser n! / p!(n-p)! ou n(n-1)/2
No direcionado, a fórmula é multiplicada por 2 pois nesse grafo a ordem dos elementos importa (a setinha possui dois sentidos). No não-direcionado, apenas uma aresta conecta os dois sentidos de um vértice. 

Matematicamente falando:
(a,b) e (b,a) não são a mesma coisa! (direcionado)
{a,b} e {b,a} são a mesma coisa! (não-direcionado)

## Denso vs Esparso
Grafos podem ser densos ou esparsos. 
Grafos densos possuem muitas arestas, sendo mais próximos do completo. 
Grafos esparsos possuem poucas, sendo mais próximos do nulo. 

## Pesos
Além de vértices e arestas, grafos também podem ter pesos. 

Pesos em arestas
G = (V,E)
E = {u,v}, u pertence a V e v pertence a V

Podemos representar o peso com (G,W), em que G é o grafo constante e W é uma 
função que mapeia os pesos (W:E -> R)
(G,W) = ({u,v},W)

Pesos em vértices
Funcionam da mesma forma, porém W:V -> R


Os pesos podem significar várias coisas, como nome do vértice, nome da aresta (como em ruas).
Podemos ter um grafo com peso ponderado em arestas e vértices (G, W, Wlinha). 

## Armazenamento de grafos
No código, temos duas formas de armazenar grafos: 

Matriz de Adjacência: 
Matriz de arestas [1,n] x [1,n]. É sempre uma matriz quadrada de n colunas e n linhas, sendo
n a quantidade de vértices. 

Lista de Adjacência: 
Arestas formam listas vaseadas nos vértices que indicam as relações. 

Obs: ADJACENTES são vizinhos. 

Prós de Lista de Adjacência: 
- União e inclusão de vértices é comum 
- Bom para grafos esparsos (ou nulos)
- Boa para pesquisar, remover e incluir vértices 
- Melhor para fusão de vértices

Contras da Lista de Adjacência: 
- Ruim para grafos completos 
- Para pesos, tem que fazer um objeto
- Custo adicional de ponteiro 
- Ruim para pesquisar, remover e incluir arestas

Prós de Matriz de Adjacência 
- Boa para grafos completos 
- Bom para pesquisar, remover e incluir arestas
- Fácil de representar grafos direcionados, pesos, labels

Contras de Matriz de Adjacência 
- Ruim para grafos nulos ou esparsos (espaço atoa)
- Ruim para pesquisar, remover e incluir vértices (caso matriz não tenha espaço para aumentar, seria necessário realocar)
- Ruim para fusão de vértices

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