import org.dlmv.ApiCorreios;
import org.dlmv.CadastrarPessoas;
import org.dlmv.DadosLocalizacao;
import org.dlmv.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals; // Para fazer chamada direta do assert
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoasTeste {

    @Mock
    private ApiCorreios apiCorreios;

    @InjectMocks
    private CadastrarPessoas cadastrarPessoas;

    @Test
    void cadastrarPessoa() {

        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("CE", "Fortaleza", "Rua José de Alencar", "Casa", "Centro");

        Mockito.when(apiCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(dadosLocalizacao);

        Pessoa joao = cadastrarPessoas.cadastrarPessoa("João", "035.489.666-87", LocalDate.of(1933, 1, 21), "60790-820");

        DadosLocalizacao enderecoJoao = joao.getEndereco();
        assertEquals(dadosLocalizacao.getBairro(), enderecoJoao.getBairro());
        assertEquals(dadosLocalizacao.getCidade(), enderecoJoao.getCidade());
        assertEquals(dadosLocalizacao.getUf(), enderecoJoao.getUf());
    }

    @Test
    void tentaCadastrarPessoaMasSistemaDosCorreiosFalha() {

        Mockito.when(
                apiCorreios.buscaDadosComBaseNoCep(anyString()))
                .thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class, () -> cadastrarPessoas.cadastrarPessoa(
                        "João", "035.489.666-87", LocalDate.of(
                                1933, 1, 21), "60790-820"));
    }

}
