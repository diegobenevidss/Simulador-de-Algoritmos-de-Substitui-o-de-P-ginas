package main.java;

import java.util.*;

public class Algoritmos {
    public static int fifo(List<Integer> paginas, int quadros) {
        Set<Integer> memoria = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        int faltas = 0;

        for (int pagina : paginas) {
            if (!memoria.contains(pagina)) {
                if (memoria.size() == quadros) {
                    int removida = fila.poll();
                    memoria.remove(removida);
                }
                memoria.add(pagina);
                fila.add(pagina);
                faltas++;
            }
        }
        return faltas;
    }

    public static int lru(List<Integer> paginas, int quadros) {
        Set<Integer> memoria = new HashSet<>();
        LinkedHashMap<Integer, Integer> usoRecente = new LinkedHashMap<>();
        int faltas = 0;

        for (int i = 0; i < paginas.size(); i++) {
            int pagina = paginas.get(i);
            if (!memoria.contains(pagina)) {
                if (memoria.size() == quadros) {
                    int lru = usoRecente.entrySet().iterator().next().getKey();
                    memoria.remove(lru);
                    usoRecente.remove(lru);
                }
                memoria.add(pagina);
                faltas++;
            } else {
                usoRecente.remove(pagina);
            }
            usoRecente.put(pagina, i);
        }

        return faltas;
    }

    public static int clock(List<Integer> paginas, int quadros) {
        int[] memoria = new int[quadros];
        boolean[] uso = new boolean[quadros];
        Arrays.fill(memoria, -1);

        int ponteiro = 0;
        int faltas = 0;

        for (int pagina : paginas) {
            boolean jaNaMemoria = false;
            for (int i = 0; i < quadros; i++) {
                if (memoria[i] == pagina) {
                    uso[i] = true;
                    jaNaMemoria = true;
                    break;
                }
            }

            if (!jaNaMemoria) {
                while (uso[ponteiro]) {
                    uso[ponteiro] = false;
                    ponteiro = (ponteiro + 1) % quadros;
                }
                memoria[ponteiro] = pagina;
                uso[ponteiro] = true;
                ponteiro = (ponteiro + 1) % quadros;
                faltas++;
            }
        }

        return faltas;
    }

    public static int otimo(List<Integer> paginas, int quadros) {
        Set<Integer> memoria = new HashSet<>();
        int faltas = 0;

        for (int i = 0; i < paginas.size(); i++) {
            int pagina = paginas.get(i);

            if (!memoria.contains(pagina)) {
                if (memoria.size() == quadros) {
                    int paginaParaRemover = -1;
                    int maisDistante = -1;

                    for (int p : memoria) {
                        int proximoUso = paginas.subList(i + 1, paginas.size()).indexOf(p);
                        if (proximoUso == -1) {
                            paginaParaRemover = p;
                            break;
                        } else if (proximoUso > maisDistante) {
                            maisDistante = proximoUso;
                            paginaParaRemover = p;
                        }
                    }

                    memoria.remove(paginaParaRemover);
                }

                memoria.add(pagina);
                faltas++;
            }
        }

        return faltas;
    }
}
