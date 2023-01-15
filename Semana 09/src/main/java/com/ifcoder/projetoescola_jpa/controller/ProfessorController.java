/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetoescola_jpa.controller;

import com.ifcoder.projetoescola_jpa.model.Professor;
import com.ifcoder.projetoescola_jpa.model.valid.ValidateProfessor;
import com.ifcoder.projetoescola_jpa.model.dao.file.ProfessorDAO;
import java.util.List;
import javax.swing.JTable;
import model.exceptions.ProfessorException;

/**
 *
 * @author jose
 */
public class ProfessorController {

    private final ProfessorDAO repositorio;
    //List<Professor> lista; //enquanto nao usamos bd ou arquivo

    public ProfessorController() {
        repositorio = new ProfessorDAO();
    }

    public void cadastrarProfessor(String nome, String sexo, String idade, String cpf) {
        ValidateProfessor valid = new ValidateProfessor();
        Professor novoProfessor = valid.validacao(nome, sexo, idade, cpf);
        
        if (repositorio.findByCpf(cpf) == null) {
            repositorio.save(novoProfessor);
        } else {
            throw new ProfessorException("Error - Já existe um professor com este 'CPF'.");
        }
    }

    public void atualizarProfessor(String cpfOriginal, String nome, String sexo, String idade, String cpf) {
        ValidateProfessor valid = new ValidateProfessor();
        Professor novoProfessor = valid.validacao(nome, sexo, idade, cpf);
        novoProfessor.setCpf(cpf);
        
        repositorio.update(cpfOriginal, novoProfessor);
    }

    public Professor buscarProfessor(String cpf) {
        return this.repositorio.findByCpf(cpf);
    }
    
    public List<Object> lst() {
        //buscar no banco de dados ou arquivo        
        return this.repositorio.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        List<Object> lst = repositorio.findAll();
        
        TMCadProfessor tmProfessor = new TMCadProfessor(lst);
        grd.setModel(tmProfessor);        
    }

    public void excluirProfessor(String cpf) {
        //Encontrar professor e exlcui                   
        Professor prof = repositorio.findByCpf(cpf);
        if(prof != null){
            repositorio.delete(prof);                    
        } else {
            throw new ProfessorException("Error - Professor inexistente.");
        }        
    }

    public String imprimirListaProfessores() {
        String listagemProfessores = "";

        for (Object obj : this.repositorio.findAll()) {
            Professor prof = (Professor) obj;
            listagemProfessores = listagemProfessores + prof.toString();
        }
                
        return listagemProfessores;
    }
    
    

}
