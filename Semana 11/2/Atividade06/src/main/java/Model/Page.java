package Model;
import Model.Book;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mateu
 */
@Entity
@Getter
@Setter
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    private int numero;
    private String text;
    
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    public Page() {
        this.id = -1;
        this.numero = 0;
        this.text = "";
        this.book = new Book();
    }
    
    public Page(int numero, String text, Book book) {
        this.numero = numero;
        this.text = text;
        this.book = book;
    }
}
