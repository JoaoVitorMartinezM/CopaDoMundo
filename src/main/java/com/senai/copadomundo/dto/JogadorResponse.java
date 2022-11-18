package com.senai.copadomundo.dto;

import com.senai.copadomundo.models.Posicao;
import com.senai.copadomundo.models.Selecao;
import lombok.Data;

@Data
public class JogadorResponse {

    private String nome;

    private Posicao posicao;

    private Selecao selecao;

}
