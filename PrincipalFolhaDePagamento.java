package folhadepagamento;
import java.util.Scanner;

public class PrincipalFolhaDePagamento
{
    public static void main(String[] args)
    {
        Empregado[] listaDeEmpregados = new Empregado[1000];
        int quantidadeEmpregados = 0;
        
        // MENU
        Scanner ler = new Scanner(System.in);
        boolean sair = false;
        int opcao, lendoInt;
        double lendoDouble;
        
        System.out.println("Folha de Pagamento - TARCISO FILHO");
        
        do
        {
            System.out.println("Digite:");
            System.out.println("1-Para adicionar um novo empregado;");
            System.out.println("2-Para remover um empregado;");
            System.out.println("3-Para lancar um cartao de ponto;");
            System.out.println("4-Para lancar um resultado de venda;");
            System.out.println("5-Para lancar uma taxa de servico;");
            System.out.println("6-Para alterar detalhes de um empregado;");
            System.out.println("7-Para rodar a folha de pagamento para hoje;");
            System.out.println("8-Para desfazer ou refazer o ultimo comando;");
            System.out.println("9-Para vizualizar a agenda de pagamento;");
            System.out.println("10-Para criar uma nova agenda de pagamento;");
            System.out.println("0-Para Sair\n");
            opcao=ler.nextInt();
            
            switch(opcao)
            {
                case 1:
                    
                break;
                case 2:
                    if( quantidadeEmpregados > 0 )
                    {
                        do
                        {
                            sair = false;
                            System.out.println("Digite o ID do Funcionario:");
                            lendoInt = ler.nextInt();
                            //InsiraAqui Caso o id esteja errado ou não existe
                            //InsiraAqui Incluir opcão de saida sem apagar
                            sair = true;
                        }while(sair != false);
                        for(int i = 0; i<quantidadeEmpregados; i++)
                        {
                            if(listaDeEmpregados[i].id == lendoInt)
                            {
                                //InsiraAqui Alterações para Redo/Undo
                                listaDeEmpregados[i].id = -1;//Remoção Lógica
                            }
                        }
                        System.out.println(" O funcionario foi removido com "
                                + "sucesso!\n");
                    }else
                    {
                        System.out.println("Não foi possivel realizar essa "
                                + "opção, pois não existe funcionarios!");
                    }
                break;
                case 3:
                    
                break;
                case 4:
                    
                break;
                case 5:
                    
                break;
                case 6:
                    
                break;
                case 7:
                    
                break;
                case 8:
                    
                break;
                case 9:
                    
                break;
                case 10:
                    
                break;
                case 0:
                    System.out.println("O programa foi encerrado com sucesso!"
                            + "\nMuito obrigado pelo uso!!!");
                break;
                default:
                    System.out.println("Você digitou uma opção incorreta,"
                            + " por favor, digite outra opção!\n");
            }
        }while(opcao != 0);
    }
    
}
