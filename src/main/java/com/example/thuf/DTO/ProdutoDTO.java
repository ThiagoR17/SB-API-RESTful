package com.example.thuf.DTO;

import java.math.BigDecimal;


import jakarta.validation.constraints.NotNull;



public record ProdutoDTO (@NotNull String name,  @NotNull BigDecimal productvalue){

}