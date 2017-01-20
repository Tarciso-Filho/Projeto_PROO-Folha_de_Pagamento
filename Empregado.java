
package folhadepagamento;

public class Empregado
{
    public boolean pertencenteAoSindicato;
    public int id, tipo, metodoDePagamento, idSindicato;
    public String nome, endereco;
    public double salario, comissao, taxaSindical;
    
    public Empregado( String novoNome, String novoEndereco, int novoTipo, 
            double novoSalario, double novaComissao, int novoId)
    {
        nome = novoNome;
        endereco = novoEndereco;
        tipo = novoTipo;
        salario = novoSalario;
        comissao = novaComissao;
        id = novoId;
        pertencenteAoSindicato = false;
        metodoDePagamento = 1;//Por padrão todos recebem um cheque pelo correio
        taxaSindical = 0.0;
    }
}
