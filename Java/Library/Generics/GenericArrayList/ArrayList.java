// Antes de começar, vou definir alguns pontos, no Array List há dois pontos cruciais
// que vou separar aqui:
// 1 - Array ou Capacidade : quando digo Array aqui me refiro à estrutura de dados, pois
// o array por si só não é a lista que vamos usar e sim onde vamos jogar ela, que é um
// bloco da memória alocado sequencialmente -> [ , , , , , ].
// 2 - Lista abstrata : quando digo Lista abstrata me refiro a lista em si que vamos
// armazenar no nosso array, se eu quero armazenar uma lista tipo -> 1, 2, 3, 4, 5
// isso por si só NÃO é um array, e sim a lista que criamos na cabeça
// nos abstraindo do computador e que agora queremos adicionar na estrutura de dados (array)

// Ideia Principal: O princípio do Array List, que é um tipo abstrato de dado assim como a lista
// estática, é que quando minha lista abstrata crescer, a minha estrutura de dados (array)
// se adapte para receber os novos dados e manter a minha lista abstrata da forma como a
// descrevi, por exemplo 1, 2, 3, 4, 5.

// Por baixo dos panos o java apaga os tipos no final (Type Erasure) e transforma tudo em Object, então
// porque usar Generics afinal? Pois Generics é sobre segurança e conveniência
// para quem usa a sua classe, não para a classe em si.
// Se alguém for usar seu ArrayList e tiver como Object ao invés do Generics, na hora de usar um get, iria
// ser obrigatório fazer um casting para o tipo de dado que o usuário quer no momento, com generics isso
// não é necessário.

public class ArrayList<T> {
    private Object array[]; // Aqui colocaremos como Object o tipo do nosso array.

    // O array list é uma junção da lógica da lista estática com uma lógica de
    // redimensionar o array, o tamanho que eu coloquei nessa variável é o tamanho
    // da minha lista abstrata, dos dados que estou armazenando, ele diminui e cresce
    // conforme minha lista necessita ao longo do código, ou seja, conforme o TAD
    // vai funcionando no código.

    private int tamanho;

    // o array list sempre vai começar com um tamanho padrão, geralmente colocam 10
    // ai ele vai crescendo conforme sua necessidade.
    private int capacidadePadrao = 10;

    public ArrayList() {
        // criamos o array como Object, mas não confunda, isso não fará com que precise de casting ao usar
        // o get, pois o tipo já vai estar definido quando instanciar essa classe por conta do Generics.

        this.array = new Object[capacidadePadrao];
        this.tamanho = 0;
    }

    // No add vamos receber como parâmetro um valor do tipo T (generics) que será decidido na hora que
    // for criado o Array List pelo usuário, por isso fazíamos ArrayList<Integer> arraylist = new ArrayList<>();
    // em POO, e esse valor do tipo T que vai ser jogado no array.

    public void add(T valor) {
        if (tamanho == this.array.length) {
            // se o array ficar todo ocupado, ele deve se redimensionar.
            redimensionar();
        }

        // se não for necessário, ele apenas joga o valor na última posição da minha lista
        // abstrata que quero manter, ou seja, se tenho [A, B, C, D, null, null]
        // e quero adicionar E, ele fica [A, B, C, D, E, null];

        this.array[this.tamanho] = valor;
        this.tamanho++;
    }

