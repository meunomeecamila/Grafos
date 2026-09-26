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
Podemos adaptar o algoritmo de djikstra para que ele percorra todas as arestas, mesmo após chegar no vértice destino. Assim, não seria mais djikstra, mas funcionaria para acíclicos positivos, negativos e cíclicos com pesos positivos.   
**Nada funciona para cíclicos com ciclos negativos**

Mas, e como fazer para grafos cíclicos com pesos positivos e negativos?   

### Grafos cíclicos, com pesos positivos e negativos e ciclos positivos 

Em alguns casos, o algoritmo de Djikstra não irá conseguir resolver, pois quando se trata de pesos negativos, ele nem sempre funciona. Isso porque djikstra não visita necessariamente todas as arestas do grafo. 

Então, para resolver esse caso em específico, temos algumas soluções: 

**Solução 01 -> Permutação**
Permutar todos os caminhos possíveis usando permutação de vértices. Funciona, mas a complexidade seria muito ruim. 

**Solução 02 -> Repetir arestas**
Na iteração x, todos os vértices com x arestas do v inicial já foram atingidos.   
O algoritmo para quando não houver nenhuma alteração de uma iteração para outra. Isso significa que o tamanho do maior caminho é o valor da última iteração e todos os valores já foram devidamente corrigidos.    
Com essa solução, verificamos mais de uma vez a mesma aresta, mas é bem melhor do que resolver com permutação.   

Veja a seguir alguns exemplos:   

---

## Domínio e Funções 

Dado: F: A -> B   

- Chamamos de **domínio** de onde a função está vindo (A)
- Chamamos de **contradomínio** para onde está indo (B)

Para onde a função está indo é chamada de **imagem** caso todos os elementos de A tenham sido mapeados em B.   

### Função total vs parcial 
Chamamos de **função total** quando todos os elementos do meu domínio são mapeados no meu contradomínio. Na **função parcial**, alguns elementos do domínio não são mapeados no contradomínio.   

W:E -> [1,|E|] é uma função total pois todas as arestas são mapeadas, de acordo com a ordem (quantidade total de arestas).    

### Injetora vs Sobrejetora vs Bijetora 

- **Função Injetora:** Cada elemento do contradomínio recebe no máximo uma ligação. Valores diferentes de x sempre geram resultados diferentes de y. Podem sobrar elementos no conjunto de chegada sem nenhum correspondente, mas nenhum valor de y será resultado de dois valores diferentes de x.

W:E -> [1,|E|] ou W:E -> Z+ não são funcões com garantia de que são injetoras, porque nesses casos, arestas diferentes podem ser mapeadas com o mesmo valor.    

No brasileirão ou nas eleicões, por exemplo, temos funções injetoras. No pior dos casos, o brasileirão desempata por sorteio, e as eleições, por idade.   

- **Função Sobrejetora:** Todo elemento do contradomínio recebe pelo menos uma ligação. Nenhum elemento sobra sozinho no conjunto de chegada (ou seja, a imagem é igual ao contradomínio), mas um mesmo valor de y pode ser gerado por mais de um valor de x.

Nessas funções, conseguimos trazer de volta (auditoria). Isso faz com que elas sejam explicáveis. Árvores de decisão, por exemplo, podem ser mapeadas de volta. Se eu tenho elementos que eu não mapeio, não consigo trazer de volta.   

- **Função Bijetora:** É a combinação de ambas. Cada elemento do contradomínio recebe exatamente uma ligação. Não sobra nenhum elemento sem par no conjunto de chegada, e não há resultados repetidos. É um pareamento perfeito e exclusivo de um-para-um entre os dois conjuntos.

**Obs:** É importante ressaltar que além de pesos em arestas, também podemos ter pesos em vértices. Eles podem ser restrições, limites, importância, prioridade, capacidade máxima, entre outros. 

---