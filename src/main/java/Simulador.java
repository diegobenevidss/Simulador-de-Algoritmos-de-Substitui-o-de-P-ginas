package main.java;

import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Simulador extends JFrame {
    private static final int NUM_QUADROS = 3;

    public Simulador() {
        setTitle("Simulador de Algoritmos de Substituição de Páginas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField campoSequencia = new JTextField();
        JButton botaoSimular = new JButton("Simular");
        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JPanel painelEntrada = new JPanel(new BorderLayout());
        painelEntrada.add(new JLabel("Sequência de páginas (ex: 1,2,3,4,2,1): "), BorderLayout.WEST);
        painelEntrada.add(campoSequencia, BorderLayout.CENTER);
        painelEntrada.add(botaoSimular, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(painelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        // Lógica de ação extraída para método separado
        botaoSimular.addActionListener(e -> processarEntrada(campoSequencia.getText().trim(), resultadoArea));
        campoSequencia.addActionListener(e -> processarEntrada(campoSequencia.getText().trim(), resultadoArea));
    }

    private void processarEntrada(String entrada, JTextArea resultadoArea) {
        if (entrada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma sequência.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Integer> sequenciaPaginas;
        try {
            sequenciaPaginas = Arrays.stream(entrada.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sequência inválida. Use apenas números separados por vírgula.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int fifo = Main.fifo(sequenciaPaginas, NUM_QUADROS);
        int lru = Main.lru(sequenciaPaginas, NUM_QUADROS);
        int clock = Main.clock(sequenciaPaginas, NUM_QUADROS);
        int otimo = Main.otimo(sequenciaPaginas, NUM_QUADROS);

        Map<String, Integer> resultados = new LinkedHashMap<>();
        resultados.put("FIFO", fifo);
        resultados.put("LRU", lru);
        resultados.put("Relógio", clock);
        resultados.put("Ótimo", otimo);

        StringBuilder sb = new StringBuilder();
        resultados.forEach((alg, faltas) -> sb.append(alg).append(": ").append(faltas).append(" faltas\n"));

        String melhor = Collections.min(resultados.entrySet(), Map.Entry.comparingByValue()).getKey();
        sb.append("\nMelhor desempenho: ").append(melhor);

        resultadoArea.setText(sb.toString());
        mostrarGrafico(resultados);
    }

    private void mostrarGrafico(Map<String, Integer> resultados) {
        JFrame frameGrafico = new JFrame("Gráfico de Faltas de Página");
        frameGrafico.setSize(400, 300);
        frameGrafico.setLocationRelativeTo(this);
        frameGrafico.add(new GraficoBarras(resultados));
        frameGrafico.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Simulador().setVisible(true));
    }
}

