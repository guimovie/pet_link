import java.util.Scanner;
public class PetLink {
    private static Scanner input = new Scanner(System.in);

    private static String[] animais = new String[50];
    private static int quantidadeAnimais = 10;

    static {
        animais[0] = "Luna (gato)";
        animais[1] = "Mia (gato)";
        animais[2] = "Mel (gato)";
        animais[3] = "Nina (gato)";
        animais[4] = "Simba (gato)";
        animais[5] = "Thor (cachorro)";
        animais[6] = "Bob (cachorro)";
        animais[7] = "Rex (cachorro)";
        animais[8] = "Max (cachorro)";
        animais[9] = "Lola (cachorro)";
    }

    private static final String ADM_LOGIN = "adm123";
    private static final String ADM_SENHA = "senha123";
    private static String[] usuarioLogin = new String[20];
    private static String[] usuarioSenha = new String[20];
    private static boolean acessoADM = false;
    private static boolean acessoUsuario = false;
    private static String usuarioLogado;
    private static final int MAX_SOLICITACOES = 50;
    private static final int SOLICITACAO_PENDENTE = 0;
    private static final int SOLICITACAO_APROVADA = 1;
    private static final int SOLICITACAO_NEGADA = 2;
    private static String[] solicitacaoUsuario = new String[MAX_SOLICITACOES];
    private static String[] solicitacaoAnimal = new String[MAX_SOLICITACOES];
    private static int[] solicitacaoStatus = new int[MAX_SOLICITACOES];

    static {
        usuarioLogin[0] = "joão123";
        usuarioLogin[1] = "jose135";
        usuarioLogin[2] = "maria133";

        usuarioSenha[0] = "senha333";
        usuarioSenha[1] = "senha443";
        usuarioSenha[2] = "senha379";
    }

    public static void verAnimais(){
        limparTerminal();
        
        // Painel de animais para a adoção
        System.out.println("====================================");
        System.out.println("    ANIMAIS ESPERANDO POR UM LAR    ");
        System.out.println("====================================");

        if (quantidadeAnimais == 0) {
            System.out.println("Nenhum animal disponível no momento.");
            voltarMenu();
            return;
        }

        // Exibe os animais que estão na lista de adoção
        for (int i = 0; i < quantidadeAnimais; i++){
            System.out.printf("%d. %s%n", i + 1, animais[i]);
        }

        System.out.print("Escolha um pet: ");
        int n = input.nextInt();
        if (n < 1 || n > quantidadeAnimais) {
            System.out.println("Selecione um número válido!");
        } else {
            if (!acessoUsuario) {
                System.out.println("Faça login como usuário para solicitar um animal.");
            } else {
                solicitarAnimal(n - 1);
            }
        }
        voltarMenu();
    }

    private static void solicitarAnimal(int indiceAnimal) {
        String animal = animais[indiceAnimal];
        for (int i = 0; i < MAX_SOLICITACOES; i++) {
            if (solicitacaoStatus[i] == SOLICITACAO_PENDENTE
                    && animal.equals(solicitacaoAnimal[i])) {
                System.out.println("Já existe uma solicitação pendente para esse animal.");
                return;
            }
        }

        System.out.printf("Deseja solicitar %s? (1 - Sim, 2 - Não): ", animal);
        int confirmacao = input.nextInt();
        if (confirmacao != 1) {
            System.out.println("Solicitação cancelada.");
            return;
        }

        for (int i = 0; i < MAX_SOLICITACOES; i++) {
            if (solicitacaoUsuario[i] == null) {
                solicitacaoUsuario[i] = usuarioLogado;
                solicitacaoAnimal[i] = animal;
                solicitacaoStatus[i] = SOLICITACAO_PENDENTE;
                System.out.println("Solicitação enviada. Você poderá consultar o resultado no menu.");
                return;
            }
        }

        System.out.println("Não foi possível enviar: o limite de solicitações foi atingido.");
    }

