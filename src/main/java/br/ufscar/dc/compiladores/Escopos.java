package br.ufscar.dc.compiladores;

import java.util.LinkedList;
import java.util.List;

public class Escopos {
    private LinkedList<SymbolTable> pilhaDeTabelas;

    public Escopos() {
        pilhaDeTabelas = new LinkedList<>();
        criarNovoEscopo();
    }

    public void criarNovoEscopo() {
        pilhaDeTabelas.push(new SymbolTable());
    }

    public SymbolTable obterEscopoAtual() {
        return pilhaDeTabelas.peek();
    }

    public List<SymbolTable> percorrerEscoposAninhados() {
        return pilhaDeTabelas;
    }

    public void abandonarEscopo() {
        pilhaDeTabelas.pop();
    }
}