public static void main(String[] args) {
    ArrayList lista = new ArrayList();

    // Adicionando 20 elementos para forçar o redimensionamento
    for (int i = 1; i <= 20; i++) {
        lista.add(i);
    }
    System.out.println("Tamanho após add: " + lista.size()); // 20

    // Removendo quase tudo para ver o encolhimento
    for (int i = 0; i < 18; i++) {
        lista.remove(0);
    }
    System.out.println("Tamanho após remove: " + lista.size()); // 2
    System.out.println("Lista final: " + lista);
}