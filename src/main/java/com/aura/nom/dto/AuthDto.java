package com.aura.nom.dto;

import lombok.Data;

public @Data class AuthDto {
    private int idColaborador;
    private int idCliente;
    private int idPerson;
    private String nombre;
    private String password;
    private String email;


}
