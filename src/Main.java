public class Main {
    public static void main(String[] args) {
        Arvore23 arvore = new Arvore23();


        int[] valores = {10, 20, 5, 6, 12, 30, 7, 17};
        System.out.println("==== INSERÇÕES ====");
        for (int valor : valores) {
            arvore.inserir(valor);
            System.out.println("Inserindo " + valor + ": ");
            arvore.printInOrdem();
        }


        System.out.println("\n==== BUSCAS ====");
        int[] buscas = {5, 10, 15, 30};
        for (int valor : buscas) {
            System.out.println("Buscar " + valor + ": " + (arvore.buscar(valor) ? "Encontrado" : "Não encontrado"));
        }


        System.out.println("\n==== REMOÇÕES ====");
        int[] remover = {6, 10, 12};
        for (int valor : remover) {
            System.out.println("Removendo " + valor + ":");
            arvore.remover(valor);
            arvore.printInOrdem();
        }
    }
}
