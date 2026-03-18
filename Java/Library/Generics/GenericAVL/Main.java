package AVL;

import java.util.*;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            AVL<Integer> avl = new AVL<Integer>();

            int k = sc.nextInt();
            for (int j = 0; j < k; j++) {
                int data = sc.nextInt();
                avl.add(data);
            }
            System.out.printf("Case %d:\n", i + 1);
            System.out.println("Pre.: " + printar(avl.preOrder()));
            System.out.println("In..: " + printar(avl.inOrder()));
            System.out.println("Post: " + printar(avl.postOrder()));
            if (i != n - 1) {
                System.out.println();
            }

        }
        sc.close();
    }
    private static String printar(List<Integer> list) {
        StringJoiner sj = new StringJoiner(" ");
        for(Integer i : list) {
            sj.add(i.toString());
        }
        return sj.toString();
    }
}