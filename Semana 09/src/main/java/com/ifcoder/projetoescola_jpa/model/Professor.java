package com.ifcoder.projetoescola_jpa.model;

import com.ifcoder.projetoescola_jpa.model.dao.file.Json;
import java.util.ArrayList;

public class Professor extends Pessoa implements Json {

    private String cpf;

    public Professor() {
        super();
        this.cpf = "000.000.000-00";
    }

    public Professor(String nome, char sexo, int idade, String cpf) {
        super(nome, sexo, idade);
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String txt
                = "---------- Professor -----------\n";
        txt += super.toString();
        txt += "CPF: " + this.cpf + "\n"
                + "-------------------------------------\n";
        return txt;
    }

    @Override
    public boolean equals(Object obj) {
        Professor outro = (Professor) obj;
        if (!super.equals(obj)) {
            return false;
        } else if (this.cpf.equals(outro.getCpf())) {
            return false;
        }

        return true;
    }

    public void copiar(Professor outro) {
        this.nome = outro.getNome();
        this.sexo = outro.getSexo();
        this.idade = outro.getIdade();
        this.cpf = outro.getCpf();
    }

    public String cabecalho() {
        return "Nome;sexo;idade;cpf\n";
    }

    @Override
    public String atributoToJSON() {
        String aux = "\n{\n" + "nome: " + this.nome + ",\n"
                + "sexo: " +  this.sexo + ",\n"
                + "idade: " + this.idade + ",\n"
                + "cpf: " + this.cpf + "\n"
                + "}";
        return aux;
    }

    @Override
    public Object JSONToAtributo(String objJson) {
        String replaceJson = objJson.replace('{', ' ').replace('}', ' ').replace('\n', ' ').replaceAll("\"", "");
            System.err.println(replaceJson);
        String linhaAttr[] = replaceJson.split(",");
        
        ArrayList<String> valorAttr = new ArrayList();
        
            System.err.println(linhaAttr.length);
        for (String line : linhaAttr) {
            System.err.println(line);
            System.err.println(line.split(":")[1]);
            valorAttr.add(line.split(":")[1]);
            
        }

            System.err.println(valorAttr.size());
         Professor p = new Professor(valorAttr.get(0).replaceFirst(" ", ""),
                valorAttr.get(1).replaceAll(" ", "").charAt(0),
                Integer.parseInt(valorAttr.get(2).replaceAll(" ", "")),
                valorAttr.get(3).replaceAll(" ", ""));
  
        return p;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
