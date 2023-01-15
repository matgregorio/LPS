
package controller;

import javax.swing.JTable;
import model.Aluno;
import model.dao.AlunoDAO;
import model.exceptions.AlunoException;
import model.valid.ValidateAluno;

/**
 *
 * @author jose
 */
public class AlunoController {

    private AlunoDAO repositorio;

    public AlunoController() {
        repositorio = new AlunoDAO();
    }

    public boolean cadastrarAluno(String nome, String sexo, String idade, String matricula, String anoIngresso) {
        ValidateAluno valid = new ValidateAluno();
        Aluno novoAluno = valid.validaCamposEntrada(nome, sexo, idade, matricula, anoIngresso);

        if (repositorio.findByMatricula(matricula) == null) {
            return repositorio.save(novoAluno);
        } else {
            throw new AlunoException("Error - Já existe um aluno com esta 'Matricula'.");
        }
    }

    public void atualizarAluno(String cpfOriginal, String nome, String sexo, String idade, String matricula, String anoIngresso) {
        ValidateAluno valid = new ValidateAluno();
        Aluno novoAluno = valid.validaCamposEntrada(nome, sexo, idade, matricula, anoIngresso);
        repositorio.update(cpfOriginal, novoAluno);
    }

    public Aluno buscarAluno(String matricula) {
        return this.repositorio.findByMatricula(matricula);
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMCadAluno(repositorio.findAll()), null);
    }

    public void excluirAluno(String matricula) {
        //Encontrar professor e exlcui                   
        Aluno aluno = repositorio.findByMatricula(matricula);
        if (aluno != null) {
            repositorio.delete(aluno);
        } else {
            throw new AlunoException("Error - Aluno inexistente.");
        }
    }    

}
