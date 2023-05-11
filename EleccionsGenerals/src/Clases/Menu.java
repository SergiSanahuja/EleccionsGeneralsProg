package Clases;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {
    static Scanner scan = new Scanner(System.in);
    public static void menu(Connection con){
        int resposta;

        System.out.print(  "╔═════════════════════════════════╗\n" +
                           "║ 1 - Importar dades a la BBDD    ║\n" +
                           "║ 2 - Realitzar sentencies CRUD   ║\n"+
                           "║ 3 - Sortir del programa         ║\n" +
                           "╚═════════════════════════════════╝\n" +
                           ">   ");

        resposta = scan.nextInt();

        while (resposta >= 4 || resposta <= 0){
            System.out.println("Introdueix un número vàlid");
            System.out.print(">   ");
            resposta = scan.nextInt();
        }

        switch (resposta) {
            case 1:
                Importacio.importacions(con);
                break;
            case 2:
                escollirTaula();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
    public static void escollirTaula(){
        int respostaTaula;

        System.out.println("Sobre quina taula vols realitzar les sentencies CRUD?");
        System.out.print("╔═════════════════════════════════╗\n" +
                         "║ 1 - Comunitats autonomes        ║\n" +
                         "║ 2 - Provincies                  ║\n"+
                         "║ 3 - Municipis                   ║\n" +
                         "║ 4 - Persones                    ║\n" +
                         "║ 5 - Candidats                   ║\n" +
                         "║ 6 - Candidatures                ║\n" +
                         "╚═════════════════════════════════╝\n" +
                         ">   ");
        respostaTaula = scan.nextInt();

        while (respostaTaula >= 4 || respostaTaula <= 0){
            System.out.println("Introdueix una taula vàlida");
            System.out.print(">   ");
            respostaTaula = scan.nextInt();
        }

        switch (respostaTaula) {
            case 1:
                escollirCRUDComunitatAutonoma();
                break;
            case 2:

        }
    }
    public static void escollirCRUDComunitatAutonoma() {
        int id, respostaCRUD;
        String nom;
        int codiINE;
        System.out.println("Quina sentencia vols realitzar sobre la taula?");
        System.out.print(  "╔═══════════════╗\n" +
                           "║ 1 - CREATE    ║\n" +
                           "║ 2 - READ      ║\n"+
                           "║ 3 - UPDATE    ║\n" +
                           "║ 4 - DELETE    ║\n" +
                           "╚═══════════════╝\n" +
                           ">   ");
        respostaCRUD = scan.nextInt();

        while (respostaCRUD >= 5 || respostaCRUD <= 0){
            System.out.println("Introdueix un número vàlid");
            System.out.print(">   ");
            respostaCRUD = scan.nextInt();
        }

        switch (respostaCRUD) {
            case 1:
                System.out.print("Digues el id, Nom, CodiINE de la comunitat autonoma\n" +
                        ">   ");
                id = scan.nextInt();
                nom = scan.nextLine().trim();
                codiINE = scan.nextInt();

                ComunitatAutonoma.insertComunitatAutonoma(id , nom, codiINE);
                break;
            case 2:
                System.out.println("Digues el id de la comunitat autonoma que vols buscar\n" +
                        ">   ");
                id = scan.nextInt();
                ComunitatAutonoma.readComunitatAutonoma(id);
                break;
            case 3:
                System.out.print("Digues l'id de la comunitat autonoma que vols modificar seguit del nou nom i codiINE\n" +
                        ">   ");
                id = scan.nextInt();
                nom = scan.nextLine().trim();
                codiINE = scan.nextInt();
                ComunitatAutonoma.updateComunitatAutonoma(id , nom, codiINE);
                break;
            case 4:
                System.out.print("Digues el id de la comunitat autonoma que vols eliminar\n" +
                        ">   ");
                id = scan.nextInt();
                ComunitatAutonoma.deleteComunitatAutonoma(id);
                break;
        }
    }
}


