/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifcoder.projetoescola_jpa.model.dao.file;

/**
 *
 * @author Mateu
 */
public interface Json {
    public String atributoToJSON();
    
    public Object JSONToAtributo(String jsonObj);
    
}
