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
import model.Professor;
import model.exceptions.ProfessorException;
import view.FrProfessor;

/**
 *
 * @author jose
 */
public class ProfessorDAO extends DAO{

    private List<Professor> lstProfessores;
    
    public ProfessorDAO() {
        super("ListagemProfessores.csv");
        this.lstProfessores = new ArrayList<>();
    }        
    
    
    public boolean save(Professor prof){     
        this.lstProfessores.add(prof);
        
        //Criar o CSV
        String texto = this.lstProfessorToCSV();
        //Escrever no arquivo
        super.save(texto);
        return true;
    }
    
    /**
     * Procura um professor pelo CPF, que é o identificador único
     * @param prof
     * @return Referencia para o professor na lstProfessores
     */
    public Professor findByCpf(String cpf){
        
        for(Professor p: this.lstProfessores){
            if(p.getCpf().equals(cpf))
                return p;
        }
        
        return null;
    }
    
    public Object find(Object obj){        
        Professor prof = (Professor) obj;
                
        for(Professor p: this.lstProfessores){
            if(p.equals(prof))
                return p;
        }
        
        return null;
    }        
    
    /**
     * Recebe um professor como parametro, procura o professor pelo CPF
     * Se encontrar remove ele da lstProfessores.
     * @param prof
     * @return 
     */
    @Override
    public boolean delete(Object obj){
        /**
         * Este downCasting so funciona porque ao chamar o delte na assinatura
         * ja estamos passando (em runtime) um objeto professor.
         * Se nesta classe chamássemos passando um aluno a linha 80 nao funcionaria
         */
        Professor prof = (Professor) obj;
        
        for(int i=0; i<= this.lstProfessores.size()-1; i++){
            Professor p = this.lstProfessores.get(i);
            if(p.getCpf().equals(prof.getCpf())){
                this.lstProfessores.remove(i);
                
                //Cria CSV e escreve no arquivo
                String texto = this.lstProfessorToCSV();
                super.save(texto);
                return true;
            }
        }
        
        return false;
    }
        
    public void update(String cpfOriginal, Professor novoProfessor){        
        Professor professorOriginal = (Professor) this.findByCpf(cpfOriginal);
        if(professorOriginal == null)
            throw new ProfessorException("Error update - Professor inexistente.");
        
        //copiar os dados de prof para a referencia de professor na lista
        professorOriginal.copiar(novoProfessor);              
                
        
        //Criar o CSV e Escrever no arquivo
        String texto = this.lstProfessorToCSV();        
        super.save(texto);        
    }
    
    public List<Professor> findAll(){
        this.lstProfessores = loadArquivo();
        
        if(this.lstProfessores == null)
            return new ArrayList<Professor>();
        else
            return this.lstProfessores; 
    }
            
    private String lstProfessorToCSV() {
        String texto = "";
        Professor a = new Professor();
        texto = a.cabecalho();

        for (Professor p: this.lstProfessores) {           
            texto = texto + p.atributoToCSV();
        }

        return texto;
    }    
    
    private List<Professor> loadArquivo() {
        FileReader f = null;
        try {
            f = new FileReader(this.pathArquivo);//"ListagemProfessores.csv");
            Scanner arquivoLido = new Scanner(f);
            arquivoLido.useDelimiter("\n");
            
            List<Professor> lista = new ArrayList<>();
            String linhaLida = arquivoLido.next();
            while (arquivoLido.hasNext()) {
                linhaLida = arquivoLido.next();

                Professor p = new Professor();
                p.CSVToAtributo(linhaLida);
                lista.add(p);
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