    private static void minhasSolicitacoes() {
        if (!acessoUsuario) {
            System.out.println("Faça login para consultar suas solicitações.");
            voltarMenu();
            return;
        }
        limparTerminal();
        System.out.println("===============================");
        System.out.println("     MINHAS SOLICITAÇÕES       ");
        System.out.println("===============================");
        boolean encontrou = false;
        for (int i = 0; i < MAX_SOLICITACOES; i++) {
            if (usuarioLogado.equals(solicitacaoUsuario[i])) {
                String status;
                switch (solicitacaoStatus[i]) {
                    case SOLICITACAO_APROVADA:
                        status = "Aprovada";
                        break;
                    case SOLICITACAO_NEGADA:
                        status = "Negada";
                        break;
                    default:
                        status = "Pendente";
                        break;
                }
                System.out.printf("%s - %s%n", solicitacaoAnimal[i], status);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Você ainda não fez solicitações.");
        }
        voltarMenu();
    }

    public static void moduloAutenticacao(){
        // Tela de Login e Cadastro
        limparTerminal();
        System.out.println("==========================");
        System.out.println("       ÁREA DE ACESSO     ");
        System.out.println("==========================");
        System.out.println("1. Fazer Login");
        System.out.println("2. Realizar Cadastro");
        System.out.println("3. Voltar");
        System.out.println("==========================");
        System.out.println("   SELECIONE UMA OPÇÃO    ");
        int opcao = input.nextInt();
        
        switch (opcao) {
            case 1:
                login();
                break;
            case 2:
                cadastroUsuario();
                break;
            case 3:
                return;
            default:
                moduloAutenticacao();
                break;
        }

        if (acessoUsuario) {
            System.out.println("Sessão de usuário ativa.");
        }

        voltarMenu();
        
    }
    
    public static void login(){
        // Área de login
        limparTerminal();
        System.out.println("=================");
        System.out.println("      LOGIN      ");
        System.out.println("=================");
        System.out.print("Usuário: ");
        String loginInput = input.next(); 
        System.out.print("\nSenha: ");
        String senhaInput = input.next();

        acessoUsuario = false;
        acessoADM = false;
        usuarioLogado = null;

        // Compara as informações para ver se bate com perfil de administrador
        if (loginInput.trim().equals(ADM_LOGIN) && senhaInput.trim().equals(ADM_SENHA)) {
            acessoADM = true;
            System.out.println("Acesso concedido para administrador :)");
            menuAdministrador();
            return;
        }

        // Laço para comparar as informações com a lista de usuário
        for (int i = 0; i < usuarioLogin.length; i++) {
            if (usuarioLogin[i] != null && loginInput.trim().equals(usuarioLogin[i]) && senhaInput.trim().equals(usuarioSenha[i])) {
                acessoUsuario = true;
                usuarioLogado = usuarioLogin[i];
                System.out.println("Acesso concedido para usuário :)");
                return;
            }
        }

        System.out.println("Acesso Negado!");
    }

    private static void menuAdministrador() {
        // Caso o acesso seja de adiministrador, o menu de administrador é aberto
        while (acessoADM) {
            limparTerminal();
            System.out.println("===============================");
            System.out.println("     MENU DO ADMINISTRADOR     ");
            System.out.println("===============================");
            System.out.println("1. Adicionar animal");
            System.out.println("2. Retirar animal");
            System.out.println("3. Analisar solicitações de adoção");
            System.out.println("4. Sair da área administrativa");
            System.out.print("Escolha uma opção: ");
            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    adicionarAnimal();
                    break;
                case 2:
                    retirarAnimal();
                    break;
                case 3:
                    analisarSolicitacoes();
                    break;
                case 4:
                    acessoADM = false;
                    System.out.println("Você saiu da área administrativa.");
                    break;
                default:
                    System.out.println("Selecione uma opção válida!");
                    break;
            }

            if (acessoADM) {
                voltarMenu();
            }
        }
    }

