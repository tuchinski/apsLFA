/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsautomatos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Leonardo Mendonça Tuchinski
 */
public class APSAutomatos{
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
       
        //declaração Alfabeto de entrada(retirado do arquivo)
        List<Character> alfabetoEntrada = new LinkedList<>();
        
        /**
         *declaração alfabeto Pilha(retirado do arquivo)
         */
        List<Character> alfabetoPilha = new LinkedList<>();
        
        //declaração simbolo Epsilon(retirado do arquivo)
        char epsilon = 0; 
        
        //declaração Simbolo inicial Pilha(retirado do arquivo)
        char simboloInicialPilha = 0;
                
        //declaração conjunto de estados(retirado do arquivo)
        List<String> conjuntoEstados = new LinkedList<>();
        
        //declaração estado Inicial(retirado do arquivo)
        String estInicial = null;
        
        //declaraçãoconj dos estados de aceitação(retirado do arquivo)
        List<String> conjuntoEstadosAceitacao = new LinkedList<>();
        
        //List<Transicao> transicoes = new LinkedList<>();
        // Declaração da variavel transições(retirado do arquivo)
        List<List<String>> transicoes2 = new LinkedList<>();
        //List<String>[0] = estado atual
        //List<String>[1] = Simbolo atual da palavra
        //List<String>[2] = Simbolo atual da pilha
        //List<String>[3] = Novo estado
        //List<String>[4] = Simbolos a empilhar(topo a esquerda e base a direita)
        
        //palavra inicial(a palavra que foi digitada)
        String palavra = "";
        
        //Pilha inicial do automato
        Stack<Character> pilhaAutomato = new Stack<>();
        
        //Pilha de Automatos(contem o estado atual, a palavra que ainda nao foi processada e a pilha atual do Automato)
        List<Automato> listaAutomatos = new ArrayList<>();
              
        //contador para verificar em qual linha do arquivo está sendo lida
        int cont = 0;
        
        //recebe a linha atual do arquivo
        String line;
        
        //recebe a divisão da linha(é um vetor de string, onde cada posição é um "token")
        String vetor[];
        
        //verifica se o automato foi aceito
        boolean eAceito = false;
        
