# Simulador de Algoritmos de Substituição de Páginas

Este projeto em Java simula os principais algoritmos de substituição de páginas utilizados em sistemas operacionais para gerenciar a memória virtual. São implementados os algoritmos:

- **FIFO (First-In First-Out)**
- **LRU (Least Recently Used)**
- **Relógio (Clock)**
- **Ótimo (Optimal)**

O simulador possui uma interface gráfica onde o usuário pode inserir:
- A **sequência de páginas** desejada
- O **número de quadros de memória**

Após a simulação, o programa exibe a quantidade de *faltas de página* para cada algoritmo, bem como um **gráfico de barras colorido** comparando os resultados.

## 📂 Estrutura

- `main/java/Main.java`: Classe principal do simulador, onde contêm todas as implementações dos algoritmos.
- `main/java/Simulador.java`: Interface gráfica que recebe os dados do usuário e apresenta os resultados.
- `main/java/GraficoBarras.java`: Responsável pela geração do gráfico de barras com os resultados.

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

1. Pré-requisitos para execução do projeto:
   ```
   JDK 17+
   JFreeChart 1.5.3
   JCommon 1.0.24

2. Compile todos os arquivos java:
   ```
   src/main/java/GraficoBarras.java 
   src/main/java/Main.java
   src/main/java/Simulador.java
   ```

3. Execute a classe:
   ```
   Simulador (src/main/java/Simulador.java)
   ```
   
4. Na interface gráfica, digite a **sequência de páginas** e o **número de quadros**:
   ```
   Ex: 1, 2, 3, 2, 4, 1, 5, 2, 4, 3, 2, 1
   Ex: 3
   ```
   Após digitar, clique no botão **Simular**.

## 📊 Saída Esperada

   ```
   Sequência: [1, 2, 3, 2, 4, 1, 5, 2, 4, 3, 2, 1]
   Quadros: 3
   
   Método FIFO - 10 faltas de página  
   Método LRU - 10 faltas de página  
   Método Relógio - 10 faltas de página  
   Método Ótimo - 7 faltas de página

   Análise:  
   O algoritmo com melhor desempenho (menos faltas de página) foi: Ótimo, com 7 faltas.
   ```

Em seguida, um **gráfico de barras** será exibido, com cores distintas para cada algoritmo e o número de faltas rotulado acima de cada barra.
## ⚙️ Configurações
   ```
   Você pode alterar a sequência de páginas ou o número de quadros diretamente no método main:

   java
   List<Integer> sequenciaPaginas = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 2, 4, 3, 2, 1);
   static final int NUM_QUADROS = 3;
   ```


## 👨‍💻 Autores
Desenvolvido por **Diego Benevides** e **Ian Sampaio**.  
Este projeto tem fins educacionais e demonstra como diferentes algoritmos de gerenciamento de memória se comportam diante da mesma sequência de páginas.