import java.util.Scanner;
public class PetLink {
    private static Scanner input = new Scanner(System.in);
    private static String[] animais = {
        "Luna (gato)",
        "Mia (gato)",
        "Mel (gato)",
        "Nina (gato)",
        "Simba (gato)",
        "Thor (cachorro)",
        "Bob (cachorro)",
        "Rex (cachorro)",
        "Max (cachorro)",
        "Lola (cachorro)"
    };

    public static void verAnimais(){
        limpar();
        
        // Painel de animais para a adoção
        System.out.println("====================================");
        System.out.println("    ANIMAIS ESPERANDO POR UM LAR    ");
        System.out.println("====================================");

        // Exibe os animais que estão na lista de adoção
        for (int i = 0; i < animais.length; i++){
            System.out.printf("%d. %s%n", i + 1, animais[i]);
        }

        System.out.print("Escolha um pet: ");
        int n = input.nextInt();
        System.out.printf("O animal escolhido foi %s%n", animais[n-1]);
        System.out.println("\nDigite algo para voltar ao menu:");
        input.next();
        
    }

    public static void limpar(){
        // Limpa o terminal sem fechar o código
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args){
        int escolha;
        do {
            limpar();
            // Menu principal com as opções para navegação
            System.out.println("============================");
            System.out.println("    SITE PET LINK - HOME    ");
            System.out.println("============================");
            System.out.println("1 - Ver animais disponiveis");
            System.out.println("2 - Cadastro / Login");
            System.out.println("3 - Doação");
            System.out.println("4 - Sair");
            System.out.println("============================");
            System.out.println("    SELECIONE UMA OPÇÃO     ");
            escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    verAnimais();
                    break;
                case 2:
                    System.out.println("Cadastro/Login");
                    break;
                case 3:
                    System.out.println("Doação");
                    break;
                case 4:
                    limpar();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Selecione uma opção válida!");
                    break;
            }

        } while (escolha != 4); 

        input.close();
    }
}
