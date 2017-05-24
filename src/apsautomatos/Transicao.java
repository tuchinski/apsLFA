/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsautomatos;

/**
 *
 * @author tuchinski
 */
public class Transicao {
    private String estadoAtual; 
    private String simboloAtual;
    private String simboloTopoPilha;
    private String novoEstado;
    private String simbolosEmpilhar; 

    public Transicao(String estadoAtual, String simboloAtual, String simboloTopoPilha, String novoEstado, String simbolosEmpilhar) {
        this.estadoAtual = estadoAtual;
        this.simboloAtual = simboloAtual;
        this.simboloTopoPilha = simboloTopoPilha;
        this.novoEstado = novoEstado;
        this.simbolosEmpilhar = simbolosEmpilhar;
    }

    public String getEstadoAtual() {
        return estadoAtual;
    }

    public String getSimboloAtual() {
        return simboloAtual;
    }

    public String getSimboloTopoPilha() {
        return simboloTopoPilha;
    }

    public String getNovoEstado() {
        return novoEstado;
    }

    public String getSimbolosEmpilhar() {
        return simbolosEmpilhar;
    }
        
}


