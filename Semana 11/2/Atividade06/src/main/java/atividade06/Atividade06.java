package atividade06;
import Model.Book;
import Model.Page;
import java.util.List;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mateu
 */
public class Atividade06 {

    public static void main(String[] args) {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager entityManeger = factory.createEntityManager();
        
        Book livro1 = new Book("Sonhos de Gregorio","Mateus Gregorio");
        
        Page pag1 = new Page(1, "texto pagina 1",livro1);
        Page pag2 = new Page(2, "texto pagina 2", livro1);
        Page pag3 = new Page(3, "texto pagina 3",livro1);
        
        List<Page> bookPages = Arrays.asList(pag1, pag2, pag3);
        livro1.setPages(bookPages);
        
        entityManeger.getTransaction().begin();
        
        entityManeger.persist(livro1);
        
        entityManeger.getTransaction().commit();
        
        entityManeger.close();
        factory.close();
    }
}
