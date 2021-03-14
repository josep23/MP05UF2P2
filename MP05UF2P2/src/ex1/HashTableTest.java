package ex1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashTableTest {
    HashTable hashTable = new HashTable();
    @Test
    void put() {


        /*
         Posible error
	     Probando cosas sin querer se me olvido rellenar un string y aun asi lo añadio osea lo deje simplemente asi: "" y para sorpresa lo añadio en el bucket[0]
	     con la clave "" entonces esta rellenada pero sin ver nada pero no es !null por lo que funciona el put().
	     osea que la clave no puede ser null pero el
        */
        hashTable.put("0", "");
        // He puesto de numero AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        //y peta me sale error java.lang.ArrayIndexOutOfBoundsException: Index -15 out of bounds for length 16
        hashTable.put("1",  "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        hashTable.put("2",  "2");
        hashTable.put("3",  "3");
        hashTable.put("4",  "4");
        // si no pones clave sobrescribe el string al primero en este caso al hashTable.put("0", ""); quedando "[, 5]"
        hashTable.put("",  "5");
        hashTable.put("6",  "6");
        hashTable.put("7",  "7");
        hashTable.put("8",  "8");
        hashTable.put("9",  "9");


        /*
         Pues he estado probando y tenia un video abierto de como cambiar la fuente del juego overwatch
          y me a salido que se cambia con el mapa de caracteres y se me ha ocurrido meter caracteres random haber que pasa y sorprendentemente los acepta he puesto esto "۞ꭊ ﺺ﷼﴾﴿֎֍۝ݿ₿☼☻☺♫♪♦♥♣♠♀♂◙ꝂꝎﻺꜻꝡꬲꟿﬕ"
          no se porque algunos aparecen como \u06DD pero luego si se ve esto:"۝"

           Porcierto si el "10" lo cambias por "۞ꭊ۩ﺺ﷼﴾﴿֎֍۝ݿ₿☼☻☺♫♪♦♥♣♠♀♂◙ꝂꝎﻺꜻꝡꬲꟿﬕ"
           sale el error java.lang.ArrayIndexOutOfBoundsException: Index -4 out of bounds for length 16
         */

        hashTable.put("10", "۞ꭊ۩ﺺ﷼﴾﴿֎֍\u06DDݿ₿☼☻☺♫♪♦♥♣♠♀♂◙ꝂꝎﻺꜻꝡꬲꟿﬕ");


        //he probado lo que dijo fer en el video de la clase de intentar sobrescrivir y lo que hace es lo siguiente:
        //no sobreescribe elementos con la misma clave, simplemente crea una colisión entre ellos, si en vez de usar put() usaramos drop() eliminaria ambos
        hashTable.put("11","Original");
        System.out.println(hashTable.toString());

        hashTable.put("11", "SobreEscribir");
        System.out.println(hashTable.toString());
    }
    @Test
    void get() {


        //Si hacemos get() en un objeto que sea null o hayamos borrado nos devolvera null, si es un objeto vacio no devuelve nada.
        hashTable.put("1", "1");
        hashTable.put("2", "2");
        hashTable.put("3", "");
        hashTable.put("4", null);
        hashTable.put("5", "۞ꭊ۩ﺺ﷼﴾﴿֎֍\u06DDݿ₿☼☻☺♫♪♦♥♣♠♀♂◙ꝂꝎﻺꜻꝡꬲꟿﬕ");
        System.out.println(hashTable.toString());

        //get() funciona aunque esten en colision asi que podemos coger todos los hastable indiferenetemente de la posicion.
       Assertions.assertEquals("1", hashTable.get("1"));
       Assertions.assertEquals("2", hashTable.get("2"));
       Assertions.assertEquals("", hashTable.get("3"));
        //get() también funciona si el valor de un elemento es nulo.
       Assertions.assertEquals(null, hashTable.get("4"));
       //Tambien funciona con simbolos "raros"
       Assertions.assertEquals("۞ꭊ۩ﺺ﷼﴾﴿֎֍\u06DDݿ₿☼☻☺♫♪♦♥♣♠♀♂◙ꝂꝎﻺꜻꝡꬲꟿﬕ", hashTable.get("5"));

    }
    @Test
    void drop() {

        hashTable.put("1", null);
        hashTable.put("2", "۞ꭊ۩ﺺ﷼﴾﴿֎֍\u06DDݿ₿☼☻☺♫♪♦♥♣♠♀♂◙ꝂꝎﻺꜻꝡꬲꟿﬕ");
        hashTable.put("3", "3");
        hashTable.put("4", "4");
        hashTable.put("5", "5");
        hashTable.put("6", "");
        hashTable.put("7", "7");
        hashTable.put("8", "8");

        System.out.println(hashTable.toString());

        //Si borras el primer elemento en el que hay colision se borra el bucket entero en vez del primer elemento.
        hashTable.drop("1");
        //no hay problema en dropear un elemento que sea "۞ꭊ۩ﺺ﷼﴾﴿֎֍۝ݿ₿☼☻☺♫♪♦♥♣♠♀♂◙ꝂꝎﻺꜻꝡꬲꟿﬕ"
        hashTable.drop("2");
        //Pero si borramos un elemento del medio solo borra ese elemento next.prev = next.prev.prev / prev.next = prev.next.next.
        hashTable.drop("5");
        //No hay problema en dropear un elemento vacio no null.
        hashTable.drop("6");
        //Si borras el ultimo de la colision, solo se borra ese elemento prev.next = null
        hashTable.drop("7");
        //No nos da ningún error al eliminar un elemento ya borrado indiferentemente de la posicion
        hashTable.drop("7");
        //Y tampoco tenemos problemas al borrar el último elemento de la HashTable.
        hashTable.drop("8");
        System.out.println(hashTable.toString());

    }
    @Test
    void count() {

        //Modificaciones he puesto un ITEMS++; y un ITEMS--; ya que siempre estaba en 0 indiferentemente de que entrara o saliera.

        //Si añadimos un count sin añadir al hashtable se queda en 0
        Assertions.assertEquals(hashTable.count(), 0);
        Assertions.assertEquals(0, hashTable.count());

        //si añadimos uno junto al hashtable se añadira 1 al anterior es decir se queda en 1
        hashTable.put("1","1");
        Assertions.assertEquals(hashTable.count(), 1);
        Assertions.assertEquals(1, hashTable.count());

        //si añadimos un segundo ITEM suma el anterior sin problema
        hashTable.put("2","2");
        Assertions.assertEquals(hashTable.count(), 2);
        Assertions.assertEquals(2, hashTable.count());
        System.out.println(hashTable.count());


        // si añadimos otro mas es decir count=3 sigue aumentando ya que ve que el count es 2 y le añade otro mas es decir 3.
        hashTable.put("3","3");
        Assertions.assertEquals(hashTable.count(), 3);
        Assertions.assertEquals(3, hashTable.count());

        //si borramos uno haciendo un drop el count baja 1 por cada drop ejecutado por lo que ahora mismo a pasado a 2.
        hashTable.drop("1");
        Assertions.assertEquals(hashTable.count(), 2);
        System.out.println(hashTable.count());


    }
    @Test
    void Size() {
        //No se me ha ocurrido ninguna prueba que hacerle ya que es una variable que no se "mueve" me refiero a que es estatico siempre es 16
        Assertions.assertEquals(hashTable.size(), 16);

    }
}
