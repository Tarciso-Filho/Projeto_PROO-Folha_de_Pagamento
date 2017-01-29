package folhadepagamento;

import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;

public class Empregado
{
    public boolean pertencenteAoSindicato;
    public int id, tipo, metodoDePagamento, idSindicato;
    public Calendar proximoPagamento;
    public String nome, endereco;
    public double salarioBruto, comissao, taxaSindical, taxaSindicalExtra;
    public double salarioLiquido;
    
    public Empregado( String novoNome, String novoEndereco, int novoTipo, 
            double novoSalario, double novaComissao, int novoDia, int novoMes,
            int novoAno, int novoId)
    {
        nome = novoNome;
        endereco = novoEndereco;
        tipo = novoTipo;
        salarioBruto = novoSalario;
        comissao = novaComissao;
        id = novoId;
        proximoPagamento = Calendar.getInstance();
        proximoPagamento.set(novoDia, novoMes, novoAno);
        /*switch(novoId)
        {
            case 1:
                diasAtePagamento = 6;
            break;
            case 2:
                diasAtePagamento = 29;
            break;
            case 3:
                diasAtePagamento = 13;
        }*/
        pertencenteAoSindicato = false;
        metodoDePagamento = 1;//Por padrão todos recebem um cheque pelo correio
        taxaSindical = 0.0;
        taxaSindicalExtra = 0.0;
        salarioLiquido = 0.0;
    }
}
