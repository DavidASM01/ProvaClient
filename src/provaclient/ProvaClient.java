/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provaclient;

import java.util.List;
import java.util.Scanner;
import webservice.Alternativas;
import webservice.Questao;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ProvaClient {

    private static java.util.List<webservice.Questao> consultquestao(int idquestao) {
        webservice.ProvaWebService_Service service = new webservice.ProvaWebService_Service();
        webservice.ProvaWebService port = service.getProvaWebServicePort();
        return port.consultquestao(idquestao);
    }

    private static java.util.List<webservice.Alternativas> consultalternativas(int idquestao) {
        webservice.ProvaWebService_Service service = new webservice.ProvaWebService_Service();
        webservice.ProvaWebService port = service.getProvaWebServicePort();
        return port.consultalternativas(idquestao);
    }

    private static java.util.List<webservice.Questao> consultcorrecao(int idquestao) {
        webservice.ProvaWebService_Service service = new webservice.ProvaWebService_Service();
        webservice.ProvaWebService port = service.getProvaWebServicePort();
        return port.consultcorrecao(idquestao);
    }

    private static void updatequestao(int idquestao, java.lang.String enunciado, java.lang.String correta, int score) {
        webservice.ProvaWebService_Service service = new webservice.ProvaWebService_Service();
        webservice.ProvaWebService port = service.getProvaWebServicePort();
        port.updatequestao(idquestao, enunciado, correta, score);
    }

    private static java.util.List<webservice.Questao> allquestao() {
        webservice.ProvaWebService_Service service = new webservice.ProvaWebService_Service();
        webservice.ProvaWebService port = service.getProvaWebServicePort();
        return port.allquestao();
    }

    public static void main(String[] args) {

        System.out.print("Bem vindo o que deseja fazer?" + "\n" + "Repondas as abaixo questoes"); 

        Questao all = (Questao) allquestao(); /*verifica o total de questoes armazenadas */

        for (int i = 1; i == all.length(); i++) { /*Exibe todas as questoes ate aa ultima cadastrada no banco */
            Questao Quest = (Questao) consultquestao(i); /*consulta o webservice e busca e armazena as questoes */
            Alternativas Alter = (Alternativas) consultalternativas(i); /*consulta o webservice e busca e armazena as alternativas  */
            System.out.print(Quest.getEnunciado() + "\n"); /*Exibe o enunciado */
            System.out.print(Alter.getA() + "\n"); /*Exibe as alternativas */
            System.out.print(Alter.getB() + "\n");
            System.out.print(Alter.getC() + "\n");
            System.out.print(Alter.getD() + "\n");
            System.out.print("Resposta:"); /*Solicita a resposta */
            String resposta = new Scanner(System.in).toString(); /*Armazena a resposta */
            String certa = Quest.getCorreta(); /*Busca a resposta Correta*/

            int Score = Quest.getScore(); /*Armazena o score de dificuldade atual da questao*/

            if (resposta == certa) { /*Verifica se a questao está correta e retira uma unidade do score*/
                System.out.print("Resposta Correta parabéns");
                Score = Score - 1;
            } else { /*Verifica se a questao está errada e adciona uma unidade ao score*/
                System.out.print("Que pena vc errou");
                Score = Score + 1;
            }

            updatequestao(Quest.getIdquestao(), Quest.getEnunciado(), Quest.getCorreta(), Score); /*Faz um update do Score no banco Ultilizando o WebService*/

        }

    }

}
