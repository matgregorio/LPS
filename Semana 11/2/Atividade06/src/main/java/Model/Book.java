package Model;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Mateu
 */
@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   
    private String title;
    private String author;
       
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Page> pages;

    public Book() {
        id = -1;
        title = "";
        author = "";
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    public Book(String title, String author,List<Page> pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

}
