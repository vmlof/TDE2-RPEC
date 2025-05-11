class No23 {
    Integer chave1;
    Integer chave2;
    No23 esquerda;
    No23 meio;
    No23 direita;
    boolean ehFolha;

    public No23(int chave) {
        this.chave1 = chave;
        this.chave2 = null;
        this.esquerda = null;
        this.meio = null;
        this.direita = null;
        this.ehFolha = true;
    }

    public boolean ehNo2() {
        return chave2 == null;
    }

    public boolean ehNo3() {
        return chave2 != null;
    }
}

public class Arvore23 {
    private No23 raiz;

    public Arvore23() {
        raiz = null;
    }

    public void inserir(int chave) {
        if (raiz == null) {
            raiz = new No23(chave);
        } else {
            No23 novaRaiz = inserir(raiz, chave);
            if (novaRaiz != null) {
                raiz = novaRaiz;
            }
        }
    }

    private No23 inserir(No23 no, int chave) {
        if (no.ehFolha) {
            if (no.ehNo2()) {
                if (chave < no.chave1) {
                    no.chave2 = no.chave1;
                    no.chave1 = chave;
                } else {
                    no.chave2 = chave;
                }
                return null;
            } else {
                return dividirFolha(no, chave);
            }
        } else {
            No23 filho;
            No23 novoFilho = null;

            if (chave < no.chave1) {
                filho = no.esquerda;
                novoFilho = inserir(filho, chave);
            } else if (no.ehNo2() || chave < no.chave2) {
                filho = no.meio;
                novoFilho = inserir(filho, chave);
            } else {
                filho = no.direita;
                novoFilho = inserir(filho, chave);
            }

            if (novoFilho != null) {
                if (no.ehNo2()) {
                    if (novoFilho.chave1 < no.chave1) {
                        no.chave2 = no.chave1;
                        no.chave1 = novoFilho.chave1;
                        no.esquerda = novoFilho.esquerda;
                        no.meio = novoFilho.meio;
                        no.direita = no.meio;
                        no.meio = novoFilho.direita;
                    } else {
                        no.chave2 = novoFilho.chave1;
                        no.meio = novoFilho.esquerda;
                        no.direita = novoFilho.meio;
                    }
                    return null;
                } else {
                    return dividirInterno(no, novoFilho);
                }
            }
            return null;
        }
    }

    private No23 dividirFolha(No23 no, int chave) {
        int min, meio, max;

        if (chave < no.chave1) {
            min = chave;
            meio = no.chave1;
            max = no.chave2;
        } else if (chave < no.chave2) {
            min = no.chave1;
            meio = chave;
            max = no.chave2;
        } else {
            min = no.chave1;
            meio = no.chave2;
            max = chave;
        }

        No23 esquerda = new No23(min);
        No23 direita = new No23(max);

        No23 pai = new No23(meio);
        pai.esquerda = esquerda;
        pai.meio = direita;
        pai.ehFolha = false;

        return pai;
    }

    private No23 dividirInterno(No23 no, No23 novoFilho) {
        No23 esquerda, direita;
        int chavePromovida;

        if (novoFilho.chave1 < no.chave1) {
            chavePromovida = no.chave1;
            esquerda = new No23(novoFilho.chave1);
            esquerda.esquerda = novoFilho.esquerda;
            esquerda.meio = novoFilho.meio;

            direita = new No23(no.chave2);
            direita.esquerda = no.meio;
            direita.meio = no.direita;
        } else if (novoFilho.chave1 < no.chave2) {
            chavePromovida = novoFilho.chave1;
            esquerda = new No23(no.chave1);
            esquerda.esquerda = no.esquerda;
            esquerda.meio = novoFilho.esquerda;

            direita = new No23(no.chave2);
            direita.esquerda = novoFilho.meio;
            direita.meio = no.direita;
        } else {
            chavePromovida = no.chave2;
            esquerda = new No23(no.chave1);
            esquerda.esquerda = no.esquerda;
            esquerda.meio = no.meio;

            direita = new No23(novoFilho.chave1);
            direita.esquerda = novoFilho.esquerda;
            direita.meio = novoFilho.meio;
        }

        esquerda.ehFolha = false;
        direita.ehFolha = false;

        No23 pai = new No23(chavePromovida);
        pai.esquerda = esquerda;
        pai.meio = direita;
        pai.ehFolha = false;

        return pai;
    }

