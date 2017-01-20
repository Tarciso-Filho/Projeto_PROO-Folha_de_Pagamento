package folhadepagamento;
import java.util.Scanner;
//import java.text.SimpleDateFormat;

public class PrincipalFolhaDePagamento
{
    public static void main(String[] args)
    {
        Empregado[] listaDeEmpregados = new Empregado[1000];
        int quantidadeEmpregados = 0, idLivre = 1;
        
        // MENU
        Scanner ler = new Scanner(System.in);
        Empregado novoFuncionario = new Empregado("","",0,0.0,0.0,0);
        boolean sair,apagado;
        int opcao, identificador, horaEntrada, minutosEntrada, horaSaida;
        int minutosSaida, horasTrabalhadas, lendoInt;
        double lendoDouble, valor, porcentagem;
        String lendoString, horaString;
        
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
            ler.nextLine();
            
            switch(opcao)
            {
                case 1:
                    System.out.println("Digite o nome completo do funcionário:");
                    lendoString = ler.nextLine();
                    novoFuncionario.nome = lendoString;
                    System.out.println("Digite o endereço do funcionário:");
                    lendoString = ler.nextLine();
                    novoFuncionario.endereco = lendoString;
                    System.out.println("Escolha o tipo do funcionário:\n"
                            + "1-Horista\n2-Assalariado\n3-Comissionado");
                    lendoInt = ler.nextInt();
                    novoFuncionario.tipo = lendoInt;
                    System.out.println("Digite o salário do funcionário:\n"
                            + "(Se horista, o valor da hora trabalhada\n"
                            + " Se assalariado ou comissionado, o valor mensal)");
                    System.out.print("R$ ");
                    lendoDouble = ler.nextDouble();
                    novoFuncionario.salarioBruto = lendoDouble;
                    novoFuncionario.comissao = 0.0;
                    novoFuncionario.id = idLivre;
                    listaDeEmpregados[(idLivre-1)] = new Empregado
        (novoFuncionario.nome, novoFuncionario.endereco, novoFuncionario.tipo, 
                            novoFuncionario.salarioBruto, novoFuncionario.comissao, 
                            novoFuncionario.id);
                    idLivre++;
                    quantidadeEmpregados++;
                    System.out.println("O funcionario do ID " +(idLivre-1)
                            + " foi cadastrado com sucesso!\n");
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
                        }while(sair != true);
                        apagado=false;
                        for(int i = 0; i<quantidadeEmpregados; i++)
                        {
                            if(listaDeEmpregados[i].id == lendoInt)
                            {
                                //InsiraAqui Alterações para Redo/Undo
                                listaDeEmpregados[i].id = -1;//Remoção Lógica
                                apagado=true;
                                quantidadeEmpregados--;
                                System.out.println(" O funcionario foi removido"
                                        + " com sucesso!\n");
                            }
                        }
                        if(!apagado)
                        {
                            System.out.println("O Funcionario não foi encontrado!");
                        }
                    }else
                    {
                        System.out.println("Não foi possivel realizar essa "
                                + "opção, pois não existe funcionarios!");
                    }
                break;
                case 3:
                    System.out.println("Informe o ID do funcionario horista:");
                    identificador = ler.nextInt();
                    ler.nextLine();
                    sair=false;
                    for(int i = 0; i<quantidadeEmpregados && !sair; i++)
                    {
                        if(listaDeEmpregados[i].id == identificador)
                        {
                            System.out.println("Informe o horario de entrada:");
                            horaString = ler.nextLine();
                            horaEntrada = (int) ( horaString.charAt(0) - '0') 
                                    * 10 + (int) ( horaString.charAt(1) - '0');
                            minutosEntrada = (int) ( horaString.charAt(3) - '0') 
                                    * 10 + (int) ( horaString.charAt(4) - '0');
                            /*horaEntrada=ler.nextInt();
                            ler.nextByte();
                            minutosEntrada=ler.nextInt();*/
                            System.out.println("Informe o horario de saída:");
                            horaString = ler.nextLine();
                            horaSaida = (int) ( horaString.charAt(0) - '0') 
                                    * 10 + (int) ( horaString.charAt(1) - '0');
                            minutosSaida = (int) ( horaString.charAt(3) - '0') 
                                    * 10 + (int) ( horaString.charAt(4) - '0');
                            /*horaSaida=ler.nextInt();
                            ler.nextByte();
                            minutosSaida=ler.nextInt();*/
                            horasTrabalhadas = horaSaida - horaEntrada;
                            if(minutosEntrada > minutosSaida)
                            {
                                horasTrabalhadas--;
                            }
                            if(horasTrabalhadas <= 8)
                            {
                                listaDeEmpregados[i].salarioLiquido += (listaDeEmpregados[i].salarioBruto * horasTrabalhadas);
                            }else
                            {
                                listaDeEmpregados[i].salarioLiquido = listaDeEmpregados[i].salarioLiquido + (listaDeEmpregados[i].salarioBruto * 8);
                                listaDeEmpregados[i].salarioLiquido = listaDeEmpregados[i].salarioLiquido + (horasTrabalhadas - 8.0) * listaDeEmpregados[i].salarioBruto * 1.5;
                            }
                            System.out.println("O Cartão foi batido com sucesso!");
                            sair = true;
                        }
                    }
                    if( sair == false )
                    {
                        System.out.println("O Empregado não foi encontrado");
                    }
                break;
                case 4:
                    System.out.println("Informe o ID do funcionario comissionado:");
                    identificador = ler.nextInt();
                    sair = false;
                    for(int i = 0; i<quantidadeEmpregados && !sair; i++)
                    {
                        if(listaDeEmpregados[i].id == identificador)
                        {
                            System.out.println("Informe o valor da venda:");
                            valor = ler.nextDouble();
                            System.out.println("Informe a porcentagem da "
                                    + "comissão:");
                            porcentagem = ler.nextDouble();
                            listaDeEmpregados[i].comissao += valor / 100 * porcentagem;
                            sair = true;
                            System.out.println("A comissão de R$ "
                                    +(valor / 100 * porcentagem)+ "foi registrada!");
                        }
                    }
                    if( sair == false )
                    {
                        System.out.println("O Empregado não foi encontrado!");
                    }
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
