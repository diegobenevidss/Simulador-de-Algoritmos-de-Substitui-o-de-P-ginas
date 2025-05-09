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
    public Simulador() {
        setTitle("Simulador de Algoritmos de Substituição de Páginas");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField campoSequencia = new JTextField();
        JTextField campoQuadros = new JTextField();
        JButton botaoSimular = new JButton("Simular");
        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JPanel painelEntrada = new JPanel(new GridLayout(2, 1));
        JPanel linha1 = new JPanel(new BorderLayout());
        linha1.add(new JLabel("Sequência de páginas (ex: 1,2,3,4,2,1): "), BorderLayout.WEST);
        linha1.add(campoSequencia, BorderLayout.CENTER);

        JPanel linha2 = new JPanel(new BorderLayout());
        linha2.add(new JLabel("Número de quadros (ex: 3): "), BorderLayout.WEST);
        linha2.add(campoQuadros, BorderLayout.CENTER);
        linha2.add(botaoSimular, BorderLayout.EAST);

        painelEntrada.add(linha1);
        painelEntrada.add(linha2);

        setLayout(new BorderLayout());
        add(painelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        botaoSimular.addActionListener(e -> processarEntrada(campoSequencia.getText().trim(), campoQuadros.getText().trim(), resultadoArea));
        campoSequencia.addActionListener(e -> processarEntrada(campoSequencia.getText().trim(), campoQuadros.getText().trim(), resultadoArea));
    }

    private void processarEntrada(String entradaSeq, String entradaQuadros, JTextArea resultadoArea) {
        if (entradaSeq.isEmpty() || entradaQuadros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha ambos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Integer> sequenciaPaginas;
        int numQuadros;
        try {
            sequenciaPaginas = Arrays.stream(entradaSeq.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            numQuadros = Integer.parseInt(entradaQuadros.trim());

            if (numQuadros <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Verifique se os dados estão corretos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int fifo = Algoritmos.fifo(sequenciaPaginas, numQuadros);
        int lru = Algoritmos.lru(sequenciaPaginas, numQuadros);
        int clock = Algoritmos.clock(sequenciaPaginas, numQuadros);
        int otimo = Algoritmos.otimo(sequenciaPaginas, numQuadros);

        Map<String, Integer> resultados = new LinkedHashMap<>();
        resultados.put("FIFO", fifo);
        resultados.put("LRU", lru);
        resultados.put("Clock", clock);
        resultados.put("Ótimo", otimo);

        StringBuilder sb = new StringBuilder();
        sb.append("Sequência: ").append(sequenciaPaginas).append("\n");
        sb.append("Quadros: ").append(numQuadros).append("\n\n");
        resultados.forEach((alg, faltas) -> sb.append(alg).append(": ").append(faltas).append(" faltas\n"));

        String melhor = Collections.min(resultados.entrySet(), Map.Entry.comparingByValue()).getKey();
        sb.append("\nAnálise:");
        sb.append("\nO algoritmo com melhor desempenho (menos faltas de página): ").append(melhor);

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