    public void remove(int indice) {

        // Esse é o tratamento de erro que é importante colocar, o índice tem que estar
        // dentro dos limites da nossa lista abstrata,
        // se minha lista abstrata agora está com 10 espaços usados eu não posso
        // pedir para remover um elemento do espaço 11.
        // o >= this.tamanho está ai porque o tamanho está sendo contado de 1, 2 ,3 ,4...
        // em diante, enquanto o array conta a partir do 0.


        if (indice < 0 || indice >= this.tamanho) {
            throw new IndexOutOfBoundsException("(!) Indice fora dos limites (!)");
        }

        // após remover um elemento da minha lista abstrata, eu tenho que reajustar
        // meu Array, para não ficar algo como [A, B, C, D] removendo C -> [A, B, null, D]
        // além de ficar feio dá problema nos dados, então como fazemos na lista estática
        // vamos "arrastar" a nossa lista abstrata para preencher os espaços vazios que
        // podem ficar no meio da nossa lista, assim concertando os "furos" da nossa lista.
        // para fazer isso é simples, é só começar um loop pelo índice que você removeu
        // e percorrer a lista jogando cada elemento que estiver "à frente" para "trás".
        // Ex: Se tenho [A, B, C, D, E] e removi o C, fica [A, B, null, D, E], entao eu
        // vou na posicao 2 onde está vazio e igualo array[2] = array[3], ficando
        // [A, B, D, null, E], repetindo o processo, chegaremos em [A, B, D, E, null].

        for (int i = indice; i < this.tamanho - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        // como a nossa lista abstrata foi reduzida, vamos atribuir null ou zero à ultima
        // posição que nossa lista abstrata estava ocupando anteriormente no nosso array
        // e depois decrementando o nosso contador do tamanho da lista abstrata, usei o zero
        // porque esse arraylist é só de inteiros para facilitar, mas se fosse para receber
        // objetos teria que colocar null.

        // Aqui ao invés de jogar 0 vamos jogar null pois agora vamos trabalhar com qualquer Objeto.
        this.array[this.tamanho - 1] = null;
        this.tamanho--;
        encolher();
    }

    // Aqui é o coração do array , quando for necessário que ele fique maior,
    // o grande truque é criar um array maior (geralmente o dobro do array anterior)
    // e jogar todos os elementos que estavam no meu array antigo e jogar no meu novo
    // array, mantendo minha lista abstrata.

    public void redimensionar() {

        int novaCapacidade = this.array.length * 2; // O tamanho do novo array que vamos criar
        Object novoArray[] = new Object[novaCapacidade];

        for (int i = 0; i < this.array.length; i++) {
            novoArray[i] = this.array[i];
        }
        // Aqui uma etapa sutil mas fundamental, estamos trocando as referências (ponteiros)
        // ou seja, estamos chamando o novoArray de array pois ele será nosso array original
        // a partir de agora, lembre-se que a variável aqui é uma referência, ela apenas nomeia
        // algo, por baixo dos panos estamos passando o endereço de memória do nosso novo array
        // para a referência guarda o endereço do nosso antigo array, com o tamanho antigo,
        // ou seja, agora a variável "array" vai apontar para o novo array que criamos.

        this.array = novoArray;
    }

    // Assim como ele cresce, ele pode encolher também, pois imagine a situação em que eu tenho
    // 1000 elementos de uma lista abstrata no meu array e agora eu tiro 900, ficando apenas 100
    // mas na memória ainda fica 1000 espaços reservados, então para resolver isso, podemos ter o
    // "inverso" de crescer, o encolher! Funciona da mesma forma que o método que faz a capacidade
    // da nossa estrutura de dados aumentar, porém ele verifica se o arrayé maior que a capacidade padrão
    // para não reduzir o array para menos que 10, e verficia se o tamanho da nossa lista abstrata, ou seja
    // do que estamos armazenando no nosso array, é menor ou igual a um quarto do tamanho do array que temos,
    // se essas condições forem satisfeitas, criamos uma capacidade nova de metade do array original e criamos
    // um novo array com essa capacidade e então fazemos o mesmo algoritmo para "jogar" os elementos do array
    // original para o novo array criado, e claro, após isso tudo, a variável "array" que criamos vai apontar
    // para o novo Array, ou seja, uma troca de endereços de memória.

    // Porque reduzir em 25% (1/4) e não 50% (1/2)? Imagine um array com capacidade 100 e com 50 elementos
    // de uma lista abstrata, se você remover 1 elemento e ele encolher para 50 imediatamente (regra do 50%),
    // se você decidir adicionar 1 novo elemento logo depois, ele vai crescer de novo,
    // ou seja, dobrar de tamanho voltando à capacidade 100 novamente, o que é custoso para o computador e
    // ineficiente. Ao encolher apenas em 25%, você garante que, mesmo após encolher o array pela metade,
    // ainda sobrará um espaço vazio (folga) para novas inserções antes de precisar
    // redimensionar para cima novamente.

    private void encolher() {
        if (this.array.length > capacidadePadrao && this.tamanho <= this.array.length / 4) {
            int novaCapacidade = this.array.length / 2;
            Object novoArray[] = new Object[novaCapacidade];

            for (int i = 0; i < tamanho; i++) {
                novoArray[i] = this.array[i];
            }
            this.array = novoArray;
        }
    }
    // O compilador Java geralmente mostra um aviso (Warning) de "Unchecked cast".
    // Para deixar o código limpo de avisos e profissional, vamos adicionar uma anotação sobre o método
    // para avisar ao compilador que sabemos que o cast é seguro porque controlamos a entrada no método add.
    
    @SuppressWarnings("unchecked")
    public T get(int indice) {

        // a mesma verificação que fizemos antes.

        if (indice < 0 || indice >= this.tamanho) {
            throw new IndexOutOfBoundsException("(!) Indice fora dos limites (!)");
        }
        return (T) this.array[indice];
    }
    // O size nesse caso serve para pegar o tamanho da nossa lista abstrata.

    public int size() {
        return this.tamanho;
    }

    @Override
    public String toString() {
        if (tamanho == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < tamanho; i++) {
            sb.append(array[i]);

            if (i < tamanho - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}