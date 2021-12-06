import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Model {

  @Id @GeneratedValue
  private Long id;
  private String random;
  private String updated;

  public Model() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRandom() {
    return random;
  }

  public void setRandom(String random) {
    this.random = random;
  }

  public String getUpdated() {
    return updated;
  }

  public void setUpdated(String updated) {
    this.updated = updated;
  }
}
