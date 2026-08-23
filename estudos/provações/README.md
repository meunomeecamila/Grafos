# Provações matemáticas

Nas aulas, nosso professor passa várias provações matemáticas e exercícios sobre grafos.

Aqui estão listados os principais deles, juntamente com suas demonstrações e algoritmos.

---

## 01 - Soma dos graus de um grafo simples não-direcionado

**Enunciado:**  
Prove que, se `G` é um grafo simples não-direcionado, a soma do grau de todos os vértices é par.

### Resposta

Essa propriedade é conhecida como **Teorema dos Apertos de Mão**.

Em um grafo não-direcionado, cada aresta possui duas extremidades. Portanto, cada aresta contribui com `1` para o grau de cada um dos dois vértices que ela conecta.

Assim, cada aresta contribui com exatamente `2` para a soma dos graus.

Se o grafo possui `m` arestas:

```text
soma dos graus = 2 * m
```

Como `2 * m` é sempre par:

```text
soma dos graus = par
```

Portanto:

> **A soma dos graus de todos os vértices de um grafo simples não-direcionado é sempre par.**

---

## 02 - Soma dos graus de um grafo simples direcionado

**Enunciado:**  
Se `G` é um grafo simples direcionado, mostre que a soma dos graus de todos os vértices é par.

### Resposta

Em um grafo direcionado, cada vértice possui:

- **grau de entrada** (`in-degree`);
- **grau de saída** (`out-degree`).

Cada aresta possui:

- uma origem, contribuindo `1` para o grau de saída;
- um destino, contribuindo `1` para o grau de entrada.

Se o grafo possui `m` arestas:

```text
soma dos graus de entrada = m

soma dos graus de saída = m
```

O grau total de um vértice é:

```text
grau(v) = grau de entrada + grau de saída
```

Portanto:

```text
soma dos graus
= soma dos graus de entrada + soma dos graus de saída
= m + m
= 2m
```

Como `2m` é sempre par:

> **A soma dos graus de todos os vértices de um grafo simples direcionado é sempre par.**

### Observação

Não é necessário que a soma dos graus de entrada seja par.

O que sempre acontece é:

```text
soma dos graus de entrada = número de arestas
soma dos graus de saída = número de arestas
```

Logo, a soma das duas é sempre par.

---

## 03 - Quantidade de vértices de grau ímpar

**Enunciado:**  
Mostre que a quantidade de vértices de grau ímpar é sempre par.

### Resposta

Pelo Teorema dos Apertos de Mão:

```text
soma dos graus = 2 * número de arestas
```

Portanto, a soma de todos os graus é par.

Podemos separar os vértices em dois grupos:

- vértices de grau par;
- vértices de grau ímpar.

A soma dos graus pares é necessariamente par, pois estamos somando apenas números pares.

Assim:

```text
soma dos graus pares + soma dos graus ímpares = número par
```

Como a soma dos graus pares já é par, a soma dos graus ímpares também precisa ser par.

Agora, uma soma de números ímpares é par somente quando existe uma quantidade **par** de números ímpares.

Por exemplo:

```text
3 + 5 = 8
```

Temos dois números ímpares e o resultado é par.

Já:

```text
3 + 5 + 7 = 15
```

Temos três números ímpares e o resultado é ímpar.

Logo:

> **A quantidade de vértices de grau ímpar em um grafo é sempre par.**

### Atenção

Não podemos afirmar que a quantidade de vértices de grau par é sempre par.

O que sabemos é que:

> **A soma dos graus dos vértices de grau par é par.**

---

## 04 - Número de componentes conexos

**Enunciado:**  
Projete um algoritmo para encontrar o número de componentes conexos de um grafo não-direcionado.

### Ideia

Podemos utilizar uma **Busca em Profundidade (DFS)**.

A ideia é percorrer todos os vértices do grafo.

Sempre que encontrarmos um vértice que ainda não foi visitado, iniciamos uma nova DFS a partir dele. Todos os vértices alcançados nessa busca pertencem à mesma componente conexa.

Portanto, cada nova DFS representa uma nova componente.

### Passo a passo

1. Criar um vetor `visitado`.
2. Inicializar o número de componentes com `0`.
3. Percorrer todos os vértices.
4. Se o vértice ainda não foi visitado:
   - aumentar o número de componentes;
   - realizar uma DFS a partir dele.
5. Ao final, retornar o número de componentes.

### Pseudocódigo

```text
componentes = 0

para cada vértice v:

    se v não foi visitado:

        componentes++

        DFS(v)

retornar componentes
```

A DFS:

```text
DFS(v):

    marcar v como visitado

    para cada vizinho u de v:

        se u não foi visitado:

            DFS(u)
```

