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