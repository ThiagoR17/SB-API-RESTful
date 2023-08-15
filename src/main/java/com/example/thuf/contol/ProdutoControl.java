package com.example.thuf.contol;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.thuf.DTO.ProdutoDTO;
import com.example.thuf.models.Productmodel;
import com.example.thuf.repositories.Repository;

import jakarta.validation.Valid;

@RestController
public class ProdutoControl {

    @Autowired
    Repository repository;

    @PostMapping("/produtos")
    public ResponseEntity<Productmodel> saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO){
        var produtomodel = new Productmodel(); 
        produtomodel.setIdProduto(UUID.randomUUID());
        produtomodel.setValue(produtoDTO.productvalue());
        BeanUtils.copyProperties(produtoDTO, produtomodel);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtomodel));
    }

    @GetMapping("produtos")
    public ResponseEntity<List<Productmodel>> getAllProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> getOneProduto(@PathVariable(value = "id") UUID id){
        Optional<Productmodel> produto = repository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProdutoDTO produtoDTO) {
        Optional<Productmodel> optionalProduto = repository.findById(id);
        
        if (optionalProduto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        
        Productmodel produtoExistente = optionalProduto.get();
        
       
        BeanUtils.copyProperties(produtoDTO, produtoExistente, "id");
        
        
        Productmodel produtoAtualizado = repository.save(produtoExistente);
        
        return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizado);
    }


    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<Productmodel> optionalProduto = repository.findById(id);
        if(optionalProduto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        
        }
        repository.delete(optionalProduto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }
    

}