    private static void adicionarAnimal() {
        if (!acessoADM) {
            System.out.println("Acesso negado. Apenas o administrador pode alterar a lista.");
            return;
        }

        input.nextLine();
        System.out.print("Digite o nome e a espécie do animal: ");
        String animal = input.nextLine().trim();

        if (animal.isEmpty()) {
            System.out.println("O nome do animal não pode ficar vazio.");
            return;
        }

        if (quantidadeAnimais == animais.length) {
            System.out.println("A lista de animais está cheia (limite de 50).");
            return;
        }

        animais[quantidadeAnimais] = animal;
        quantidadeAnimais++;
        System.out.println("Animal adicionado com sucesso!");
    }

    private static void retirarAnimal() {
        if (!acessoADM) {
            System.out.println("Acesso negado. Apenas o administrador pode alterar a lista.");
            return;
        }

        if (quantidadeAnimais == 0) {
            System.out.println("Não há animais para retirar.");
            return;
        }

        // Mostra os animais disponíveis varrendo a lista de animais com um laço for
        System.out.println("Animais disponíveis:");
        for (int i = 0; i < quantidadeAnimais; i++) {
            System.out.printf("%d. %s%n", i + 1, animais[i]);
        }

        System.out.print("Digite o número do animal que deseja retirar: ");
        int numero = input.nextInt();
        if (numero < 1 || numero > quantidadeAnimais) {
            System.out.println("Selecione um número válido!");
            return;
        }

        String animalSelecionado = animais[numero - 1];
        for (int i = 0; i < MAX_SOLICITACOES; i++) {
            if (solicitacaoStatus[i] == SOLICITACAO_PENDENTE
                    && animalSelecionado.equals(solicitacaoAnimal[i])) {
                System.out.println("Esse animal tem uma solicitação pendente. Analise-a antes de retirar o animal.");
                return;
            }
        }

        System.out.printf("Confirma a exclusão de %s? (1 - Sim, 2 - Não): ", animalSelecionado);
        int confirmacao = input.nextInt();
        if (confirmacao != 1) {
            System.out.println("Exclusão cancelada.");
            return;
        }

        String animalRemovido = animais[numero - 1];
        removerAnimalDaLista(numero - 1);
        System.out.printf("%s foi retirado da lista.%n", animalRemovido);
    }

