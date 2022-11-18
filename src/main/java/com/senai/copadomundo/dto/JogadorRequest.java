package com.senai.copadomundo.dto;

import com.senai.copadomundo.models.Posicao;
import com.senai.copadomundo.models.Selecao;
import lombok.Data;


@Data
public class JogadorRequest {

    private String nome;

    private Posicao posicao;


}