### Java

```java
import java.util.*;

public class ComponentesConexos {

    static void dfs(int v, List<Integer>[] grafo, boolean[] visitado) {

        visitado[v] = true;

        for (int vizinho : grafo[v]) {

            if (!visitado[vizinho]) {
                dfs(vizinho, grafo, visitado);
            }
        }
    }

    static int contarComponentes(List<Integer>[] grafo) {

        boolean[] visitado = new boolean[grafo.length];

        int componentes = 0;

        for (int v = 0; v < grafo.length; v++) {

            if (!visitado[v]) {

                componentes++;

                dfs(v, grafo, visitado);
            }
        }

        return componentes;
    }
}
```

### Complexidade

Utilizando lista de adjacência:

```text
O(V + E)
```

---

## 05 - Dois vértices com o mesmo grau

**Enunciado:**  
Seja `G = (V,E)` um grafo simples conexo em que a quantidade de vértices é maior ou igual a `2`. Mostre que há pelo menos 2 vértices que possuem o mesmo grau.

### Resposta

Considere um grafo simples com `n` vértices.

Como o grafo é conexo e possui pelo menos dois vértices, nenhum vértice pode ter grau `0`.

Portanto:

```text
grau mínimo = 1
```

Como o grafo é simples, um vértice não pode possuir uma aresta para ele mesmo e não existem arestas paralelas.

Assim, um vértice pode estar conectado, no máximo, aos outros `n - 1` vértices.

Portanto:

```text
1 <= grau(v) <= n - 1
```

Existem então apenas `n - 1` valores possíveis de grau:

```text
1, 2, 3, ..., n - 1
```

Porém, temos `n` vértices.

Ou seja:

```text
n vértices
n - 1 possíveis graus
```

Pelo **Princípio da Casa dos Pombos**, pelo menos dois vértices devem possuir o mesmo grau.

Logo:

> **Em todo grafo simples conexo com pelo menos 2 vértices, existem pelo menos dois vértices com o mesmo grau.**

### Exemplo

Considere um grafo conexo com `6` vértices.

Os graus possíveis são:

```text
1, 2, 3, 4, 5
```

Temos:

```text
6 vértices
5 possíveis graus
```

Portanto, pelo menos dois vértices terão o mesmo grau.

---

## 06 - Número de subgrafos de um grafo completo

**Enunciado:**  
Seja `G = (V,E)` um grafo não-direcionado completo. Encontre o número de subgrafos que podem ser encontrados em `G`.

Considere que `G = K_n`.

### Quantidade de arestas

Em um grafo completo, cada vértice está conectado a todos os outros.

A quantidade de arestas é:

```text
|E| = n(n - 1) / 2
```

Essa fórmula representa a **quantidade de arestas**, e não a quantidade de vértices.

A quantidade de vértices é simplesmente:

```text
|V| = n
```

### Quantidade de subgrafos

Para criar um subgrafo, podemos:

1. escolher quais vértices estarão presentes;
2. escolher quais arestas estarão presentes.

Se escolhermos `k` vértices, existem:

```text
C(n,k)
```

maneiras de escolher esses vértices.

Entre esses `k` vértices, existem:

```text
k(k - 1) / 2
```

arestas possíveis.

Cada uma dessas arestas pode ser escolhida ou não.

Portanto, para `k` vértices existem:

```text
2^(k(k - 1)/2)
```

possibilidades de arestas.

Assim, o número total de subgrafos é:

```text
Σ [ C(n,k) * 2^(k(k - 1)/2) ]
```

para:

```text
k = 0 até n
```

Ou seja:

```text
                n
               ---
Número =       \    C(n,k) * 2^(k(k-1)/2)
               /
               ---
               k=0
```

Essa fórmula considera todos os subgrafos possíveis, inclusive o subgrafo vazio.

### Observação

Se o professor estiver considerando apenas **subgrafos geradores**, todos os `n` vértices precisam permanecer.

Nesse caso, cada uma das `|E|` arestas pode ser escolhida ou não:

```text
Número de subgrafos geradores = 2^|E|
```

---

## 07 - Fecho transitivo direto

**Enunciado:**  
Seja `G = (V,E)` um grafo direcionado. Projete um algoritmo para encontrar todos os vértices que podem ser **atingidos a partir de `u`**.

### Ideia

Queremos encontrar todos os vértices para os quais existe um caminho partindo de `u`.

Podemos realizar uma **DFS a partir de `u`**.

Todos os vértices visitados pela DFS podem ser atingidos a partir de `u`.

### Passo a passo

