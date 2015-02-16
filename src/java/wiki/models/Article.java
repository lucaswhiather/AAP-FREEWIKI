package wiki.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author RENAN
 */
@Entity
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Basic(optional = false)
    @Column(length = 30)
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(length = 30)
    private String content;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "id_category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category category;
    @Basic(optional = false)
    @Column
    private boolean userApproved;
    @Basic(optional = false)
    @Column
    private boolean admApproved;
    @Basic(optional = false)
    @Column
    private int useless;
    @Basic(optional = false)
    @Column
    private int intusefull;

    public Article() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUseless() {
        return useless;
    }

    public void setUseless(int useless) {
        this.useless = useless;
    }

    public int getIntusefull() {
        return intusefull;
    }

    public void setIntusefull(int intusefull) {
        this.intusefull = intusefull;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isUserApproved() {
        return userApproved;
    }

    public void setUserApproved(boolean userApproved) {
        this.userApproved = userApproved;
    }

    public boolean isAdmApproved() {
        return admApproved;
    }

    public void setAdmApproved(boolean admApproved) {
        this.admApproved = admApproved;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + '}';
    }

}
