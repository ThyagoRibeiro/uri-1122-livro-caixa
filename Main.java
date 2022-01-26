package parte_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Resolução do problema Livro-Caixa (URI Online Judge | 1122) para a disciplina SIN5013
 * do Mestrado em Sistemas de Informação da EACH-USP
 *
 * @author Thyago Ribeiro dos Santos - nUSP 9377491
 */

class Main {

    static int[] numeros;
    static boolean[] operacoesSoma;
    static boolean[] operacoesSubtracao;
    static int f;
    static int n;
    static Map<Integer, Map<Integer, Boolean>> caixaByPosicaoMap;

    public static void main(String args[]) {

        Scanner scan = new Scanner (System.in);

        while(scan.hasNextInt()) {

            n = scan.nextInt();

            if(n == 0)
                break;

            f = scan.nextInt();
            numeros = new int[n];
            operacoesSoma = new boolean[n];
            operacoesSubtracao = new boolean[n];
            caixaByPosicaoMap = new HashMap<>();

            for(int i = 0; i < n; i++) {
                caixaByPosicaoMap.put(i, new HashMap<>());
                numeros[i] = scan.nextInt();
            }

            if(!tenta(0, 0))
                System.out.println("*");
            else
                System.out.println(operacoesToString());
        }

    }

    public static boolean tenta(int pos, int caixa) {

        if(pos == numeros.length)
            return caixa == f;

        if(caixaByPosicaoMap.get(pos).containsKey(caixa))
            return caixaByPosicaoMap.get(pos).get(caixa);

        boolean resultadoSoma = tenta(pos + 1, caixa + numeros[pos]);
        if(resultadoSoma)
            operacoesSoma[pos] = true;

        boolean resultadoSubtracao = tenta(pos + 1, caixa - numeros[pos]);
        if(resultadoSubtracao)
            operacoesSubtracao[pos] = true;

        caixaByPosicaoMap.get(pos).put(caixa, resultadoSoma || resultadoSubtracao);
        return caixaByPosicaoMap.get(pos).get(caixa);

    }

    private static String operacoesToString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numeros.length; i++) {

            if(operacoesSoma[i] && operacoesSubtracao[i])
                stringBuilder.append('?');
            else if (operacoesSoma[i])
                stringBuilder.append('+');
            else
                stringBuilder.append('-');

        }

        return stringBuilder.toString();

    }

}
