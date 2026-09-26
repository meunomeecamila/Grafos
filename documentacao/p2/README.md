# 🕸️ Grafos -> Prova 01

## Observações 
- A definição utilizada nesse documento é a do professor Silvio.

## Djikstra 
O algoritmo de djikstra é utilizado quando temos W:E -> Z+, isto é, uma função que mapeia o conjunto de arestas em algum conjunto. Nesse caso, é o de inteiros positivos. Logo, usamos djikstra para descobrir o menor caminho entre dois vértices, considerando como critério as arestas ponderadas.    

Ele segue a mesma ideia da **ordenação topológica**, vista na p1. No caso dos inteiros positivos, mesmo podendo ter ciclos, os clicos não irão atrapalhar, pois o menor caminho não irá passar diversas vezes no mesmo ciclo, uma vez que por serem valores positivos, sempre iria aumentar a soma de pesos. 

**Algoritmo explicado**

```java
public int dijkstra(List<List<Aresta>> grafo, int origem, int destino) {
    int[] d = new int[grafo.size()];
    Arrays.fill(d, Integer.MAX_VALUE);
    d[origem] = 0;
    
    Set<Integer> naoVisitados = new HashSet<>();
    for (int i = 0; i < grafo.size(); i++) naoVisitados.add(i);

    while (!naoVisitados.isEmpty()) {
        // Encontra o vértice com a menor distância
        int u = -1, min = Integer.MAX_VALUE;
        for (int i : naoVisitados) if (d[i] < min) min = d[u = i];
        
        // Para se não há mais caminhos ou se chegou no destino
        if (u == -1 || u == destino) break;
        naoVisitados.remove(u);

        // Atualiza a distância dos vizinhos
        for (Aresta a : grafo.get(u)) {
            if (naoVisitados.contains(a.destino)) {
                d[a.destino] = Math.min(d[a.destino], d[u] + a.peso);
            }
        }
    }
    
    return d[destino]; // Retorna o menor custo até o destino
}
```

**Obs 01:** O algoritmo de djikstra funciona apenas para PESOS POSITIVOS. Isso inclui grafos acíclicos e grafos cíclicos com ciclos positivos. Ciclos positivos são ciclos que após uma volta completa, tem um valor arrecadado positivo.     

**Obs 02:** Não é possível **conceitualmente** encontrar o menor caminho em grafos cíclicos com pesos negativos. Isso porque, como cada iteração e volta no ciclo diminui mais o peso, ficaria - infinito. É um problema conceitual.   

### Adaptação 
Podemos adaptar o algoritmo de djikstra para que ele percorra todas as arestas, mesmo após chegar no vértice destino. Assim, não seria mais djikstra, mas funcionaria para acíclicos positivos, negativos e cíclicos com pesos positivos e negativos, mas sem ciclos negativos.   
**Nada funciona para cíclicos com ciclos negativos**

---

## Domínio e Funções 
