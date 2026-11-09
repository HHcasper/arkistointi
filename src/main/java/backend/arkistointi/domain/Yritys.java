package backend.arkistointi.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Yritys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "yritys")
    private List<Arkisto> arkistot;

    public Yritys() {
    }

    public Yritys(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Arkisto> getArkistot() {
        return arkistot;
    }
    
    public void setArkistot(List<Arkisto> arkistot) {
        this.arkistot = arkistot;
    }
}