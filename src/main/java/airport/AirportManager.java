package airport;

import java.util.List;
import java.util.Scanner;

import arquivo.ArquivoCsv;
import model.Cliente;
import model.Passagem;

public class AirportManager {
	// Menus
	
    static void MenuBoasVindas() {
        System.out.println();
        System.out.println("***** Bem vindo (19/02/2024) *****");
        System.out.println("1 - Menu do cliente");
        System.out.println("2 - Menu de Passagens");
        System.out.println("0 - Para encerrar o programa");
        System.out.println();
        System.out.print("Selecione uma das opcoes acima: ");
    }

    static void MenuCliente() {
        System.out.println();
        System.out.println("****** Menu Cliente *****");
        System.out.println("1 - Ver clientes cadastrados");
        System.out.println("2 - Adicionar cadastro");
        System.out.println("3 - Atualizar cadastro");
        System.out.println("4 - Deleta cadastro");
        System.out.println("9 - Para Voltar");
        System.out.println();
        System.out.print("Escolha uma das opcoes acima: ");

    }

    static void MenuPassagem() {
        System.out.println();
        System.out.println("***** Menu Passagem *****");
        System.out.println("1 - Ver Passagem");
        System.out.println("2 - Compra Passagem");
        System.out.println("3 - Trocar Passagem");
        System.out.println("4 - Cancelar Passagem");
        System.out.println("5 - Ver voos disponíveis");
        System.out.println("9 - Voltar");
        System.out.println();
        System.out.print("Escolha uma das opcoes acima: ");
    }

    // Cliente

    static void VerClientes(ArquivoCsv file) {
        List<String> lista = file.LerLinhasArquivo();
        System.out.println();
        System.out.println("Código identificado / Saindo de / Chegando em / Duração / Preço");
        System.out.println();

        for(int i = 1; i < lista.size() ; i++ ){

            String[] array = file.ConverteStringParaArray(lista.get(i));
            System.out.println();
            System.out.println(array[0] + " / " + array[1] + " / " + array[2] + " / " + array[3]);
        }

    }

    static void AdicionarCliente(ArquivoCsv file){

        Scanner sc2 = new Scanner(System.in);

        System.out.print("Informe o nome: ");
        String nome = sc2.next();

        String cpf;

        do{
            System.out.println("Informe um cpf de 14 digítos: ");
            cpf = sc2.next();
        }while(cpf.length() != 14);



        Cliente c = new Cliente(file,nome,cpf);

        file.AdcionarLinha(c.ConverteClienteParaString());

    }

    static void RemoverCliente(ArquivoCsv file){
        // Remove o casatro de um cliente
        System.out.println();
        System.out.println("***** Remover cadastro *****");

        int opt = -1;
        do{
            try{

                System.out.print("Informe o id do cadastro que deseja apagar (0 - cancelar): ");
                Scanner sc = new Scanner(System.in);
                opt = sc.nextInt();

                if(!(file.ObterLinhaPorId(opt) == null)){
                    file.RemoveLinhaArquivoPorId(opt);

                    break;
                }else{

                    if(opt != 0){
                        System.out.println();
                        System.out.println("Id não encontrado, tente novamente");
                        System.out.println();
                    }
                    else{
                        System.out.println();
                        System.out.println("Voltando ....");
                        System.out.println();
                    }

                }

            }catch (Exception e){
                System.out.println();
                System.out.println("Escreva apenas números!");
                System.out.println();
            }

        }while (!(opt == 0));

    }

