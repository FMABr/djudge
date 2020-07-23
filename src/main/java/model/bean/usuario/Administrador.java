package model.bean.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.bean.ModelBean;

@Entity
public class Administrador extends Usuario implements ModelBean<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
   
    public Administrador() {}

    public Administrador(Long id, String email, String nome, String senha) {
        super(email, nome, senha);
        this.id = id;
    }
    
    public Long getPK() {
        return id;
    }

    public void setPK(Long id) {
        this.id = id;
    }
}
