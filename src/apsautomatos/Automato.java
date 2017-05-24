/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsautomatos;

import java.util.Stack;

/**
 *
 * @author tuchinski
 */
public class Automato {
     
    private String palavraNaoProcessada;
    public Stack<Character> pilha = new Stack<>();
    private String estAtual;
    boolean avancou;

    /**
     * 
     * @param palavraNaoProcessada A palavra que ainda não foi processada pelo autômato
     * @param estAtual O estado que o atuômato se encontra
     * @param p A pilha atual do autômato topo na direita e base na esquerda
     */
    
    public Automato(String palavraNaoProcessada, String estAtual, Stack<Character> p) {
        this.avancou = false;
        this.palavraNaoProcessada = palavraNaoProcessada;
        this.estAtual = estAtual;
        this.pilha = p;
    }

    public String getPalavraNaoProcessada() {
        return palavraNaoProcessada;
    }

    public void setPalavraNaoProcessada(String palavraNaoProcessada) {
        this.palavraNaoProcessada = palavraNaoProcessada;
    }

    public Stack<Character> getPilha() {
        return pilha;
    }

    public void setPilha(Stack<Character> pilha) {
        this.pilha = pilha;
    }

    public String getEstAtual() {
        return estAtual;
    }

    public void setEstAtual(String estAtual) {
        this.estAtual = estAtual;
    }
    /**
     * Imprime um autômato
     */
    public void printAutomato(){
        System.out.println("Estado atual: " + this.estAtual);
        System.out.println("Palavra não processada: " + this.palavraNaoProcessada);
        System.out.println("Pilha: " + this.pilha);
    }

    /**
     *
     * @return Retorna 1 se o automato avançou nas transiçoes e 0 se o automato não consegue realizar mais nenhuma transição
     */
    public boolean getAvancou() {
        return avancou;
    }

    /**
     *
     * @param avancou true ou false
     */
    public void setAvancou(boolean avancou) {
        this.avancou = avancou;
    }
    
    
}
