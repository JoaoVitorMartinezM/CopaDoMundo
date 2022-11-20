package com.senai.copadomundo.dto;

import com.senai.copadomundo.models.Posicao;
import com.senai.copadomundo.models.Selecao;
import lombok.Data;

@Data
public class JogadorResponse {

    private Integer id;

    private String nome;

    private Posicao posicao;



}
