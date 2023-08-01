import org.dlmv.Conta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContaTeste {

    @Spy //Para observar o comportamento de um objeto ou classe
    private Conta conta = new Conta(2000);

    @Test
    void verificaAOrdemDasChamadas() {

        InOrder inOrder = Mockito.inOrder(conta);
        conta.pagaBoleto(300);

        inOrder.verify(conta).pagaBoleto(300);
        inOrder.verify(conta).validaSaldo(300);
        inOrder.verify(conta).debita(300);
        inOrder.verify(conta).enviaCreditoParaEmissor(300);
    }

    @Test
    void validaQuantidadeDeChamdas() {

        conta.validaSaldo(100);
        conta.validaSaldo(200);
        conta.validaSaldo(300);

        Mockito.verify(conta, Mockito.times(3))
                .validaSaldo(ArgumentMatchers.any());
    }



}
