package com.ifcoder.projetoescola_jpa.model.dao;
import com.ifcoder.projetoescola_jpa.factory.Persistencia;
import com.ifcoder.projetoescola_jpa.model.Secretaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecretariaDAO implements IDao
{
    
    protected Connection connection; 
    private PreparedStatement statement;
    private final String sql;
    
    public SecretariaDAO() 
    {
        this.sql = "";
    } 
    
    @Override
    public void save(Object obj) 
    {
        Secretaria secretaria = (Secretaria) obj;
        
        String sql = " INSERT INTO "
                + " secretaria(nomeFuncionario, documentos) "
                + " VALUES(?,?) ";
        try 
        {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            
            //preencher cada ? com o campo adequado
            statement.setString(1, secretaria.getNomeFuncionario()+"");        
            statement.setString(2, secretaria.getDocumentos());
            
            statement.execute();
            statement.close();
            
        }catch (SQLException u){
            throw new RuntimeException(u);
        
        } finally{
            Persistencia.closeConnection();            
        }
    }

    public void update(Object obj) 
    {
        Secretaria secretaria = (Secretaria) obj;

        String sql = " UPDATE secretaria "
                + " SET nomeFuncionario=?, documentos=?, "
                + " WHERE id = ?";
        try 
        {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            
            //preencher cada ? com o campo adequado
            statement.setString(1, secretaria.getNomeFuncionario()+"");           
            statement.setString(2, secretaria.getDocumentos());
            
            //Informando quem e o ID que quero atualizar
            statement.setInt(3, secretaria.getId());
            
            statement.execute();
            statement.close();
        } 
        catch(SQLException u){
            throw new RuntimeException(u);
        }finally{
            Persistencia.closeConnection();           
        }    
    }   
    
    @Override
    public boolean delete(Object obj) 
    {
       Secretaria secretaria = (Secretaria) obj;
        
        String sql = " DELETE FROM secretaria WHERE id = ? ";
        try 
        {
            connection = Persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            //preenche a condição
            statement.setLong(1, secretaria.getId());
            
            statement.execute();
            statement.close();
            return true;
        } catch 
                (SQLException ex) 
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            Persistencia.closeConnection();
        }
    }
    
    
    @Override
    public Object find(Object obj) 
    {
        Secretaria secretaria = (Secretaria) obj;
        
        String sql = " SELECT * FROM secretaria WHERE id = ? ";
        try 
        {            
            statement = Persistencia.getConnection().prepareStatement(sql);
            statement.setInt(1, secretaria.getId());
            
            ResultSet resultset = statement.executeQuery();
            
            Secretaria s = null;
            while (resultset.next()) 
            {
                s = new Secretaria(
                        resultset.getInt(1),
                        resultset.getString(2),
                        resultset.getString(3));              
            }
            
            statement.close();
            return s;
        } 
        catch
            (SQLException u) 
        {
            throw new RuntimeException(u);
        }
        finally
        {
            Persistencia.closeConnection();
            //connection.close();
        }
    }    
    @Override
    public List<Object> findAll() 
    {
        List<Object> list = new ArrayList<>();

        String sql = " SELECT * FROM secretaria ORDER BY upper(id) ";
        try 
        {            
            statement = Persistencia.getConnection().prepareStatement(sql);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) 
            {
                Secretaria secretaria = new Secretaria(
                        resultset.getInt(1),
                        resultset.getString(2),                     
                        resultset.getString(3));

                list.add(secretaria);
            }
            statement.close();
        } 
        catch 
            (SQLException ex) 
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            Persistencia.closeConnection();            
        }  
        return list;
    }    
}

    