    static void AtualizarCliente(ArquivoCsv file){
        // Remove o casatro de um cliente
        System.out.println();
        System.out.println("***** Atualizar cadastro *****");

        int opt = -1;
        do{
            try{

                System.out.print("Informe o id do cadastro que deseja atualizar (0 - cancelar): ");
                Scanner sc = new Scanner(System.in);
                opt = sc.nextInt();

                if(!(file.ObterLinhaPorId(opt) == null)){
                    System.out.println();
                    System.out.print("Informe o nome: ");
                    String nome = sc.next();

                    System.out.print("Informe o cpf: ");
                    String cpf = sc.next();

                    Cliente novoCliente = new Cliente(file,nome,cpf,opt,file.ObterIdPassagemPeloId(opt));

                    file.AtualizarLinhaArquivoPorId(opt,novoCliente.ConverteClienteParaString());


                    break;

                }else{

                    if(opt != 0){
                        System.out.println();
                        System.out.println("Id não encontrado, tente novamente");
                        System.out.println();
                    }
                    else{
                        System.out.println();
                        System.out.println("Voltando ....");
                        System.out.println();
                    }

                }

            }catch (Exception e){
                System.out.println();
                System.out.println("Escreva apenas números!");
                System.out.println();
            }

        }while (!(opt == 0));
    }

    static void ClienteMain(ArquivoCsv file) {

        int opcao = -1;
        do
        {
            MenuCliente();
            try
            {
                Scanner sc = new Scanner(System.in);
                opcao = sc.nextInt();

            }
            catch(Exception e)
            {
                System.out.println();
                System.out.println("Atenção: Use apenas números e escolha apenas umas das opções dispíneveis");
                System.out.println();
            }

            switch (opcao)
            {
                // Ver cadastros
                case 1:
                    VerClientes(file);
                    break;

                // Add card
                case 2:
                    AdicionarCliente(file);
                    break;

                // Remove card
                case 3:
                    AtualizarCliente(file);
                    break;

                // Update card
                case 4:
                    RemoverCliente(file);
                    break;

                // Read all deck
                case 9:
                    break;


                default:
                    System.out.println();
                    System.out.println("Entrada inválida, Por favor escolha apenas as opções diponéveis");
                    System.out.println();
                    break;

            }

        }
        while (!(opcao == 9));
    }

    // Passagem
    static void VerPassagem(ArquivoCsv filePassagem, ArquivoCsv fileCliente){
        /*
        * Lista as Passagens comprados com seus donos
        * */
        System.out.println("***** Passagens Compradas *****");
        System.out.println();

        System.out.println("ID -- Nome -- Local de partida -- Local de chegada -- Duração -- Preço");
        System.out.println();
        // listas com toda as linhas
        List<String> listaCliente = fileCliente.LerLinhasArquivo();
        List<String> listaPassagem = filePassagem.LerLinhasArquivo();

        // Instâncias de classes
        Cliente cliente = new Cliente(fileCliente);
        Passagem passagem = new Passagem();

        for(int i = 1 ; i < fileCliente.TotalLinha(); i++){
            // Amarzenando linhas em arrays
           String[] linhaCliente = fileCliente.ConverteStringParaArray(listaCliente.get(i));
            String[] linhaPassagem = filePassagem.ConverteStringParaArray(listaPassagem.get(i));

            // Setando os dados em classes
            cliente.SetValues(linhaCliente);
            passagem.SetValues(linhaPassagem);

            if(TemPassagem(cliente.getId(),fileCliente)){

                System.out.print(cliente.getId() + " // ");
                System.out.print(cliente.getNome() + " // ");
                System.out.print(passagem.getLocalPartida() + " // ");
                System.out.print(passagem.getLocalChegada() + " // ");
                System.out.print(passagem.getDuracao() + " // ");
                System.out.print( "R$ "+ passagem.getPreco());
                System.out.println();
            }


        }
    }

