package com.iza.core.infra.business;

import org.springframework.http.HttpStatus;

public enum BusinessMessage {
    E100("100", "O campo %s é de preenchimento obrigatório","Preencha o campo de acordo com as instruções"),
    E101("101", "Não existe um(a) %s com o ID: %s informado","O registro deve existir previamente"),

    E404("404", "Não existe um(a) %s com o(a) %s informado(a)","O registro deve existir previamente"){
        @Override
        public int getHttpStatus() {
            return HttpStatus.NOT_FOUND.value();
        }
    },
    E204("204", "Consulta sem registros","Realize uma operação de cadastro"){
        @Override
        public int getHttpStatus() {
            return HttpStatus.NOT_FOUND.value();
        }
    },


    E199("199", "Erro na tentativa de concluir a transação de persistência %s","Reporte ao suporte técnico"),
    E900("900", "Token inválido ou expirado","Realize uma nova autenticação"),
    E501("501", "Erro ao tentar acessar o recurso","Contacte o Suporte Técnico"){

        @Override
        public int getHttpStatus() {
            return 500;
        }
    },
    E502("502", "Método não implementado","Contacte o Suporte Técnico"){
        @Override
        public int getHttpStatus() {
            return 500;
        }
    },
    E500("500", "Erro não mapeado","Contacte o Suporte Técnico"){

        @Override
        public int getHttpStatus() {
            return 500;
        }
    },
    ;
    private final String code;
    private final String message;
    private final String suggestion;

    private int httpStatus;

    private BusinessMessage(String code, String mensagem, String suggestion) {
        this.code = code;
        this.message = mensagem;
        this.suggestion = suggestion;
    }

    public int getHttpStatus() {
        return HttpStatus.CONFLICT.value();
    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
