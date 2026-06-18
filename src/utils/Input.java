package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class Input {

    private Input() {
    }

    // ===================== TEXTO =====================
    public static String lerObrigatorio(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String valor = sc.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Este campo é obrigatório.");
        }
    }

    public static String lerOpcional(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    // ===================== INTEIROS =====================
    public static int lerInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(linha);
                if (valor < min || valor > max) {
                    System.out.println(mensagemIntervalo(min, max));
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Introduz um número inteiro.");
            }
        }
    }

    // Devolve null se o utilizador deixar vazio (usado em edições: Enter para manter).
    public static Integer lerIntOpcional(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) {
                return null;
            }
            try {
                int valor = Integer.parseInt(linha);
                if (valor < min || valor > max) {
                    System.out.println(mensagemIntervalo(min, max));
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Introduz um número inteiro.");
            }
        }
    }

    // ===================== DECIMAIS =====================
    public static double lerDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(linha);
                if (valor < min || valor > max) {
                    System.out.println(mensagemIntervalo(min, max));
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Introduz um número.");
            }
        }
    }

    public static Double lerDoubleOpcional(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim().replace(',', '.');
            if (linha.isEmpty()) {
                return null;
            }
            try {
                double valor = Double.parseDouble(linha);
                if (valor < min || valor > max) {
                    System.out.println(mensagemIntervalo(min, max));
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Introduz um número.");
            }
        }
    }

    // ===================== DATAS (ISO aaaa-mm-dd) =====================
    public static LocalDate lerData(Scanner sc, String prompt) {
        return lerData(sc, prompt, null);
    }

    public static LocalDate lerData(Scanner sc, String prompt, LocalDate minimo) {
        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim();
            try {
                LocalDate data = LocalDate.parse(linha);
                if (minimo != null && data.isBefore(minimo)) {
                    System.out.println("A data não pode ser anterior a " + minimo + ".");
                } else {
                    return data;
                }
            } catch (DateTimeParseException e) {
                System.out.println("A data deve estar no formato aaaa-mm-dd.");
            }
        }
    }

    public static LocalDate lerDataOpcional(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(linha);
            } catch (DateTimeParseException e) {
                System.out.println("A data deve estar no formato aaaa-mm-dd.");
            }
        }
    }

    // ===================== OUTROS =====================
    // Índice de uma lista: aceita 0..size-1.
    public static int lerIndice(Scanner sc, String prompt, int size) {
        return lerInt(sc, prompt, 0, size - 1);
    }

    public static boolean lerSimNao(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = sc.nextLine().trim().toLowerCase();
            if (linha.equals("s") || linha.equals("sim")) {
                return true;
            }
            if (linha.equals("n") || linha.equals("nao") || linha.equals("não")) {
                return false;
            }
            System.out.println("Responde 's' (sim) ou 'n' (não).");
        }
    }

    private static String mensagemIntervalo(double min, double max) {
        String minStr = (min == Math.floor(min)) ? String.valueOf((long) min) : String.valueOf(min);
        if (max == Integer.MAX_VALUE || max == Double.MAX_VALUE) {
            return "O valor tem de ser >= " + minStr + ".";
        }
        String maxStr = (max == Math.floor(max)) ? String.valueOf((long) max) : String.valueOf(max);
        return "O valor tem de estar entre " + minStr + " e " + maxStr + ".";
    }
}