    public boolean buscar(int chave) {
        return buscar(raiz, chave);
    }

    private boolean buscar(No23 no, int chave) {
        if (no == null) return false;

        if (no.chave1 == chave || (no.chave2 != null && no.chave2 == chave)) {
            return true;
        }

        if (no.ehFolha) {
            return false;
        }

        if (chave < no.chave1) {
            return buscar(no.esquerda, chave);
        } else if (no.chave2 == null || chave < no.chave2) {
            return buscar(no.meio, chave);
        } else {
            return buscar(no.direita, chave);
        }
    }

    public void printInOrdem() {
        printInOrdem(raiz);
        System.out.println();
    }

    private void printInOrdem(No23 no) {
        if (no != null) {
            printInOrdem(no.esquerda);
            if (no.chave1 != null) System.out.print(no.chave1 + " ");
            printInOrdem(no.meio);
            if (no.chave2 != null) System.out.print(no.chave2 + " ");
            printInOrdem(no.direita);
        }
    }


    public void remover(int chave) {
        if (raiz == null) return;
        remover(null, raiz, chave);

        if (raiz.chave1 == null) {
            if (raiz.ehFolha) {
                raiz = null;
            } else {
                raiz = raiz.esquerda;
            }
        }
    }

    private void remover(No23 pai, No23 no, int chave) {
        if (no == null) return;

        if (no.ehFolha) {

            if (no.chave1 != null && no.chave1 == chave || (no.chave2 != null && no.chave2 == chave)) {
                if (no.ehNo3()) {

                    if (no.chave1 == chave) {
                        no.chave1 = no.chave2;
                    }
                    no.chave2 = null;
                } else {

                    no.chave1 = null;
                    rebalancear(pai, no);
                }
            }
        } else {

            if (no.chave1 != null && no.chave1 == chave) {

                No23 predecessor = encontrarPredecessor(no.esquerda);
                if (predecessor != null && predecessor.chave1 != null) {

                    int predecessorChave = (predecessor.chave2 != null) ? predecessor.chave2 : predecessor.chave1;
                    no.chave1 = predecessorChave;
                    remover(no, no.esquerda, predecessorChave);
                } else {

                    No23 sucessor = encontrarSucessor(no.meio);
                    if (sucessor != null && sucessor.chave1 != null) {
                        no.chave1 = sucessor.chave1;
                        remover(no, no.meio, sucessor.chave1);
                    }
                }
            } else if (no.chave2 != null && no.chave2 == chave) {

                No23 sucessor = encontrarSucessor(no.direita);
                if (sucessor != null && sucessor.chave1 != null) {
                    no.chave2 = sucessor.chave1;
                    remover(no, no.direita, sucessor.chave1);
                }
            } else {

                if (chave < no.chave1) {
                    remover(no, no.esquerda, chave);
                } else if (no.ehNo2() || chave < no.chave2) {
                    remover(no, no.meio, chave);
                } else {
                    remover(no, no.direita, chave);
                }
            }

            if (no.chave1 == null && !no.ehFolha) {
                rebalancear(pai, no);
            }
        }
    }

    private No23 encontrarPredecessor(No23 no) {
        if (no == null) return null;
        while (!no.ehFolha) {
            no = (no.direita != null) ? no.direita :
                    (no.meio != null) ? no.meio :
                            no.esquerda;
        }
        return (no.chave1 != null) ? no : null;
    }

    private No23 encontrarSucessor(No23 no) {
        if (no == null) return null;
        while (!no.ehFolha) {
            no = no.esquerda;
        }
        return (no.chave1 != null) ? no : null;
    }

