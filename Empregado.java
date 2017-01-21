
package folhadepagamento;

public class Empregado
{
    public boolean pertencenteAoSindicato;
    public int id, tipo, metodoDePagamento, idSindicato;
    public String nome, endereco;
    public double salarioBruto, comissao, taxaSindical, taxaSindicalExtra;
    public double salarioLiquido;
    
    public Empregado( String novoNome, String novoEndereco, int novoTipo, 
            double novoSalario, double novaComissao, int novoId)
    {
        nome = novoNome;
        endereco = novoEndereco;
        tipo = novoTipo;
        salarioBruto = novoSalario;
        comissao = novaComissao;
        id = novoId;
        pertencenteAoSindicato = false;
        metodoDePagamento = 1;//Por padrão todos recebem um cheque pelo correio
        taxaSindical = 0.0;
        taxaSindicalExtra = 0.0;
        salarioLiquido = 0.0;
    }
}
