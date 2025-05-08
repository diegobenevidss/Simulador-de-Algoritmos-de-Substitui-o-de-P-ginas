# Simulador de Algoritmos de Substituição de Páginas

Este projeto em Java simula os principais algoritmos de substituição de páginas utilizados em sistemas operacionais para gerenciar a memória virtual. São implementados os algoritmos:

- **FIFO (First-In First-Out)**
- **LRU (Least Recently Used)**
- **Relógio (Clock)**
- **Ótimo (Optimal)**

O simulador executa cada algoritmo com uma sequência fixa de páginas e um número definido de quadros de memória, exibindo a quantidade de *faltas de página* para cada estratégia.

## 📂 Estrutura

O código principal está localizado em `main.java.Main` e pode ser executado diretamente como uma aplicação Java padrão.

## 🧠 Algoritmos Implementados

### FIFO
Remove a página mais antiga da memória, ou seja, a primeira que entrou.

### LRU
Remove a página que não é usada há mais tempo.

### Relógio
Utiliza um ponteiro circular e bits de uso para simular uma segunda chance para as páginas.

### Ótimo
Remove a página que será usada mais tardiamente no futuro (ideal, porém irrealista em sistemas reais).

## ▶️ Execução

1. Compile o código:
   ```bash
   javac main/java/Main.java
   ```

2. Execute:
   ```bash
   java main.java.Main
   ```

## 📊 Saída Esperada

```bash
Método FIFO - 9 faltas de página  
Método LRU - 8 faltas de página  
Método Relógio - 9 faltas de página  
Método Ótimo - 7 faltas de página  

Análise:  
O algoritmo com melhor desempenho (menos faltas de página) foi: Ótimo, com 7 faltas.
```

## ⚙️ Configurações

Você pode alterar a sequência de páginas ou o número de quadros diretamente no método `main`:

```java
List<Integer> sequenciaPaginas = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 2, 4, 3, 2, 1);
static final int NUM_QUADROS = 3;
```

## 👨‍💻 Autores

Desenvolvido por **Diego Benevides** e **Ian Sampaio**.  
Este projeto tem fins educacionais e demonstra como diferentes algoritmos de gerenciamento de memória se comportam diante da mesma sequência de páginas.
