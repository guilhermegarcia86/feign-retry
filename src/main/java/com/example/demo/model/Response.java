package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Response {

    @Id
    private String cep;
    private Date create_at;
    private Date update_at;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean success;

}
