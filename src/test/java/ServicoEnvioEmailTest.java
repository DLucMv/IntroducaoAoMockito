import org.dlmv.Email;
import org.dlmv.Formato;
import org.dlmv.PlataformaDeEnvio;
import org.dlmv.ServicoEnvioEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServicoEnvioEmailTest {

    @Mock // Objeto a ser mockado e injetado na classe de test
    private PlataformaDeEnvio plataforma;

    @InjectMocks //Sempre referente a classe que queremos testar
    private ServicoEnvioEmail servico;

    @Captor //Captura um argumento de método
    private ArgumentCaptor<Email> emailCaptor;

    @Test
    public void validaSeEmailEstaComDadosCorretos() {

        String email = "usuario@gmail.com";
        String mensagem = "Mensagem de Teste";

        servico.enviaEmail(email, mensagem, true);
        Mockito.verify(plataforma).enviaEmail(emailCaptor.capture());

        Email emailCapturado = emailCaptor.getValue();
        Assertions.assertEquals(email, emailCapturado.getEnderecoEmail());
        Assertions.assertEquals(mensagem, emailCapturado.getMensagem());
        Assertions.assertEquals(Formato.HTML, emailCapturado.getFormato());
    }

}
