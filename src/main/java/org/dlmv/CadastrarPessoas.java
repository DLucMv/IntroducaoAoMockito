package org.dlmv;

import java.time.LocalDate;

public class CadastrarPessoas {

    private ApiCorreios apiCorreios;

    public void CadastrarPessoa(final ApiCorreios apiCorreios) {
        this.apiCorreios = apiCorreios;
    }

    public Pessoa cadastrarPessoa(String nome, String documento, LocalDate nascimento, String cep) {
        Pessoa pessoa = new Pessoa(nome, documento, nascimento);
        DadosLocalizacao dadosLocalizacao = apiCorreios.buscaDadosComBaseNoCep(cep);
        pessoa.adicionaDadosDeEndereco(dadosLocalizacao);
        return pessoa;
    }

}
