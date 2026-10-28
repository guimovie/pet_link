import java.util.Scanner;
public class PetLink {
    private static Scanner input = new Scanner(System.in);

    public static void verAnimais(){
        
    }
    public static void main(String[] args){
        System.out.println("============================");
        System.out.println("    SITE PET LINK - HOME    ");
        System.out.println("============================");
        System.out.println("1 - Ver animais disponiveis");
        System.out.println("2 - Cadastro / Login");
        System.out.println("3 - Doação");
        System.out.println("4 - Sair");
        System.out.println("============================");
        System.out.println("    SELECIONE UMA OPÇÃO     ");
        int escolha = input.nextInt();

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
                System.out.println("Sair");
                break;
            default:
                System.out.println("Selecione uma opção válida");
                break;
        }

        
        input.close();
    }
}
