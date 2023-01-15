
package controller;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Professor;

public class TMCadProfessor extends AbstractTableModel {

    private final List<Professor> lista;
    
    private final int COL_NOME = 0;   
    private final int COL_SEXO = 1;    
    private final int COL_IDADE = 2;
    private final int COL_CPF = 3;       

    public TMCadProfessor(List<Professor> lstProfessor) {        
        lista = lstProfessor;        
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
    public String getColumnName(int column) {
        
        switch (column) {
            
            case COL_NOME:
                return "Nome";
            case COL_SEXO:
                return "Sexo";
            case COL_IDADE:
                return "Idade"; 
            case COL_CPF:
                return "CPF";
            
            default:
                break;
        }

        return "";
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Professor aux = new Professor();
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
                case COL_CPF:
                    return aux.getCpf();
               
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
    public Class getColumnClass(int columnIndex) {
//        if(columnIndex == COL_MATRICULA)
//            return Boolean.class;
        
        return String.class;
    }

}
