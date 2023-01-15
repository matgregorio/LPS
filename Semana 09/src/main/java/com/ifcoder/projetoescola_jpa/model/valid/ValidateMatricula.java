/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifcoder.projetoescola_jpa.model.valid;

/**
 *
 * @author darloonlino
 */
public class ValidateMatricula {
    public boolean validaMAtricula(String Matricula){
        if(Matricula.length() != 7 ){
            return false;            
        }
        
        if(Matricula.length() == 7){
            Matricula = Matricula.replaceAll("-", "");
            
        }
        if(Matricula.length() ==6 && Matricula.matches("[0-9]*")){
            String []vet = Matricula.split("");
            String x = ""+vet[0]+vet[1]+vet[2]+vet[3];
            int anoEntrada = Integer.parseInt(x);
            int Classificao = Integer.parseInt(""+vet[4]+vet[5]);
            
            System.out.printf("Ano de Entrada:"+anoEntrada+"\nClassificao:"+Classificao);
            if((anoEntrada < 1980)||(2080<anoEntrada)){
                return false;
            }
            if((Classificao < 0)||(99<Classificao)){
                return false;
            }
            return true;
        }
        return false;
    }
}