    private void rebalancear(No23 pai, No23 no) {
        if (pai == null || no.chave1 != null) {
            return;
        }

        if (pai.ehNo3()) {
            if (pai.esquerda == no) {
                No23 irmao = pai.meio;
                if (irmao.ehNo3()) {

                    no.chave1 = pai.chave1;
                    pai.chave1 = irmao.chave1;
                    irmao.chave1 = irmao.chave2;
                    irmao.chave2 = null;

                    if (!no.ehFolha) {
                        no.meio = irmao.esquerda;
                        irmao.esquerda = irmao.meio;
                        irmao.meio = irmao.direita;
                        irmao.direita = null;
                    }
                } else {

                    no.chave1 = pai.chave1;
                    no.chave2 = irmao.chave1;
                    pai.chave1 = pai.chave2;
                    pai.chave2 = null;
                    pai.esquerda = no;
                    pai.meio = pai.direita;
                    pai.direita = null;

                    if (!no.ehFolha) {
                        no.meio = irmao.esquerda;
                        no.direita = irmao.meio;
                    }
                }
            } else if (pai.meio == no) {
                No23 irmaoEsq = pai.esquerda;
                No23 irmaoDir = pai.direita;

                if (irmaoEsq.ehNo3()) {

                    no.chave1 = pai.chave1;
                    pai.chave1 = irmaoEsq.chave2;
                    irmaoEsq.chave2 = null;

                    if (!no.ehFolha) {
                        no.meio = no.esquerda;
                        no.esquerda = irmaoEsq.direita;
                        irmaoEsq.direita = null;
                    }
                } else if (irmaoDir != null && irmaoDir.ehNo3()) {

                    no.chave1 = pai.chave2;
                    pai.chave2 = irmaoDir.chave1;
                    irmaoDir.chave1 = irmaoDir.chave2;
                    irmaoDir.chave2 = null;

                    if (!no.ehFolha) {
                        no.meio = irmaoDir.esquerda;
                        irmaoDir.esquerda = irmaoDir.meio;
                        irmaoDir.meio = irmaoDir.direita;
                        irmaoDir.direita = null;
                    }
                } else {

                    irmaoEsq.chave2 = pai.chave1;
                    pai.chave1 = pai.chave2;
                    pai.chave2 = null;
                    pai.meio = pai.direita;
                    pai.direita = null;

                    if (!no.ehFolha) {
                        irmaoEsq.direita = no.esquerda;
                    }
                }
            } else if (pai.direita == no) {
                No23 irmao = pai.meio;
                if (irmao.ehNo3()) {

                    no.chave1 = pai.chave2;
                    pai.chave2 = irmao.chave2;
                    irmao.chave2 = null;

                    if (!no.ehFolha) {
                        no.meio = no.esquerda;
                        no.esquerda = irmao.direita;
                        irmao.direita = null;
                    }
                } else {

                    irmao.chave2 = pai.chave2;
                    pai.chave2 = null;
                    pai.direita = null;

                    if (!no.ehFolha) {
                        irmao.direita = no.esquerda;
                    }
                }
            }
        } else {

            if (pai.esquerda == no) {
                No23 irmao = pai.meio;
                if (irmao.ehNo3()) {

                    no.chave1 = pai.chave1;
                    pai.chave1 = irmao.chave1;
                    irmao.chave1 = irmao.chave2;
                    irmao.chave2 = null;

                    if (!no.ehFolha) {
                        no.meio = irmao.esquerda;
                        irmao.esquerda = irmao.meio;
                        irmao.meio = irmao.direita;
                        irmao.direita = null;
                    }
                } else {

                    no.chave1 = pai.chave1;
                    no.chave2 = irmao.chave1;
                    pai.chave1 = null;
                    pai.meio = null;

                    if (!no.ehFolha) {
                        no.meio = irmao.esquerda;
                        no.direita = irmao.meio;
                    }
                }
            } else if (pai.meio == no) {
                No23 irmao = pai.esquerda;
                if (irmao.ehNo3()) {

                    no.chave1 = pai.chave1;
                    pai.chave1 = irmao.chave2;
                    irmao.chave2 = null;

                    if (!no.ehFolha) {
                        no.meio = no.esquerda;
                        no.esquerda = irmao.direita;
                        irmao.direita = null;
                    }
                } else {

                    irmao.chave2 = pai.chave1;
                    pai.chave1 = null;
                    pai.meio = null;

                    if (!no.ehFolha) {
                        irmao.direita = no.esquerda;
                    }
                }
            }
        }
    }
}
