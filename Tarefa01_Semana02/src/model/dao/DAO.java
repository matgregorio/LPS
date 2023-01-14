/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Professor;

/**
 *
 * @author jose
 */
public abstract class DAO {
    
    protected String pathArquivo;

    public DAO() {
        this.pathArquivo = "file.csv";
    }    

    public DAO(String pathArquivo) {
        this.pathArquivo = pathArquivo;
    } 
    
    /**
     *
     * @return
     */
    public abstract boolean delete(Object obj);
    
    public abstract Object find(Object obj);
        
    
    /**
     * Escreve uma lista de objetos no arquivo de texto
     * @param texto 
     */
    protected void save(String texto) {
        FileWriter arq = null;
        try {
            arq = new FileWriter(this.pathArquivo);
            PrintWriter gravaArq = new PrintWriter(arq);
            gravaArq.print(texto);
            arq.close();
        } catch (IOException ex) {
           //nao possso furar fila
           //o correto aqui seria usar throws
            System.out.println(ex.getMessage());            
        } finally {
            try {
                arq.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());                
            }
        }
    }
        
}