        //tenta fazer a leitura do arquivo
        try (BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream("/home/todos/alunos/cm/a1792334/Documentos/apsLFA/teste.txt")))) {
               //enquanto houver linhas disponiveis faça
            while (((line = data.readLine()) != null)) {
                /*esse switch serve para salvar cada dado de cada linha na sua respectiva variável
                  a ordem é: linha 1 - alfabeto de entrada
                             linha 2 - alfabeto da pilha
                             linha 3 - simbolo que representa epsilon
                             linha 4 - simbolo inicial da pilha
                             linha 5 - conjunto de estados
                             linha 6 - estado inicial
                             linha 7 - conjunto dos estados de aceitação
                             linha 8 até o fim do arquivo - transições no formato estado atual,
                             simbolo atual da palavra, simbolo do topo da pilha, novo estado, 
                             novos simbolos a serem empilhados (topo a esquerda, base a direita*/
                switch (cont) {
                    case 0:
                        //divide a linha atual para o vetor de Strings
                        vetor = line.split(" ");
                        int i = 0;
                        for(String s: vetor){
                            alfabetoEntrada.add(s.charAt(0));
                            i++;
                        }
                        //adiciona todas as posiçoes do vetor na lista, utilizando a 
                        //função Arrays.asList(vetor) que serve para fazer o "casting"
                        //alfabetoEntrada.addAll(Arrays.asList(vetor));
                        break;
                    case 1:
                        vetor = line.split(" ");
                        //esse for percorre o vetor, string por string, até o fim
                        // for(tipo da variavel : onde essas variáveis estão)
                        int j = 0;
                        for(String s : vetor){
                            if(!s.isEmpty()){
                                alfabetoPilha.add(s.charAt(0));
                            }
                        }
                        break;
                    case 2:
                        epsilon = line.charAt(0);
                        break;
                    case 3:
                        simboloInicialPilha = line.charAt(0);
                        //System.out.println(simboloInicialPilha);
                        break;
                    case 4:
                        vetor = line.split(" ");
                        conjuntoEstados.addAll(Arrays.asList(vetor));
                        break;
                    case 5:
                        estInicial = line;
                        break;
                    case 6:
                        vetor = line.split(" ");
                        conjuntoEstadosAceitacao.addAll(Arrays.asList(vetor));
                        break;
                    default:
                        vetor = line.split(" ");
                        transicoes2.add((Arrays.asList(vetor)));
                        //Transicao t = new Transicao(vetor[0], vetor[1], vetor[2], vetor[3], vetor[4]);
                        //transicoes.add(t);
                        break;
                }
                cont++;
                
            }
        }
        //se o arquivo não for encontrado
        catch(FileNotFoundException a){
            System.out.println(a.toString());
        }  
        
        //verifica se a palavra possui algum simbolo que nao está no alfabeto
        for(int i=0;i<palavra.length();i++){
            if(!alfabetoEntrada.contains(palavra.charAt(i))){
                System.out.println("A palavra contem simbolo(s) que não faz parte do alfabeto");
                System.exit(1);
            }
        }         
        //inicia a pilha com o simbolo inicial da pilha
        pilhaAutomato.push(simboloInicialPilha);
        //cria um objeto Automato e adiciona na lista de Automato(é o automato inicial)
        Automato a = new Automato(palavra, estInicial, pilhaAutomato);
        listaAutomatos.add(a);
        
        //identifica o automato que está sendo computado
        int elementoLista = 0;
        
        while(!listaAutomatos.isEmpty()){
            Automato automatoAtual = listaAutomatos.get(elementoLista);
            automatoAtual.setPalavraNaoProcessada(listaAutomatos.get(elementoLista).getPalavraNaoProcessada());
            if(automatoAtual.getPalavraNaoProcessada().equals("")){
                if((automatoAtual.pilha.isEmpty()) | conjuntoEstadosAceitacao.contains(automatoAtual.getEstAtual())){
                    eAceito = true;
                    break;
                }
            }
            //percorre a lista de transições
            for(List<String> ls: transicoes2){
                //verifica se o estado atual do automato que está sendo computado 
                //é igual ao estado atual que a transição em questão pede
                if(automatoAtual.getEstAtual().equals(ls.get(0))){  
                   // verifica se o simbolo da palavra do automato é igual ao 
                   // simbolo da transiçao em questão
                   if(automatoAtual.getPalavraNaoProcessada().charAt(0) == ls.get(1).charAt(0)) {
                       //verifica se o topo da pilha do automato é igual ao simbolo determinado na transição
                       //PRIMEIRA VERIFICAÇÃO
                       if(automatoAtual.getPilha().peek() == ls.get(2).charAt(0)){
                           automatoAtual.setPalavraNaoProcessada(automatoAtual.getPalavraNaoProcessada().substring(1));
                           automatoAtual.pilha.pop();
                           String simbolosEmpilhar = ls.get(4);
                           if(!simbolosEmpilhar.equals(epsilon)){
                               for(int var = simbolosEmpilhar.length();var<=0;var--){
                                   automatoAtual.pilha.push(simbolosEmpilhar.charAt(var));
                               }
                           }
                           automatoAtual.setEstAtual(ls.get(3));
                           automatoAtual.setAvancou(true);
                       }else //SEGUNDA VERIFICAÇÃO: verifica se o simbolo do topo da pilha na transição é epsilon 
                           if(ls.get(2).equals(epsilon)){
                           automatoAtual.setPalavraNaoProcessada(automatoAtual.getPalavraNaoProcessada().substring(1));
                           String simbolosEmpilhar = ls.get(4);
                           if(!simbolosEmpilhar.equals(epsilon)){
                               for(int var = simbolosEmpilhar.length();var<=0;var--){
                                   automatoAtual.pilha.push(simbolosEmpilhar.charAt(var));
                               }
                           }
                           automatoAtual.setEstAtual(ls.get(3));
                           automatoAtual.setAvancou(true);
                       }
                   }
                   //verifica se o simbolo da palavra pedido pela transiçao é epsilon
                   if(ls.get(1).equals(epsilon)){
                       //TERCEIRA VERIFICAÇÃO
                       //verifica se o topo da pilha do automato é igual ao simbolo determinado na transição
                       if(automatoAtual.pilha.peek() == ls.get(2).charAt(0)){
                           Stack<Character> pilhaNovoAutomato = (Stack<Character>) automatoAtual.pilha.clone();
                           pilhaNovoAutomato.pop();
                           String simbolosEmpilhar = ls.get(4);
                           if(!simbolosEmpilhar.equals(epsilon)){
                               for(int var = simbolosEmpilhar.length();var<=0;var--){
                                   automatoAtual.pilha.push(simbolosEmpilhar.charAt(var));
                               }
                           }   
                           Automato novoAutomato = new Automato(automatoAtual.getPalavraNaoProcessada(), ls.get(3), pilhaNovoAutomato);
                           listaAutomatos.add(novoAutomato);
                           automatoAtual.setAvancou(true);
                       }else
                            if(ls.get(2).equals(epsilon)){
                                Automato novoAutomato = new Automato(automatoAtual.getPalavraNaoProcessada(), automatoAtual.getEstAtual(), automatoAtual.pilha);
                                listaAutomatos.add(novoAutomato);
                       }
                   }
                }
            }
            if(!automatoAtual.getAvancou()){
                Automato remove = listaAutomatos.remove(elementoLista);
                System.out.println("Transição removida: ");
                remove.printAutomato();
            }
            
            if(!listaAutomatos.isEmpty()){
                elementoLista = (elementoLista+1) % listaAutomatos.size();
            }
        }
        
        if(!eAceito){
            System.out.println("Palavra não é aceita pelo autômato");
        }else{
            System.out.println("Palavra aceita pelo automato");
        }
        
        
    }
    
}
    
//impressão das variaveis obtidas do arquivo
//        System.out.print("Alfabeto de entrada: " + alfabetoEntrada);        
//        System.out.print("\nAlfabeto da Pilha: " + alfabetoPilha);        
//        System.out.println("\nSimbolo Epsilon: " + epsilon);
//        System.out.println("Simbolo inicial pilha: " + simboloInicialPilha);
//        System.out.print("Conjunto de estados: " + conjuntoEstados);        
//        System.out.println("\nEstado inicial: " + estInicial);        
//        System.out.println("Conjunto de estados de aceitação: " + conjuntoEstadosAceitacao + "\n\n");
//        int num = 0;
//        //for(Transicao t : transicoes){
//        for(List<String> t : transicoes2){
//            System.out.println("Transicao " + num + ": ");
//            
//            System.out.println("Estado Atual: " + t.get(0));
//            System.out.println("Simbolo atual da palavra: " + t.get(1));
//            System.out.println("Simbolo atual da pilha: " + t.get(2));
//            System.out.println("Novo Estado: " + t.get(3));
//            System.out.println("Simbolo a empilhar: " + t.get(4));
//            System.out.println("\n\n\n");
//            num++;
//        }      






