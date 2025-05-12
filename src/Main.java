public class Main {
    public static void main(String[] args) {
        Arvore23 arvore = new Arvore23();


        int[] valores = {10, 20, 5, 6, 12, 30, 7, 17};
        int tamanhoInsercao = 8;
        System.out.println("==== INSERÇÕES ====");
        for (int i = 0; i < tamanhoInsercao; i++) {
            arvore.inserir(valores[i]);
            System.out.println("Inserindo " + valores[i] + ": ");
            arvore.printInOrdem();
        }


        System.out.println("\n==== BUSCAS ====");
        int[] buscas = {5, 10, 15, 30};
        int tamanhoBusca = 4;

        for (int j = 0; j < tamanhoBusca; j++) {
            System.out.println("Buscar " + buscas[j] + ": " + (arvore.buscar(buscas[j]) ? "Encontrado" : "Não encontrado"));
        }


        System.out.println("\n==== REMOÇÕES ====");
        int[] remover = {6, 10, 12};
        int tamanhoRemocao = 3;
        for (int k = 0; k < tamanhoRemocao; k++) {
            System.out.println("Removendo " + remover[k] + ":");
            arvore.remover(remover[k]);
            arvore.printInOrdem();
        }
    }
}
