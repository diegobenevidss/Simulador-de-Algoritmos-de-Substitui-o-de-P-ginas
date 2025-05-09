# Simulador de Algoritmos de Substituição de Páginas

Este projeto em Java simula os principais algoritmos de substituição de páginas utilizados em sistemas operacionais para gerenciar a memória virtual. São implementados os algoritmos:

- **FIFO (First-In First-Out): remove a página mais antiga da memória.**
- **LRU (Least Recently Used): substitui a página que não é usada há mais tempo.**
- **Relógio (Clock):  uma otimização do FIFO com bits de uso que permitem “segunda chance”.**
- **Ótimo (Optimal): remove a página que será utilizada mais tarde no futuro — utilizado como referência teórica.**

O simulador possui uma interface gráfica onde o usuário pode inserir:
- A **sequência de páginas** desejada
- O **número de quadros de memória**

Após a simulação, o programa exibe a quantidade de **faltas de página** para cada algoritmo, bem como um **gráfico de barras colorido** comparando os resultados.

## 📂 Estrutura

- `main/java/Algoritmos.java`: Classe principal do simulador, onde contêm todas as implementações dos algoritmos.
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
   src/main/java/Algoritmos.java
   src/main/java/GraficoBarras.java 
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

FIFO: 10 faltas
LRU: 10 faltas
Clock: 10 faltas
Ótimo: 7 faltas

Análise:
O algoritmo com melhor desempenho (menos faltas de página): Ótimo
   ```

## 👨‍💻 Autores
Desenvolvido por **Diego Benevides** e **Ian Sampaio**.

## 🔗 Repositório

[Acesse o repositório do projeto no GitHub.](https://github.com/diegobenevidss/Simulador-de-Algoritmos-de-Substitui-o-de-P-ginas)
 
## **Referências**
- Java Swing: [Link1](https://docs.oracle.com/javase/tutorial/uiswing/), [Link2](https://www.devmedia.com.br/introducao-a-interface-gui-no-java/25646);
- JCommom: [Link](https://www.jfree.org/jcommon/);
- JFreeChart: [Link](https://www.jfree.org/jfreechart/);
- TANENBAUM, A. S.; BOS, H. Modern Operating Systems. 4th ed. Pearson, 2015;
- SILBERSCHATZ, A.; GALVIN, P. B.; GAGNE, G. Operating System Concepts. 10th ed. Wiley, 2018;
- STALLINGS, W. Sistemas Operacionais: Internals e Design Principles. 7ª ed. Pearson, 2013.

## **Palavras-Chave**
Paginação. Algorítimos de substituição. Sistemas operacionais. Java. FIFO. LRU. Clock. Ótimo. Simulador.

## Considerações Finais
Este projeto tem fins educacionais e demonstra como diferentes algoritmos de gerenciamento de memória se comportam diante da mesma sequência de páginas.