    private static void analisarSolicitacoes() {
        if (!acessoADM) {
            System.out.println("Acesso negado. Apenas o administrador pode analisar solicitações.");
            return;
        }

        boolean encontrou = false;
        System.out.println("Solicitações pendentes:");
        for (int i = 0; i < MAX_SOLICITACOES; i++) {
            if (solicitacaoStatus[i] == SOLICITACAO_PENDENTE
                    && solicitacaoUsuario[i] != null) {
                System.out.printf("%d. %s solicitou %s%n",
                        i + 1, solicitacaoUsuario[i], solicitacaoAnimal[i]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Não há solicitações pendentes.");
            return;
        }

        System.out.print("Digite o número da solicitação para analisar (0 para voltar): ");
        int numero = input.nextInt();
        if (numero == 0) {
            return;
        }
        if (numero < 1 || numero > MAX_SOLICITACOES
                || solicitacaoStatus[numero - 1] != SOLICITACAO_PENDENTE
                || solicitacaoUsuario[numero - 1] == null) {
            System.out.println("Número de solicitação inválido.");
            return;
        }

        int indice = numero - 1;
        System.out.printf("%s solicitou %s.%n", solicitacaoUsuario[indice], solicitacaoAnimal[indice]);
        System.out.print("1. Aprovar  2. Negar: ");
        int decisao = input.nextInt();
        if (decisao == 1) {
            solicitacaoStatus[indice] = SOLICITACAO_APROVADA;
            removerAnimalDaLista(buscarAnimal(solicitacaoAnimal[indice]));
            System.out.println("Solicitação aprovada. O usuário poderá consultar o resultado.");
        } else if (decisao == 2) {
            solicitacaoStatus[indice] = SOLICITACAO_NEGADA;
            System.out.println("Solicitação negada. O usuário poderá consultar o resultado.");
        } else {
            System.out.println("Opção inválida. A solicitação continua pendente.");
        }
    }

    private static int buscarAnimal(String animal) {
        for (int i = 0; i < quantidadeAnimais; i++) {
            if (animal.equals(animais[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void removerAnimalDaLista(int indice) {
        if (indice < 0 || indice >= quantidadeAnimais) {
            return;
        }
        for (int i = indice; i < quantidadeAnimais - 1; i++) {
            animais[i] = animais[i + 1];
        }
        quantidadeAnimais--;
        animais[quantidadeAnimais] = null;
    }

    public static void cadastroUsuario(){
        limparTerminal();
        System.out.println("===============================");
        System.out.println("      CADASTRO DE USUÁRIO      ");
        System.out.println("===============================");
        System.out.print("Usuário: ");
        String cadastroUsuarioInput = input.next().trim();
        System.out.print("\nSenha: ");
        String cadastroSenhaInput = input.next().trim();

        // Verifica se o usuário coincide com o do administrador
        if (cadastroUsuarioInput.equals(ADM_LOGIN)) {
            System.out.println("Esse usuário já está cadastrado.");
            return;
        }

        for (int i = 0; i < usuarioLogin.length; i++){
            // Verifica dentro da lista de usuário se esse usuário já foi utilizado
            if (cadastroUsuarioInput.equals(usuarioLogin[i])) {
                System.out.println("Esse usuário já está cadastrado.");
                return;
            }

            // Coloca as informações de usuário e senha no proximo espaço vazio de cada lista
            if (usuarioLogin[i] == null && usuarioSenha[i] == null){
                usuarioLogin[i] = cadastroUsuarioInput;
                usuarioSenha[i] = cadastroSenhaInput;
                System.out.println("Cadastro realizado com sucesso :)");
                return;
            }

        }
    }

    public static void fazerDoacao(){
        limparTerminal();
        // Informações sobre doações para a ONG
        System.out.println("======================================");
        System.out.println("       COMO AJUDAR A ONG PATINHAS     ");
        System.out.println("======================================");
        System.out.println("Chave PIX Oficial: pix@ongpatinhas.org\n");
        System.out.println("""
                        ######  ##  ######
                        #    #  ##  #    #
                        ######  # # ######
                            ##
                        ### #  #### ##  ##
                        # #  ###  # #    #
                        ######  ##  ######
                """);
        System.out.println("TODA AJUDA É VÁLIDA!");
        voltarMenu();
        
    }

    
    public static void voltarMenu(){
        System.out.println("\nDigite algo para voltar ao menu:");
        input.next();

    }

    public static void limparTerminal(){
        // Limpa o terminal sem fechar o código
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args){
        int escolha;
        do {
            limparTerminal();
            // Menu principal com as opções para navegação
            System.out.println("============================");
            System.out.println("    SITE PET LINK - HOME    ");
            System.out.println("============================");
            System.out.println("1 - Ver animais disponiveis");
            System.out.println("2 - Cadastro / Login");
            System.out.println("3 - Minhas solicitações");
            System.out.println("4 - Doação");
            System.out.println("5 - Sair");
            System.out.println("============================");
            System.out.println("    SELECIONE UMA OPÇÃO     ");
            escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    verAnimais();
                    break;
                case 2:
                    moduloAutenticacao();;
                    break;
                case 3:
                    minhasSolicitacoes();
                    break;
                case 4:
                    fazerDoacao();
                    break;
                case 5:
                    limparTerminal();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Selecione uma opção válida!");
                    break;
            }

        } while (escolha != 5);

        input.close();
    }
}
