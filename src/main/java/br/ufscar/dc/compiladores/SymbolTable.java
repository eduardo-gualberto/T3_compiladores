package br.ufscar.dc.compiladores;

import java.util.HashMap;

import br.ufscar.dc.compiladores.SymbolTableInput.LATypes;

public class SymbolTable {
    private HashMap<String, SymbolTableInput> map; 

    public SymbolTable() {
        this.map = new HashMap<>();
    }

    public void insert(String name, LATypes type) {
        SymbolTableInput sti = new SymbolTableInput();
        sti.setName(name);
        sti.setType(type);
        this.map.put(name, sti);
    }

    public boolean contains(String name) {
        return this.map.containsKey(name);
    }

    public SymbolTableInput peek(String name) {
        return this.map.get(name);
    }
}
