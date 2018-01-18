package com.kodekul.kondrat.bfsword.master;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodekul.kondrat.bfsword.knife.Knife;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "master")
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="master_id")
    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("master")
    private List<Knife> knifes;

    @JsonIgnore
    private boolean archived = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Knife> getKnifes() {
        return knifes;
    }

    public void setKnifes(List<Knife> knifes) {
        this.knifes = knifes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString(){
        return "id: " + getId() + " name :" + name;
    }
}