1. Criar um vetor `visitado`.
2. Começar a DFS em `u`.
3. Marcar `u` como visitado.
4. Para cada vizinho de `u`:
   - se ainda não foi visitado, realizar DFS nele.
5. Ao final, os vértices visitados são os vértices atingíveis a partir de `u`.

### Pseudocódigo

```text
FechoDireto(u):

    DFS(u)

    retornar visitados


DFS(v):

    marcar v como visitado

    para cada vizinho u de v:

        se u não foi visitado:

            DFS(u)
```

### Java

```java
import java.util.*;

public class FechoDireto {

    static void dfs(int v, List<Integer>[] grafo, boolean[] visitado) {

        visitado[v] = true;

        for (int vizinho : grafo[v]) {

            if (!visitado[vizinho]) {
                dfs(vizinho, grafo, visitado);
            }
        }
    }

    static boolean[] fechoDireto(List<Integer>[] grafo, int u) {

        boolean[] visitado = new boolean[grafo.length];

        dfs(u, grafo, visitado);

        // O próprio u não é considerado atingido por um caminho
        // de tamanho maior que zero.
        visitado[u] = false;

        return visitado;
    }
}
```

### Complexidade

```text
O(V + E)
```

---

## 08 - Fecho transitivo inverso

**Enunciado:**  
Seja `G = (V,E)` um grafo direcionado. Projete um algoritmo para encontrar todos os vértices que **atingem `u`**.

### Ideia

Agora queremos encontrar todos os vértices `v` para os quais existe um caminho:

```text
v → ... → u
```

Uma maneira simples de fazer isso é construir o **grafo transposto**.

No grafo transposto, todas as arestas têm sua direção invertida.

Por exemplo:

```text
A → B
```

vira:

```text
B → A
```

Depois de construir o grafo transposto, basta fazer uma DFS começando em `u`.

### Passo a passo

1. Criar um novo grafo.
2. Para cada aresta `v → w`:
   - adicionar `w → v` no grafo transposto.
3. Fazer uma DFS a partir de `u` no grafo transposto.
4. Os vértices encontrados são os que conseguem atingir `u` no grafo original.

### Pseudocódigo

```text
FechoInverso(u):

    criar grafo transposto

    para cada aresta v → w:

        adicionar w → v no grafo transposto

    DFS(u) no grafo transposto

    retornar visitados
```

### Java

```java
import java.util.*;

public class FechoInverso {

    static void dfs(int v, List<Integer>[] grafo, boolean[] visitado) {

        visitado[v] = true;

        for (int vizinho : grafo[v]) {

            if (!visitado[vizinho]) {
                dfs(vizinho, grafo, visitado);
            }
        }
    }

    static boolean[] fechoInverso(List<Integer>[] grafo, int u) {

        int n = grafo.length;

        List<Integer>[] transposto = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            transposto[i] = new ArrayList<>();
        }

        // Inverte todas as arestas
        for (int v = 0; v < n; v++) {

            for (int vizinho : grafo[v]) {

                transposto[vizinho].add(v);
            }
        }

        boolean[] visitado = new boolean[n];

        dfs(u, transposto, visitado);

        // O próprio u não é considerado
        visitado[u] = false;

        return visitado;
    }
}
```

### Complexidade

```text
O(V + E)
```

---

## 09 - Verificar se existe ciclo em um grafo direcionado

**Enunciado:**  
Seja `G = (V,E)` um grafo direcionado. Projete uma solução para dizer se há ciclo em `G`.

### Ideia

Podemos utilizar uma DFS e controlar três estados para cada vértice:

```text
0 = ainda não visitado
1 = está sendo visitado
2 = já foi finalizado
```

Durante a DFS, se encontrarmos uma aresta para um vértice que está no estado `1`, encontramos um ciclo.

Isso acontece porque esse vértice ainda está no caminho atual da DFS.

### Exemplo

Considere:

```text
A → B
    ↓
    C
    ↓
    B
```

Durante a DFS:

```text
A → B → C
```

Quando `C` encontra `B`, percebemos que `B` ainda está sendo visitado.

Logo:

```text
C → B
```

forma um ciclo.

### Pseudocódigo

```text
TemCiclo(G):

    para cada vértice v:

        estado[v] = 0

    para cada vértice v:

        se estado[v] == 0:

            se DFS(v) == verdadeiro:

                retornar verdadeiro

    retornar falso
```

DFS:

```text
DFS(v):

    estado[v] = 1

    para cada vizinho u de v:

        se estado[u] == 1:

            retornar verdadeiro

        se estado[u] == 0:

            se DFS(u) == verdadeiro:

                retornar verdadeiro

    estado[v] = 2

    retornar falso
```

### Java

