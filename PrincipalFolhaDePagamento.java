package folhadepagamento;
import java.util.Calendar;
import java.util.Scanner;

public class PrincipalFolhaDePagamento
{
    public static void imprimirEmpregado( Empregado info )
    {
        System.out.println("\nEMPREGADO #" + info.id);
        System.out.println("Nome: " + info.nome);
        System.out.println("Endereço: " + info.endereco);
        System.out.println("Tipo: " + info.tipo + "\n 1-Horista\n 2-Assalariado"
                + "\n 3-Comissionado");
        System.out.println("Salário: R$" + info.salarioBruto);
        System.out.println("Valor Comissões: R$ " + info.comissao);
        System.out.printf("Pertence a um Sindicato: ");
        if(info.pertencenteAoSindicato)
        {
            System.out.println("SIM");
            System.out.println("ID no Sindicato: #" + info.idSindicato);
            System.out.println("Taxa Sindical: R$ " + info.taxaSindical);
            System.out.println("Taxa Sindical Extra: R$ " 
                    + info.taxaSindicalExtra);
        }else
        {
            System.out.println("NÃO");
        }
        System.out.println("Proximo Pagamento: " 
                + info.proximoPagamento.get(Calendar.DAY_OF_MONTH) + "/" 
                + (info.proximoPagamento.get(Calendar.MONTH) + 1) + "/" 
                + info.proximoPagamento.get(Calendar.YEAR) );
        System.out.println("Método de Pagamento: " + info.metodoDePagamento 
                + "\n 1-Cheque pelos correios\n 2-Cheque em mãos"
                        + "\n 3-Depósito em conta bancária");
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        Empregado[] listaDeEmpregados = new Empregado[1000];
        int quantidadeEmpregados = 0, idLivre = 1;
        
        // MENU
        Scanner ler = new Scanner(System.in);
        Empregado novoFuncionario = new Empregado("","",0,0.0,0.0,1,1,1900,0);
        Empregado anterior = new Empregado("","",0,0.0,0.0,1,1,1900,0);
        Empregado posterior = new Empregado("","",0,0.0,0.0,1,1,1900,0);
        Calendar dataCursor;
        boolean sair, estado, algoFoiDesfeito = false;
        int opcao, identificador, horaEntrada, minutosEntrada, horaSaida;
        int minutosSaida, horasTrabalhadas, lendoInt, quantidadeRecebedores;
        int idAnterior = -1;
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
                    dataCursor = Calendar.getInstance();
                    System.out.println(dataCursor.get(Calendar.DAY_OF_MONTH) + "/" 
                        + (dataCursor.get(Calendar.MONTH)+1) + "/" 
                        + dataCursor.get(Calendar.YEAR) + " " + dataCursor.get(Calendar.DAY_OF_WEEK));
                    switch(lendoInt)
                    {
                        case 1:
                            while(dataCursor.get(Calendar.DAY_OF_WEEK)!=Calendar.FRIDAY)
                            {
                                dataCursor.add(Calendar.DAY_OF_MONTH, 1);
                                
                            }
                        break;
                        case 2:
                            dataCursor.set(Calendar.DAY_OF_MONTH, 1);
                            dataCursor.add(Calendar.MONTH, 1);
                            dataCursor.add(Calendar.DAY_OF_MONTH, -1);
                            
                        break;
                        case 3:
                            identificador = 0;
                            while(identificador<=1)
                            {
                                if(dataCursor.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
                                {
                                    identificador++;
                                }
                                if(identificador<=1)
                                {
                                    dataCursor.add(Calendar.DAY_OF_MONTH, 1);
                                }
                            }
                        break;
                        default:
                            
                    }
                    System.out.println(dataCursor.get(Calendar.DAY_OF_MONTH) + "/" 
                        + (dataCursor.get(Calendar.MONTH)+1) + "/" 
                        + dataCursor.get(Calendar.YEAR) + " " + dataCursor.get(Calendar.DAY_OF_WEEK));
                    novoFuncionario.proximoPagamento.set(dataCursor.get
                        (Calendar.YEAR), (dataCursor.get(Calendar.MONTH)), 
                        dataCursor.get(Calendar.DAY_OF_MONTH));
                    /*System.out.println(novoFuncionario.proximoPagamento.get(Calendar.DAY_OF_MONTH) + "/" 
                        + (novoFuncionario.proximoPagamento.get(Calendar.MONTH)+1) + "/" 
                        + novoFuncionario.proximoPagamento.get(Calendar.YEAR) + " " + novoFuncionario.proximoPagamento.get(Calendar.DAY_OF_WEEK));*/
                    novoFuncionario.tipo = lendoInt;
                    System.out.println("Digite o salário do funcionário:\n"
                            + "(Se horista, o valor da hora trabalhada\n"
                            + " Se assalariado ou comissionado, o valor mensal)");
                    System.out.print("R$ ");
                    lendoDouble = ler.nextDouble();
                    novoFuncionario.salarioBruto = lendoDouble;
                    novoFuncionario.comissao = 0.0;
                    novoFuncionario.id = idLivre;
                    idAnterior = idLivre-1;
                    listaDeEmpregados[(idLivre-1)] = new Empregado
                        (novoFuncionario.nome, novoFuncionario.endereco, novoFuncionario.tipo, 
                        novoFuncionario.salarioBruto, novoFuncionario.comissao, 
                        novoFuncionario.proximoPagamento.get(Calendar.DAY_OF_MONTH), 
                        novoFuncionario.proximoPagamento.get(Calendar.MONTH), 
                        novoFuncionario.proximoPagamento.get(Calendar.YEAR), 
                        novoFuncionario.id);
                    anterior.id = -1;
                    idLivre++;
                    quantidadeEmpregados++;
                    System.out.println("O funcionario do ID " +(idLivre-1)
                            + " foi cadastrado com sucesso!\n");
                    imprimirEmpregado(listaDeEmpregados[idLivre-2]);
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
                        sair=false;
                        for(int i = 0; i<quantidadeEmpregados; i++)
                        {
                            if(listaDeEmpregados[i].id == lendoInt)
                            {
                                //InsiraAqui Alterações para Redo/Undo
                                idAnterior = i;
                                anterior.comissao=listaDeEmpregados[i].comissao;
                                anterior.endereco=listaDeEmpregados[i].endereco;
                                anterior.id=listaDeEmpregados[i].id;
                                anterior.idSindicato=
                                        listaDeEmpregados[i].idSindicato;
                                anterior.metodoDePagamento=
                                        listaDeEmpregados[i].metodoDePagamento;
                                anterior.nome=listaDeEmpregados[i].nome;
                                anterior.pertencenteAoSindicato=
                                        listaDeEmpregados[i].pertencenteAoSindicato;
                                anterior.proximoPagamento=
                                        listaDeEmpregados[i].proximoPagamento;
                                anterior.salarioBruto=
                                        listaDeEmpregados[i].salarioBruto;
                                anterior.salarioLiquido=
                                        listaDeEmpregados[i].salarioLiquido;
                                anterior.taxaSindical=
                                        listaDeEmpregados[i].taxaSindical;
                                anterior.taxaSindicalExtra=
                                        listaDeEmpregados[i].taxaSindicalExtra;
                                anterior.tipo=listaDeEmpregados[i].tipo;
                                //Fim da copia para Redo/Undo
                                listaDeEmpregados[i].id = -1;//Remoção Lógica
                                sair=true;
                                quantidadeEmpregados--;
                                System.out.println(" O funcionario foi removido"
                                        + " com sucesso!\n");
                            }
                        }
                        if(!sair)
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
                            //InsiraAqui Alterações para Redo/Undo
                            idAnterior = i;
                            anterior.comissao=listaDeEmpregados[i].comissao;
                            anterior.endereco=listaDeEmpregados[i].endereco;
                            anterior.id=listaDeEmpregados[i].id;
                            anterior.idSindicato=
                                        listaDeEmpregados[i].idSindicato;
                            anterior.metodoDePagamento=
                                        listaDeEmpregados[i].metodoDePagamento;
                            anterior.nome=listaDeEmpregados[i].nome;
                            anterior.pertencenteAoSindicato=
                                        listaDeEmpregados[i].pertencenteAoSindicato;
                            anterior.proximoPagamento=
                                        listaDeEmpregados[i].proximoPagamento;
                            anterior.salarioBruto=
                                        listaDeEmpregados[i].salarioBruto;
                            anterior.salarioLiquido=
                                        listaDeEmpregados[i].salarioLiquido;
                            anterior.taxaSindical=
                                        listaDeEmpregados[i].taxaSindical;
                            anterior.taxaSindicalExtra=
                                        listaDeEmpregados[i].taxaSindicalExtra;
                            anterior.tipo=listaDeEmpregados[i].tipo;
                            //Fim da copia para Redo/Undo
                            System.out.println("Informe o horario de entrada:");
                            horaString = ler.nextLine();
                            horaEntrada = (int) ( horaString.charAt(0) - '0') 
                                    * 10 + (int) ( horaString.charAt(1) - '0');
                            minutosEntrada = (int) ( horaString.charAt(3) - '0') 
                                    * 10 + (int) ( horaString.charAt(4) - '0');
                            System.out.println("Informe o horario de saída:");
                            horaString = ler.nextLine();
                            horaSaida = (int) ( horaString.charAt(0) - '0') 
                                    * 10 + (int) ( horaString.charAt(1) - '0');
                            minutosSaida = (int) ( horaString.charAt(3) - '0') 
                                    * 10 + (int) ( horaString.charAt(4) - '0');
                            horasTrabalhadas = horaSaida - horaEntrada;
                            if(minutosEntrada > minutosSaida)
                            {
                                horasTrabalhadas--;
                            }
                            if(horasTrabalhadas <= 8)
                            {
                                listaDeEmpregados[i].salarioLiquido += 
                                        (listaDeEmpregados[i].salarioBruto 
                                        * horasTrabalhadas);
                            }else
                            {
                                listaDeEmpregados[i].salarioLiquido += 
                                        (listaDeEmpregados[i].salarioBruto * 8);
                                listaDeEmpregados[i].salarioLiquido += 
                                        (horasTrabalhadas - 8.0) * 
                                        listaDeEmpregados[i].salarioBruto * 1.5;
                            }
                            System.out.println("O Cartão foi batido com sucesso!");
                            sair = true;
                        }
                    }
                    if( !sair )
                    {
                        System.out.println("O Empregado não foi encontrado!");
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
                            //InsiraAqui Alterações para Redo/Undo
                            idAnterior = i;
                            anterior.comissao=listaDeEmpregados[i].comissao;
                            anterior.endereco=listaDeEmpregados[i].endereco;
                            anterior.id=listaDeEmpregados[i].id;
                            anterior.idSindicato=
                                        listaDeEmpregados[i].idSindicato;
                            anterior.metodoDePagamento=
                                        listaDeEmpregados[i].metodoDePagamento;
                            anterior.nome=listaDeEmpregados[i].nome;
                            anterior.pertencenteAoSindicato=
                                        listaDeEmpregados[i].pertencenteAoSindicato;
                            anterior.proximoPagamento=
                                        listaDeEmpregados[i].proximoPagamento;
                            anterior.salarioBruto=
                                        listaDeEmpregados[i].salarioBruto;
                            anterior.salarioLiquido=
                                        listaDeEmpregados[i].salarioLiquido;
                            anterior.taxaSindical=
                                        listaDeEmpregados[i].taxaSindical;
                            anterior.taxaSindicalExtra=
                                        listaDeEmpregados[i].taxaSindicalExtra;
                            anterior.tipo=listaDeEmpregados[i].tipo;
                            //Fim da copia para Redo/Undo
                            System.out.println("Informe o valor da venda:");
                            System.out.print("R$ ");
                            valor = ler.nextDouble();
                            System.out.println("Informe a porcentagem da "
                                    + "comissão:");
                            porcentagem = ler.nextDouble();
                            listaDeEmpregados[i].comissao += valor / 100 * porcentagem;
                            sair = true;
                            System.out.println("A comissão de R$ "
                                    +(valor / 100 * porcentagem)
                                    + " foi registrada!");
                        }
                    }
                    if( !sair )
                    {
                        System.out.println("O Empregado não foi encontrado!");
                    }
                break;
                case 5:
                    System.out.println("Informe o ID do funcionario:");
                    identificador = ler.nextInt();
                    sair = false;
                    for(int i = 0; i<quantidadeEmpregados && !sair; i++)
                    {
                        if(listaDeEmpregados[i].id == identificador)
                        {
                            //InsiraAqui Alterações para Redo/Undo
                            idAnterior = i;
                            anterior.comissao=listaDeEmpregados[i].comissao;
                            anterior.endereco=listaDeEmpregados[i].endereco;
                            anterior.id=listaDeEmpregados[i].id;
                            anterior.idSindicato=
                                        listaDeEmpregados[i].idSindicato;
                            anterior.metodoDePagamento=
                                        listaDeEmpregados[i].metodoDePagamento;
                            anterior.nome=listaDeEmpregados[i].nome;
                            anterior.pertencenteAoSindicato=
                                        listaDeEmpregados[i].pertencenteAoSindicato;
                            anterior.proximoPagamento=
                                        listaDeEmpregados[i].proximoPagamento;
                            anterior.salarioBruto=
                                        listaDeEmpregados[i].salarioBruto;
                            anterior.salarioLiquido=
                                        listaDeEmpregados[i].salarioLiquido;
                            anterior.taxaSindical=
                                        listaDeEmpregados[i].taxaSindical;
                            anterior.taxaSindicalExtra=
                                        listaDeEmpregados[i].taxaSindicalExtra;
                            anterior.tipo=listaDeEmpregados[i].tipo;
                            //Fim da copia para Redo/Undo
                            System.out.println("Informe a taxa de serviço:");
                            System.out.print("R$ ");
                            lendoDouble = ler.nextDouble();
                            listaDeEmpregados[i].taxaSindicalExtra += lendoDouble;
                            sair = true;
                            System.out.println("A taxa de serviço de R$ "
                                    +lendoDouble+ " foi registrada!");
                        }
                    }
                    if( !sair )
                    {
                        System.out.println("O Empregado não foi encontrado!");
                    }
                break;
                case 6:
                    System.out.println("Informe o ID do funcionario:");
                    identificador = ler.nextInt();
                    sair = false;
                    for(int i = 0; i<quantidadeEmpregados && !sair; i++)
                    {
                        if(listaDeEmpregados[i].id == identificador)
                        {
                            //InsiraAqui Alterações para Redo/Undo
                            idAnterior = i;
                            anterior.comissao=listaDeEmpregados[i].comissao;
                            anterior.endereco=listaDeEmpregados[i].endereco;
                            anterior.id=listaDeEmpregados[i].id;
                            anterior.idSindicato=
                                        listaDeEmpregados[i].idSindicato;
                            anterior.metodoDePagamento=
                                        listaDeEmpregados[i].metodoDePagamento;
                            anterior.nome=listaDeEmpregados[i].nome;
                            anterior.pertencenteAoSindicato=
                                        listaDeEmpregados[i].pertencenteAoSindicato;
                            anterior.proximoPagamento=
                                        listaDeEmpregados[i].proximoPagamento;
                            anterior.salarioBruto=
                                        listaDeEmpregados[i].salarioBruto;
                            anterior.salarioLiquido=
                                        listaDeEmpregados[i].salarioLiquido;
                            anterior.taxaSindical=
                                        listaDeEmpregados[i].taxaSindical;
                            anterior.taxaSindicalExtra=
                                        listaDeEmpregados[i].taxaSindicalExtra;
                            anterior.tipo=listaDeEmpregados[i].tipo;
                            //Fim da copia para Redo/Undo
                            System.out.println("Substituir o nome completo do "
                                    + "funcionário: " + listaDeEmpregados[i].nome);
                            lendoString = ler.nextLine();
                            listaDeEmpregados[i].nome = lendoString;
                            System.out.println("Substituir o endereço do "
                                    + "funcionário: " 
                                    + listaDeEmpregados[i].endereco);
                            lendoString = ler.nextLine();
                            listaDeEmpregados[i].endereco = lendoString;
                            System.out.println("Substitua o tipo do funcionário:"
                                    + "\n1-Horista\n2-Assalariado"
                                    + "\n3-Comissionado\nTipo Atual:" 
                                    + listaDeEmpregados[i].tipo );
                            lendoInt = ler.nextInt();
                            /*switch(lendoInt)
                            {
                                case 1:
                                    listaDeEmpregados[i].diasAtePagamento = 6;
                                break;
                                case 2:
                                    listaDeEmpregados[i].diasAtePagamento = 29;
                                break;
                                case 3:
                                    listaDeEmpregados[i].diasAtePagamento = 13;
                            }*/
                            listaDeEmpregados[i].tipo = lendoInt;
                            System.out.println("Substitua o salário do funcionário:\n"
                            + "(Se horista, o valor da hora trabalhada\n"
                            + " Se assalariado ou comissionado, o valor mensal)"
                                    + "\nValor Atual: R$ " 
                                    + listaDeEmpregados[i].salarioBruto );
                            System.out.print("R$ ");
                            lendoDouble = ler.nextDouble();
                            novoFuncionario.salarioBruto = lendoDouble;
                            System.out.println("Substitua o valor da comissão "
                                    + "do funcionário:");
                            System.out.print("Valor Atual: R$ " 
                                    + listaDeEmpregados[i].comissao + "\nR$ ");
                            lendoDouble = ler.nextDouble();
                            listaDeEmpregados[i].comissao = lendoDouble;
                            System.out.println("Substitua o estado de "
                                    + "associação ao sindicato: (S/N)");
                            lendoString = ler.nextLine();
                            estado = false;
                            if(lendoString.charAt(0) == 'S')
                            {
                                System.out.println("Informe o ID perante ao "
                                        + "sindicato:");
                                lendoInt = ler.nextInt();
                                listaDeEmpregados[i].idSindicato = lendoInt;
                                System.out.println("Informe a taxa sindical:");
                                lendoDouble = ler.nextDouble();
                                listaDeEmpregados[i].taxaSindical = lendoDouble;
                                estado = true;
                            }
                            listaDeEmpregados[i].pertencenteAoSindicato = estado;
                            System.out.println("Substitua o método de pagamento"
                                    + " do funcionário:\n1-Cheque pelos correios"
                                    + "\n2-Cheque em mãos\n3-Depósito em conta "
                                    + "bancária\nMétodo Atual:" 
                                    + listaDeEmpregados[i].metodoDePagamento );
                            lendoInt = ler.nextInt();
                            listaDeEmpregados[i].metodoDePagamento = lendoInt;
                            sair = true;
                            System.out.println("O funcionário foi editado com "
                                    + "sucesso!");
                        }
                    }
                    if( !sair )
                    {
                        System.out.println("O Empregado não foi encontrado!");
                    }
                break;
                case 7:
                    dataCursor = Calendar.getInstance();
                    quantidadeRecebedores = 0;
                    if( quantidadeEmpregados > 0 )
                    {
                        for(int i = 0; i<quantidadeEmpregados; i++)
                        {
                            if(dataCursor.get(Calendar.DAY_OF_MONTH)==listaDeEmpregados[i].proximoPagamento.get(Calendar.DAY_OF_MONTH) 
                                    && dataCursor.get(Calendar.MONTH)==listaDeEmpregados[i].proximoPagamento.get(Calendar.MONTH) 
                                    && dataCursor.get(Calendar.YEAR)==listaDeEmpregados[i].proximoPagamento.get(Calendar.YEAR))
                            {
                                if(listaDeEmpregados[i].tipo == 2)
                                {
                                    listaDeEmpregados[i].salarioLiquido += listaDeEmpregados[i].salarioBruto;
                                }if(listaDeEmpregados[i].tipo == 3)
                                {
                                    listaDeEmpregados[i].salarioLiquido += 
                                            (listaDeEmpregados[i].salarioBruto/2) + listaDeEmpregados[i].comissao;
                                }
                                listaDeEmpregados[i].salarioLiquido -= (listaDeEmpregados[i].taxaSindical + listaDeEmpregados[i].taxaSindicalExtra);
                                System.out.println("Ao Funcionario " + listaDeEmpregados[i].nome + ", deve ser pago R$ " + listaDeEmpregados[i].salarioLiquido);
                                quantidadeRecebedores++;
                                listaDeEmpregados[i].salarioLiquido = 0.0;
                                switch(listaDeEmpregados[i].tipo)
                                {
                                    case 1:
                                        while(dataCursor.get(Calendar.DAY_OF_WEEK)!=Calendar.FRIDAY)
                                        {
                                            dataCursor.add(Calendar.DAY_OF_MONTH, 1);
                                
                                        }
                                    break;
                                    case 2:
                                        dataCursor.set(Calendar.DAY_OF_MONTH, 1);
                                        dataCursor.add(Calendar.MONTH, 1);
                                        dataCursor.add(Calendar.DAY_OF_MONTH, -1);
                            
                                    break;
                                    case 3:
                                        identificador = 0;
                                        while(identificador<=1)
                                        {
                                            if(dataCursor.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
                                            {
                                                identificador++;
                                            }
                                            if(identificador<=1)
                                            {
                                                dataCursor.add(Calendar.DAY_OF_MONTH, 1);
                                            }
                                        }
                                    break;
                                    default:
                            
                                }
                            }
                        }
                        /*quantidadeRecebedores = 0;
                        for(int i = 0; i<quantidadeEmpregados; i++)
                        {
                            if( listaDeEmpregados[i].diasAtePagamento > 1 )
                            {
                                listaDeEmpregados[i].diasAtePagamento--;
                            }else
                            {
                                quantidadeRecebedores++;
                                switch(listaDeEmpregados[i].tipo)
                                {
                                    
                                }
                            }
                        }*/
                    }else
                    {
                        System.out.println("Não foi possivel realizar essa "
                                + "opção, pois não existe funcionarios!");
                    }
                    System.out.println("Devem ser pagos " + quantidadeRecebedores + "funcionario(s).");
                break;
                case 8:
                    if( quantidadeEmpregados > 0 )
                    {
                        if(!algoFoiDesfeito)
                        {
                            //InsiraAqui Alterações para Redo
                            posterior.comissao=listaDeEmpregados[idAnterior].comissao;
                            posterior.endereco=listaDeEmpregados[idAnterior].endereco;
                            posterior.id=listaDeEmpregados[idAnterior].id;
                            posterior.idSindicato=
                                        listaDeEmpregados[idAnterior].idSindicato;
                            posterior.metodoDePagamento=
                                        listaDeEmpregados[idAnterior].metodoDePagamento;
                            posterior.nome=listaDeEmpregados[idAnterior].nome;
                            posterior.pertencenteAoSindicato=
                                        listaDeEmpregados[idAnterior].pertencenteAoSindicato;
                            posterior.proximoPagamento=
                                        listaDeEmpregados[idAnterior].proximoPagamento;
                            posterior.salarioBruto=
                                        listaDeEmpregados[idAnterior].salarioBruto;
                            posterior.salarioLiquido=
                                        listaDeEmpregados[idAnterior].salarioLiquido;
                            posterior.taxaSindical=
                                        listaDeEmpregados[idAnterior].taxaSindical;
                            posterior.taxaSindicalExtra=
                                        listaDeEmpregados[idAnterior].taxaSindicalExtra;
                            posterior.tipo=listaDeEmpregados[idAnterior].tipo;
                            //Fim da copia para Redo
                            //InsiraAqui Modificações para Undo
                            listaDeEmpregados[idAnterior].comissao=anterior.comissao;
                            listaDeEmpregados[idAnterior].endereco=anterior.endereco;
                            listaDeEmpregados[idAnterior].id=anterior.id;
                            listaDeEmpregados[idAnterior].idSindicato=
                                    anterior.idSindicato;
                            listaDeEmpregados[idAnterior].metodoDePagamento=
                                    anterior.metodoDePagamento;
                            listaDeEmpregados[idAnterior].nome=anterior.nome;
                            listaDeEmpregados[idAnterior].pertencenteAoSindicato=
                                    anterior.pertencenteAoSindicato;
                            listaDeEmpregados[idAnterior].proximoPagamento=
                                    anterior.proximoPagamento;
                            listaDeEmpregados[idAnterior].salarioBruto=anterior.salarioBruto;
                            listaDeEmpregados[idAnterior].salarioLiquido=anterior.salarioLiquido;
                            listaDeEmpregados[idAnterior].taxaSindical=anterior.taxaSindical;
                            listaDeEmpregados[idAnterior].taxaSindicalExtra=anterior.taxaSindicalExtra;
                            listaDeEmpregados[idAnterior].tipo=anterior.tipo;
                            //Fim do Undo
                            System.out.println("A ultima ação executada foi desfeita");
                            algoFoiDesfeito = true;
                        }else
                        {
                            //InsiraAqui Modificações para Redo
                            listaDeEmpregados[idAnterior].comissao=posterior.comissao;
                            listaDeEmpregados[idAnterior].endereco=posterior.endereco;
                            listaDeEmpregados[idAnterior].id=posterior.id;
                            listaDeEmpregados[idAnterior].idSindicato=
                                    posterior.idSindicato;
                            listaDeEmpregados[idAnterior].metodoDePagamento=
                                    posterior.metodoDePagamento;
                            listaDeEmpregados[idAnterior].nome=posterior.nome;
                            listaDeEmpregados[idAnterior].pertencenteAoSindicato=
                                    posterior.pertencenteAoSindicato;
                            listaDeEmpregados[idAnterior].proximoPagamento=
                                    posterior.proximoPagamento;
                            listaDeEmpregados[idAnterior].salarioBruto=posterior.salarioBruto;
                            listaDeEmpregados[idAnterior].salarioLiquido=posterior.salarioLiquido;
                            listaDeEmpregados[idAnterior].taxaSindical=posterior.taxaSindical;
                            listaDeEmpregados[idAnterior].taxaSindicalExtra=posterior.taxaSindicalExtra;
                            listaDeEmpregados[idAnterior].tipo=posterior.tipo;
                            //Fim do Redo
                            System.out.println("A ultima ação executada foi refeita");
                            algoFoiDesfeito = false;
                        }
                    }else
                    {
                        System.out.println("Não foi possivel realizar essa "
                                + "opção, pois não existe funcionarios!");
                    }
                    
                    
                break;
                case 9:
                    if( quantidadeEmpregados > 0 )
                    {
                        for(int i = 0; i<quantidadeEmpregados; i++)
                        {
                            imprimirEmpregado(listaDeEmpregados[i]);
                        }
                    }else
                    {
                        System.out.println("Não foi possivel realizar essa "
                                + "opção, pois não existe funcionarios!");
                    }
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
