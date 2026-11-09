package backend.arkistointi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Arkisto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String huone;
    private String hylly;
    private String rivi;
    @NotNull
    @Min(1)
    private Integer mappienMaara;

    @ManyToOne
    @JoinColumn(name = "yritys_id")
    private Yritys yritys;

    public Arkisto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHuone() {
        return huone;
    }
    
    public void setHuone(String huone) {
        this.huone = huone;
    }

    public String getHylly() {
        return hylly;
    }
    
    public void setHylly(String hylly) {
        this.hylly = hylly;
    }

    public String getRivi() {
        return rivi;
    }
    
    public void setRivi(String rivi) {
        this.rivi = rivi;
    }

    public Integer getMappienMaara() {
        return mappienMaara;
    }
    
    public void setMappienMaara(Integer mappienMaara) {
        this.mappienMaara = mappienMaara;
    }

    public Yritys getYritys() {
        return yritys;
    }

    public void setYritys(Yritys yritys) {
        this.yritys = yritys;
    }
}