```java
import java.util.*;

public class Ciclo {

    static boolean dfs(int v, List<Integer>[] grafo, int[] estado) {

        estado[v] = 1;

        for (int vizinho : grafo[v]) {

            // Encontrou um vértice que ainda está no caminho da DFS
            if (estado[vizinho] == 1) {
                return true;
            }

            if (estado[vizinho] == 0) {

                if (dfs(vizinho, grafo, estado)) {
                    return true;
                }
            }
        }

        estado[v] = 2;

        return false;
    }

    static boolean temCiclo(List<Integer>[] grafo) {

        int[] estado = new int[grafo.length];

        for (int v = 0; v < grafo.length; v++) {

            if (estado[v] == 0) {

                if (dfs(v, grafo, estado)) {
                    return true;
                }
            }
        }

        return false;
    }
}
```

### Complexidade

```text
O(V + E)
```

---

## 10 - Contabilizar componentes conexos através de busca

**Enunciado:**  
Contabilize o número de componentes conexos de um grafo através de busca (largura ou profundidade).

Essa questão utiliza a mesma ideia da questão `04`.

Podemos utilizar:

- DFS (Busca em Profundidade);
- BFS (Busca em Largura).

### Usando BFS

A ideia é a mesma: sempre que encontrarmos um vértice ainda não visitado, iniciamos uma nova BFS. Cada BFS representa uma componente conexa.

### Pseudocódigo

```text
componentes = 0

para cada vértice v:

    se v não foi visitado:

        componentes++

        adicionar v na fila
        marcar v como visitado

        enquanto a fila não estiver vazia:

            u = remover da fila

            para cada vizinho w de u:

                se w não foi visitado:

                    marcar w como visitado
                    adicionar w na fila

retornar componentes
```

### Java

```java
import java.util.*;

public class ComponentesBFS {

    static int contarComponentes(List<Integer>[] grafo) {

        boolean[] visitado = new boolean[grafo.length];

        int componentes = 0;

        for (int v = 0; v < grafo.length; v++) {

            if (!visitado[v]) {

                componentes++;

                Queue<Integer> fila = new LinkedList<>();

                fila.add(v);
                visitado[v] = true;

                while (!fila.isEmpty()) {

                    int atual = fila.remove();

                    for (int vizinho : grafo[atual]) {

                        if (!visitado[vizinho]) {

                            visitado[vizinho] = true;
                            fila.add(vizinho);
                        }
                    }
                }
            }
        }

        return componentes;
    }
}
```

### Complexidade

```text
O(V + E)
```

---

# Exercícios

## 01 - Exercícios do PDF

Exercícios completos:

- Exercício 01
- Exercício 03
- Exercício 11

---

# Resumo

| Questão | Conceito principal |
|---|---|
| 01 | Teorema dos Apertos de Mão |
| 02 | Graus de entrada e saída |
| 03 | Vértices de grau ímpar |
| 04 | Componentes conexos + DFS |
| 05 | Princípio da Casa dos Pombos |
| 06 | Contagem de subgrafos |
| 07 | Fecho transitivo direto |
| 08 | Fecho transitivo inverso + grafo transposto |
| 09 | Detecção de ciclos + DFS |
| 10 | Componentes conexos + BFS/DFS |

---

# Conceitos importantes para lembrar

### Soma dos graus

Para um grafo não-direcionado:

```text
soma dos graus = 2 * número de arestas
```

Para um grafo direcionado:

```text
soma dos graus de entrada = número de arestas

soma dos graus de saída = número de arestas
```

Logo:

```text
soma dos graus totais = 2 * número de arestas
```

### Grau dos vértices

Em um grafo simples com `n` vértices:

```text
0 <= grau(v) <= n - 1
```

Se o grafo também for conexo e possuir pelo menos 2 vértices:

```text
1 <= grau(v) <= n - 1
```

### DFS

A DFS pode ser utilizada para:

- encontrar componentes conexos;
- encontrar vértices atingíveis;
- encontrar fecho transitivo;
- detectar ciclos;
- percorrer um grafo.

### BFS

A BFS também pode ser utilizada para:

- encontrar componentes conexos;
- encontrar vértices atingíveis;
- encontrar menores caminhos em grafos não ponderados.

### Grafo transposto

Para um grafo direcionado:

```text
u → v
```

no grafo transposto se torna:

```text
v → u
```

Ele pode ser utilizado para encontrar o **fecho transitivo inverso**.

### Detecção de ciclos com DFS

Estados:

```text
0 → não visitado
1 → sendo visitado
2 → finalizado
```

Se encontrarmos:

```text
aresta para um vértice com estado 1
```

então existe um ciclo.