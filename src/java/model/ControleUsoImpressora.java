package model;
//Classe de Entidade de Relacionamento

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cadusoimpressora")
public class ControleUsoImpressora implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id")
    private Integer id;
    @Column(name="fk_usuario")
    private String fk_usuario;
    @Column(name="fk_impressora")
    private String fk_impressora;
    @Column(name="data")
    private String data;
    @Column(name="qtde_paginas")
    private String qtde_paginas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(String fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public String getFk_impressora() {
        return fk_impressora;
    }

    public void setFk_impressora(String fk_impressora) {
        this.fk_impressora = fk_impressora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getQtde_paginas() {
        return qtde_paginas;
    }

    public void setQtde_paginas(String qtde_paginas) {
        this.qtde_paginas = qtde_paginas;
    }
}