package atividade06;
import Model.Book;
import Model.Page;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mateu
 */
public class Ativ06 {

    public static void main(String[] args) {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager entityManeger = factory.createEntityManager();
        
        Book livro1 = new Book("Padrão de projeto", "Erich Gamma");        
        Page pag1 = new Page(1, "Essa é a primeira pagina", livro1);
        Page pag2 = new Page(2, "Essa é a segunda pagina", livro1);
        
        entityManeger.getTransaction().begin();
       
        entityManeger.persist(livro1);
        entityManeger.persist(pag1);
        entityManeger.persist(pag2);
        
        entityManeger.getTransaction().commit();
        
        entityManeger.close();
        factory.close();
    }
}