    static void ComprarPassagem(ArquivoCsv filePassagem,ArquivoCsv fileCliente){
        // Muda o idPassagem

        System.out.println();
        System.out.println("***** Comprar passagem *****");
        
        int opt = -1;
        Scanner sc = new Scanner(System.in);

        do{

                try{
                    System.out.print("Informe o id do seu cadastro (0 - cancelar): ");
                    opt = sc.nextInt();


                    if(!(TemPassagem(opt,fileCliente)) && opt != 0 ){
                        // Mostrar passagens dispníveis
                        VerVooPassagem(filePassagem);
                        System.out.println();

                        // Perdir código da passagem
                        System.out.print("Informe o código identificado da passagem que quer comprar: ");

                        int idPassagem = sc.nextInt();
                        System.out.println(opt);

                        // Muda apenas o idPassagem
                        String[] array = fileCliente.ConverteStringParaArray(fileCliente.ObterLinhaPorId(opt));
                        array[3] = Integer.toString(idPassagem);

                        Cliente novoCliente = new Cliente(fileCliente,array);

                        fileCliente.AtualizarLinhaArquivoPorId(opt, novoCliente.ConverteClienteParaString());

                        // Válida se a pessoa tem uma passagem

                        System.out.println();
                        System.out.println("Passagem comprada com sucesso");
                        System.out.println("Voltando...");
                        System.out.println();

                        break;

                    }else if(TemPassagem(opt,fileCliente)){
                        System.out.println();
                        System.out.println("Você não pode ter mais de uma passagem.");
                        System.out.println();

                    }else if(opt != 0){
                        System.out.println();
                        System.out.println("Id não encontrado, tente novamente");
                        System.out.println();

                    }else{
                        System.out.println();
                        System.out.println("Voltando ....");
                        System.out.println();
                    }
                }catch (Exception e){
                    System.out.println();
                    System.out.println("Escreva apenas números!");
                    System.out.println();
                }



        }while (true);


    }

    static void TrocarPassagem(ArquivoCsv filePassagem,ArquivoCsv fileCliente){
        // Troca a passagem de um cliente

        System.out.println();
        System.out.println("***** Trocar passagem *****");
        Scanner scT = new Scanner(System.in);
        int opt = -1;
        do{
            try{

                System.out.print("Informe o id do seu cadastro (0 - cancelar): ");
                opt = scT.nextInt();

                if(TemPassagem(opt,fileCliente) && opt != 0){
                    // Mostrar passagens dispníveis
                    VerVooPassagem(filePassagem);
                    System.out.println();

                    // Perdir código da passagem
                    System.out.print("Informe o código identificado da passagem para qual deseja trocar: ");
                    int idPassagem = scT.nextInt();

                    // Muda apenas o idPassagem
                    String[] array = fileCliente.ConverteStringParaArray(fileCliente.ObterLinhaPorId(opt));
                    array[3] = Integer.toString(idPassagem);

                    Cliente novoCliente = new Cliente(fileCliente,array);

                    fileCliente.AtualizarLinhaArquivoPorId(opt, novoCliente.ConverteClienteParaString());

                    System.out.println();
                    System.out.println("Troca realizada com sucesso");
                    System.out.println("Voltando ....");
                    System.out.println();

                    break;

                }else if(!(TemPassagem(opt,fileCliente))){
                    System.out.println();
                    System.out.println("Você não tem uma passagem.");
                    System.out.println();

                }else if(opt != 0){
                    System.out.println();
                    System.out.println("Id não encontrado, tente novamente");
                    System.out.println();

                }else{
                    System.out.println();
                    System.out.println("Voltando ....");
                    System.out.println();
                }

            }catch (Exception e){
                System.out.println();
                System.out.println("Escreva apenas números!");
                System.out.println();
            }

        }while (!(opt == 0));

    }

    static void CancelarPassagem(ArquivoCsv filePassagem,ArquivoCsv fileCliente){
        System.out.println();
        System.out.println("***** Cancelar passagem *****");
        Scanner sc = new Scanner(System.in);

        int opt = -1;
        do{
            try{

                System.out.print("Informe o id do seu cadastro (0 - cancelar): ");
                opt = sc.nextInt();

                if(TemPassagem(opt,fileCliente) && opt != 0){
                    // Mostrar passagens dispníveis
                    VerVooPassagem(filePassagem);
                    System.out.println();

                    // Muda apenas o idPassagem
                    String[] array = fileCliente.ConverteStringParaArray(fileCliente.ObterLinhaPorId(opt));
                    array[3] = "0";

                    Cliente novoCliente = new Cliente(fileCliente, array);

                    fileCliente.AtualizarLinhaArquivoPorId(opt, novoCliente.ConverteClienteParaString());

                    System.out.println();
                    System.out.println("Cancelado com sucesso");
                    System.out.println("Voltando ....");
                    System.out.println();

                    break;

                }else if(!(TemPassagem(opt,fileCliente))){
                    System.out.println();
                    System.out.println("Você não tem uma passagem.");
                    System.out.println();

                }else if(opt != 0){
                    System.out.println();
                    System.out.println("Id não encontrado, tente novamente");
                    System.out.println();

                }else{
                    System.out.println();
                    System.out.println("Voltando ....");
                    System.out.println();
                }

            }catch (Exception e){
                System.out.println();
                System.out.println("Escreva apenas números!");
                System.out.println();
            }

        }while (!(opt == 0));
    }

