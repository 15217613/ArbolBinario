package arbol;

public class Principal {
    public static void main(String[] args) {
        Arbol<Integer> arbol = new Arbol<>();
        
        arbol.add(8); // Root
        
        // Pruebas para agregar datos
        arbol.add(4);
        arbol.add(arbol.getRoot(), 10);
        arbol.add(arbol.getRoot(), 12);
        arbol.add(1);
        //arbol.add(9);
        //arbol.add(20);
        
        System.out.println(arbol);
        //arbol.show(arbol.getRoot());
        
        // Pruebas para eliminar datos
        /*
        Nodo<Integer> t4 = arbol.add(4); // Hijo izquierdo
        arbol.add(arbol.getRoot(), 10);
        arbol.add(arbol.getRoot(), 12);
        arbol.add(1);
        arbol.add(0);
        arbol.add(2);
        
        Nodo<Integer> t = arbol.add(9); // Hoja
        arbol.add(20);
        arbol.add(30);
        
        Nodo<Integer> t2 = arbol.add(14); // Dos nodos
        arbol.add(16);
        arbol.add(15);
        
        Nodo<Integer> t3 = arbol.add(13); // Hoja
        arbol.remove(t);
        arbol.remove(t2);
        
        arbol.remove(t3);
        arbol.remove(t4);
        
        arbol.show(arbol.getRoot());
        */
        
        // Pruebas para buscar
        /*
        Nodo<Integer> t4 = arbol.add(4); // Hijo izquierdo
        arbol.add(arbol.getRoot(), 10);
        arbol.add(arbol.getRoot(), 12);
        arbol.add(1);
        arbol.add(0);
        arbol.add(2);
        
        Nodo<Integer> t = arbol.add(9); // Hoja
        arbol.add(20);
        arbol.add(30);
        
        Nodo<Integer> t2 = arbol.add(14); // Dos nodos
        arbol.add(16);
        arbol.add(15);
        
        Nodo<Integer> t3 = arbol.add(13); // Hoja
        
        arbol.inorder(arbol.getRoot());
        System.out.println("");
        
        Nodo<Integer> nodo = arbol.getNodo(arbol.getRoot(), 12);
        
        if (nodo != null) { // Si el nodo existe
            System.out.println("Nodo: " + nodo.getElement());
        }
        
        Integer element = arbol.getElement(nodo, 90);
        System.out.println("Elemento: " + element);
        */
        
        // Pruebas para mostrar la estructura jerarquica
        /*
        Nodo<Integer> t4 = arbol.add(4); // Hijo izquierdo
        arbol.add(arbol.getRoot(), 10);
        arbol.add(arbol.getRoot(), 12);
        arbol.add(1);
        arbol.add(0);
        arbol.add(2);
        
        Nodo<Integer> t = arbol.add(9); // Hoja
        arbol.add(20);
        arbol.add(30);
        
        Nodo<Integer> t2 = arbol.add(14); // Dos nodos
        arbol.add(16);
        arbol.add(15);
        
        Nodo<Integer> t3 = arbol.add(13); // Hoja
        arbol.show(arbol.getRoot());
        */
    }
}
