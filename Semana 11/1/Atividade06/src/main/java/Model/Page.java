package Model;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mateu
 */
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   
    private int number;
    private String text;
    
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    public Page() {
        this.id = -1;
        this.number = 0;
        this.text = "";
        this.book = new Book();
    }
    public Page(int number,String text,Book book){
        this.number = number;
        this.text = text;
        this.book = book;
    }
}
