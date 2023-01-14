
package controller;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.Professor;
import model.dao.ProfessorDAO;
import model.exceptions.ProfessorException;



public class ProfessorController {

    private ProfessorDAO repositorio;
    //List<Professor> lista; //enquanto nao usamos bd ou arquivo

    public ProfessorController() {
        repositorio = new ProfessorDAO();
    }

    public boolean cadastrarProfessor(String nome, String sexo, String idade, String cpf) {
        Professor novoProfessor = validacaoVazio(nome, sexo, idade, cpf);

        if (repositorio.findByCpf(cpf) == null) {
            return repositorio.save(novoProfessor);
        } else {
            throw new ProfessorException("Error - Já existe um professor com este 'CPF'.");
        }
    }

    public void atualizarProfessor(String cpfOriginal, String nome, String sexo, String idade, String cpf) {
        Professor novoProfessor = validacaoVazio(nome, sexo, idade, cpf);
        repositorio.update(cpfOriginal, novoProfessor);
    }

    public Professor buscarProfessor(String cpf) {
        return this.repositorio.findByCpf(cpf);
    }

    public List<Professor> listarProfessores() {
        //buscar no banco de dados ou arquivo        
        return this.repositorio.findAll();
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
        String listagemTotal = "";

        for (Professor p : this.repositorio.findAll()) {
            listagemTotal = listagemTotal + p.toString();
        }
                
        return listagemTotal;
    }
    
    private Professor validacaoVazio(String nome, String sexo, String idade, String cpf){
        Professor p = new Professor();
        if (nome.isEmpty())
            throw new ProfessorException("Error - Campo vazio: 'nome'.");
        p.setNome(nome);
        
        if (sexo.isEmpty()) 
            throw new ProfessorException("Error - Campo vazio: 'sexo'.");                
        p.setSexo(sexo.charAt(0));        

        int idadeInt = 0;
        if (idade.isEmpty())
            throw new ProfessorException("Error - Campo vazio: 'idade'.");
        
        if(!idade.matches("[0-9]*"))
            throw new ProfessorException("Error - Valor inválido no campo 'idade'.");
        
        idadeInt = Integer.parseInt(idade);
        p.setIdade(idadeInt);        

        if(cpf.isEmpty())
            throw new ProfessorException("Error - Campo vazio: 'cpf'.");
        p.setCpf(cpf);
        
        return p;
    }

 public void atualizarTabela2(JTable grd) {
        Util.jTableShow(grd, new TMCadProfessor (repositorio.findAll()), null);
    }
    
}
