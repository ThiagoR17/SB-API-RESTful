package com.example.thuf.models;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_products")

public class Productmodel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID idProduto;
    private String name;

    @Column(name = "product_value") // O nome da coluna no banco de dados
    private BigDecimal productvalue;
    
  
   public Productmodel() {
        this.idProduto = UUID.randomUUID();
        
    }
    public Productmodel(String name, BigDecimal productvalue) {
        this.idProduto = UUID.randomUUID();
        this.name = name;
        this.productvalue = productvalue;
    }

    public UUID getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(UUID idProduto) {
        this.idProduto = idProduto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return productvalue;
    }

    public void setValue(BigDecimal productvalue) {
        this.productvalue = productvalue;
    }
}

