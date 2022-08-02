package br.ufscar.dc.compiladores;

public class SymbolTableInput {
    public enum LATypes {
        INT, REAL, PTR, STR, ERROR, OP_UNARIO, IDENT, OP_RELACIONAL, BOOL
    }

    private String name;
    private LATypes type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LATypes getType() {
        return type;
    }

    public void setType(LATypes type) {
        this.type = type;
    }
}
