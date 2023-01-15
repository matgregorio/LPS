/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;
import model.exceptions.AlunoException;
import view.FrProfessor;

/**
 *
 * @author jose
 */
public class AlunoDAO extends DAO{

    private List<Aluno> lst;
    
    public AlunoDAO() {
        super("ListagemAlunos.csv");
        this.lst = new ArrayList<>();
    }        
    
    
    public boolean save(Aluno prof){     
        this.lst.add(prof);
        
        //Criar o CSV
        String texto = this.lstAlunoToCSV();
        //Escrever no arquivo
        super.save(texto);
        return true;
    }
    
    /**
     * Procura um professor pelo CPF, que é o identificador único
     * @param matricula do aluno
     * @return Referencia para o aluno na lstAluno
     */
    public Aluno findByMatricula(String matricula){
        
        for(Aluno a: this.lst){
            if(a.getMatricula().equals(matricula))
                return a;
        }
        
        return null;
    }
    
    @Override
    public Object find(Object obj){        
        Aluno aluno = (Aluno) obj;
                
        for(Aluno a: this.lst){
            if(a.equals(aluno))
                return a;
        }
        
        return null;
    }        
    
    /**
     * Recebe um Aluno como parametro, procura o Aluno pela Matricula
     * Se encontrar remove ele da lstAlunos.
     * @param obj
     * @param Aluno
     * @return 
     */
    @Override
    public boolean delete(Object obj){
        /**
         * Este downCasting so funciona porque ao chamar o delte na assinatura
         * ja estamos passando (em runtime) um objeto professor.
         * Se nesta classe chamássemos passando um aluno a linha 80 nao funcionaria
         */
        Aluno alunoProcurado = (Aluno) obj;
        
        for(int i=0; i<= this.lst.size()-1; i++){
            Aluno p = this.lst.get(i);
            if(p.getMatricula().equals(alunoProcurado.getMatricula())){
                this.lst.remove(i);
                
                //Cria CSV e escreve no arquivo
                String texto = this.lstAlunoToCSV();
                super.save(texto);
                return true;
            }
        }
        
        return false;
    }
        
    public void update(String matriculaOriginal, Aluno novoAluno){        
        Aluno alunoOriginal = (Aluno) this.findByMatricula(matriculaOriginal);
        if(alunoOriginal == null)
            throw new AlunoException("Error update - Aluno inexistente.");
        
        //copiar os dados de prof para a referencia de professor na lista
        alunoOriginal.copiar(novoAluno);              
                
        
        //Criar o CSV e Escrever no arquivo
        String texto = this.lstAlunoToCSV();        
        super.save(texto);        
    }
    
    public List<Aluno> findAll(){
        this.lst = loadArquivo();
        
        if(this.lst == null)
            return new ArrayList<Aluno>();
        else
            return this.lst; 
    }
            
    private String lstAlunoToCSV() {
        String texto = "";
        Aluno a = new Aluno();
        texto = a.cabecalho();

        for (Aluno aluno: this.lst) {           
            texto = texto + aluno.atributoToCSV();
        }

        return texto;
    }    
    
    /**
     * Este metodo é um bom exemplo para usar heranca e metodos abstratos
     * Dá para subir com este metodo para SUPER.
     * O metodo é igual para ALUNO e PROFESSOR e tem a mesma chamada de CSVToAtributo. Logo este pode
     * subir e temos que garantir que ALUNO e PROFESSOR implementem CSVToAtributo
     * @return 
     */
    private List<Aluno> loadArquivo() {
        FileReader f = null;
        try {
            f = new FileReader(this.pathArquivo);//"ListagemProfessores.csv");
            Scanner arquivoLido = new Scanner(f);
            arquivoLido.useDelimiter("\n");
            
            List<Aluno> lista = new ArrayList<>();
            String linhaLida = arquivoLido.next();
            while (arquivoLido.hasNext()) {
                linhaLida = arquivoLido.next();

                Aluno aluno = new Aluno();
                aluno.CSVToAtributo(linhaLida);
                lista.add(aluno);
            }
            return lista;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrProfessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(FrProfessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
