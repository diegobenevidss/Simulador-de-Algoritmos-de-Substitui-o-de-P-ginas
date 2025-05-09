package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GraficoBarras extends JPanel {
    private final Map<String, Integer> resultados;
    private final Color[] cores = {Color.BLUE, Color.RED, Color.GREEN.darker(), Color.ORANGE};

    public GraficoBarras(Map<String, Integer> resultados) {
        this.resultados = resultados;
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int largura = getWidth();
        int altura = getHeight();
        int margem = 50;
        int espacamento = 20;

        int numBarras = resultados.size();
        int larguraBarra = (largura - 2 * margem - (numBarras - 1) * espacamento) / numBarras;

        int maxFaltas = resultados.values().stream().max(Integer::compare).orElse(1);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int i = 0;
        for (Map.Entry<String, Integer> entry : resultados.entrySet()) {
            int x = margem + i * (larguraBarra + espacamento);
            int barHeight = (int) ((altura - 2 * margem) * ((double) entry.getValue() / maxFaltas));
            int y = altura - margem - barHeight;

            g2d.setColor(cores[i % cores.length]);
            g2d.fillRect(x, y, larguraBarra, barHeight);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, larguraBarra, barHeight);

            String nome = entry.getKey();
            FontMetrics fm = g2d.getFontMetrics();
            int nomeX = x + (larguraBarra - fm.stringWidth(nome)) / 2;
            g2d.drawString(nome, nomeX, altura - 30);

            String faltas = String.valueOf(entry.getValue());
            int faltasX = x + (larguraBarra - fm.stringWidth(faltas)) / 2;
            g2d.drawString(faltas, faltasX, y - 5);

            i++;
        }

        g2d.drawLine(margem - 10, altura - margem, margem - 10, margem);
    }
}
