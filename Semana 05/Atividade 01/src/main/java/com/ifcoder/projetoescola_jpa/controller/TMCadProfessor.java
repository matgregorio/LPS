/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifcoder.projetoescola_jpa.controller;

import com.ifcoder.projetoescola_jpa.model.Professor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author darlonlino
 */
public class TMCadProfessor extends AbstractTableModel
{
    private List<Object> lista;
    
    private final int COL_NOME = 0;   
    private final int COL_SEXO = 1;    
    private final int COL_IDADE = 2;
    private final int COL_CPF = 3;  

    public TMCadProfessor(List<Object> lstProfessor) 
    {        
        this.lista = lstProfessor;        
    }

    
    @Override
    public int getRowCount()
    {
        return lista.size();
    }
    
    @Override
    public int getColumnCount()
    {
        return 4;
    }
    
    @Override
    public String getColumnName(int column) 
    {
        switch (column) 
        {
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
    public Object getValueAt(int rowIndex, int columnIndex) 
    {               
        Professor aux = new Professor();
        if (lista.isEmpty()) 
        {
            return aux;
        } 
        else 
        {
            aux = (Professor) lista.get(rowIndex);
            //verifica qual valor deve ser retornado
            switch (columnIndex) 
            {
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
    public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
        return false;
    }

    @Override
    public Class getColumnClass(int columnIndex) 
    {
//        if(columnIndex == COL_CPF)
//            return Boolean.class;
        
        return String.class;
    }
}