
package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Aluno;

public class TMCadAluno extends AbstractTableModel {

    private final List<Aluno> lista;
    
    private final int COL_NOME = 0;   
    private final int COL_SEXO = 1;    
    private final int COL_IDADE = 2;
    private final int COL_MATRICULA = 3;       

    public TMCadAluno(List<Aluno> lstAlunos) {        
        lista = lstAlunos;        
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Aluno aux = new Aluno();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_NOME:
                    return aux.getNome();
                case COL_SEXO:
                    return aux.getSexo();
                 case COL_IDADE:
                    return aux.getIdade();
                case COL_MATRICULA:
                    return aux.getMatricula();
               
                default: 
                    break;
            }
        }
        return aux;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        
        switch (column) {
            case COL_NOME:
                return "Name";
            case COL_SEXO:
                return "Sexo";
            case COL_IDADE:
                return "Idade"; 
            case COL_MATRICULA:
                return "Matricula";
            
            default:
                break;
        }

        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
//        if(columnIndex == COL_MATRICULA)
//            return Boolean.class;
        
        return String.class;
    }

}
