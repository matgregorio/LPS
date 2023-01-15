/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetoescola_jpa.model;

/**
 *
 * @author Mateu
 */

public class Secretaria
{
    private int id;
    
    private String nomeFuncionario;
    private String documentos;
    
    
    public Secretaria() 
    {
        this.id = -1;
        this.nomeFuncionario = "";
        this.documentos = "";
    }
    
    public Secretaria(int id, String nomeFuncionario, String documentos) 
    {        
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.documentos = documentos;
    }
    
    public void copiar(Secretaria outro) 
    {
        this.id = outro.getId();
        this.nomeFuncionario = outro.getNomeFuncionario();
        this.documentos = outro.getDocumentos();
    }

    @Override
    public String toString() 
    {
        String txt = "---- Informacoes sobre a Secretaria ------\n"
                + "id: " + this.id + "\n"
                + " Nome do Funcionário: " + this.nomeFuncionario + "\n"
                + " Documentos Guardados : " + this.documentos + "\n"
                + "-------------------------------------\n";
        return txt;
    }
      
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }
    
    public String getNomeFuncionario() 
    {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) 
    {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getDocumentos() 
    {
        return documentos;
    }

    public void setDocumentos(String documentos) 
    {
        this.documentos = documentos;
    }
}