    static void VerVooPassagem(ArquivoCsv file){
        // Mostra todas as opições de voos diponíveis

        System.out.println();
        System.out.println("***** Voos diponíveis *****");
        System.out.println("Código identificado / Saindo de / Chegando em / Duração / Preço");
        System.out.println();

        List<String> lista = file.LerLinhasArquivo();

        for(int i = 1; i < lista.size() ; i++ ){

            String[] array = file.ConverteStringParaArray(lista.get(i));

            System.out.println(array[0] + " / " + array[1] + " / " + array[2] + " / " + array[3] + " / " + array[4]);

        }
    }

    static boolean TemPassagem(int id, ArquivoCsv fileCliente){
        if(id == 0){
            return false;
        }

        int idPassagemCliente = fileCliente.ObterIdPassagemPeloId(id);

        if(idPassagemCliente == 0){
            return false;
        }
        return true;
    }

    static void PassagemMenu(ArquivoCsv filePassagem,ArquivoCsv fileCliente){
        int opcao = -1;
        do
        {
            MenuPassagem();
            try
            {
                Scanner sc = new Scanner(System.in);
                opcao = sc.nextInt();

            }
            catch(Exception e)
            {
                System.out.println();
                System.out.println("Atenção: Use apenas números e escolha apenas umas das opções dispíneveis");
                System.out.println();
            }

            switch (opcao)
            {
                // Ver passagem
                case 1:
                    VerPassagem(filePassagem, fileCliente);
                    break;

                // comprar passagem
                case 2:
                    ComprarPassagem(filePassagem,fileCliente);
                    break;

                // Trocar Passagem
                case 3:
                    TrocarPassagem(filePassagem,fileCliente);
                    break;

                // Cancelar Passagem
                case 4:
                    CancelarPassagem(filePassagem,fileCliente);
                    break;
                case 5:
                // Listar voos diponíveis
                    VerVooPassagem(filePassagem);
                    break;
                // Read all deck
                case 9:
                    break;


                default:
                    System.out.println();
                    System.out.println("Entrada inválida, Por favor escolha apenas as opções diponéveis");
                    System.out.println();
                    break;

            }

        }
        while (!(opcao == 9));
    }

    // Menu
    static void Main() {
        int opcao = -1;
        do
        {
            System.out.println();
            MenuBoasVindas();

            try
            {
                Scanner sc = new Scanner(System.in);
                opcao = sc.nextInt();
            }
            catch(Exception e)
            {
                System.out.println();
                System.out.println("Atenção: Use apenas números e escolha apenas umas das opções dispíneveis");
                System.out.println();
            }
            // Intâncias do arquivo csv
            ArquivoCsv csvCliente = new ArquivoCsv("TP1_Java","Cliente","csv","src\\main\\java\\data\\cliente.csv");
            ArquivoCsv csvPassagem = new ArquivoCsv("TP1_Java","Passagem","csv","src\\main\\java\\data\\passagem.csv");

            // Menu
            switch (opcao)
            {
                // Menu Cliente
                case 1:

                    ClienteMain(csvCliente);
                    break;

                // Menu Passagem
                case 2:
                    PassagemMenu(csvPassagem,csvCliente);
                    break;

                case 0:
                    System.out.println("Até mais!");
                    break;

                default:
                    System.out.println();
                    System.out.println("Entrada inválida, Por favor escolha apenas as opções diponéveis");
                    System.out.println();
                    break;

            }

        }
        while (!(opcao == 0));
    }


    public static void main(String[] args) {
        Main();
    }
}
