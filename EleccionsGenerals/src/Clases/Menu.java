package Clases;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    static Scanner scan = new Scanner(System.in);
    public static void menu(Connection con) throws IOException {

        int resposta;

        System.out.print(  "╔═════════════════════════════════╗\n" +
                           "║ 1 - Importar dades a la BBDD    ║\n" +
                           "║ 2 - Realitzar sentencies CRUD   ║\n"+
                           "║ 3 - Sortir del programa         ║\n" +
                           "╚═════════════════════════════════╝\n" +
                           ">   ");

        resposta = scan.nextInt();

        while (resposta >= 4 || resposta <= 0){
            System.out.print("Introdueix un número vàlid");
            System.out.print(">   ");
            resposta = scan.nextInt();
        }

        switch (resposta) {
            case 1:
                Importacio.importacions();
                break;
            case 2:
                escollirTaula();
                break;
            case 3:
                System.out.println("Fins aviat!");
                System.exit(0);
                break;
        }
    }
    public static void escollirTaula() throws IOException {
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

        while (respostaTaula >= 7 || respostaTaula <= 0){
            System.out.print("Introdueix una taula vàlida");
            System.out.print(">   ");
            respostaTaula = scan.nextInt();
        }

        switch (respostaTaula) {
            case 1:
                escollirCRUDComunitatAutonoma();
                break;
            case 2:
                escollirCRUDProvincies();
                break;
            case 3:
                //escollirCRUDMunicipis();
                break;
            case 4:
                escollirCRUDPersones();
                break;
            case 5:
                escollirCRUDCandidats();
                break;
            case 6:
                escollirCRUDCandidatures();
                break;
        }
    }
    public static void printCRUD(){
        System.out.println("Quina sentencia vols realitzar sobre la taula?");
        System.out.print(  "╔═══════════════╗\n" +
                "║ 1 - CREATE    ║\n" +
                "║ 2 - READ      ║\n"+
                "║ 3 - UPDATE    ║\n" +
                "║ 4 - DELETE    ║\n" +
                "╚═══════════════╝\n" +
                ">   ");
    }
    public static void comprovarCRUD(int respostaCRUD){
        while (respostaCRUD >= 5 || respostaCRUD <= 0){
            System.out.print("Introdueix un número vàlid");
            System.out.print(">   ");
            respostaCRUD = scan.nextInt();
            scan.nextLine();
        }
    }
    public static void escollirCRUDComunitatAutonoma() {
        int id, respostaCRUD, codiINE;
        String nom;

        printCRUD();
        respostaCRUD = scan.nextInt();
        scan.nextLine();

        comprovarCRUD(respostaCRUD);

        switch (respostaCRUD) {
            case 1:
                System.out.print("Digues el id, Nom, CodiINE de la comunitat autonoma\n" +
                        ">   ");
                id = scan.nextInt();
                nom = scan.next();
                codiINE = scan.nextInt();
                scan.nextLine();

                ComunitatAutonoma.insertComunitatAutonoma(id , nom, codiINE);
                break;
            case 2:
                System.out.print("Digues el id de la comunitat autonoma que vols buscar\n" +
                        ">   ");
                id = scan.nextInt();
                ComunitatAutonoma.readComunitatAutonoma(id);
                break;
            case 3:
                System.out.print("Digues l'id de la comunitat autonoma que vols modificar seguit del nou nom i codiINE\n" +
                        ">   ");
                id = scan.nextInt();
                nom = scan.next().trim();
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
    public static void escollirCRUDProvincies(){
        int id, respostaCRUD, id_comunitat_autonoma, codi_ine,nom_escons;
        String nom;
        printCRUD();
        respostaCRUD = scan.nextInt();
        scan.nextLine();

        comprovarCRUD(respostaCRUD);
        switch (respostaCRUD){
            case 1:
                System.out.print("Digues el id, id_comunitat_autonoma, nom, codi_ine i nom_escons de la provincia\n" +
                        ">   ");
                id = scan.nextInt();
                id_comunitat_autonoma = scan.nextInt();
                nom = scan.next();
                codi_ine = scan.nextInt();
                nom_escons = scan.nextInt();
                scan.nextLine();

                Provincies.insert(id , id_comunitat_autonoma, nom, codi_ine, nom_escons);
                break;
            case 2:
                System.out.print("Digues el id de la provincia que vols buscar\n" +
                        ">   ");
                id = scan.nextInt();
                Provincies.read(id);
                break;
            case 3:
                System.out.print("Digues l'id de la provincia que vols modificar seguit del nou id_comunitat_autonoma, nom, codi_ine i nom_escons\n" +
                        ">   ");
                id = scan.nextInt();
                nom = scan.next().trim();
                Provincies.update(id , nom);
                break;
            case 4:
                System.out.print("Digues el id de la provincia que vols eliminar\n" +
                        ">   ");
                id = scan.nextInt();
                Provincies.delete(id);
                break;
        }
    }
    public static void escollirCRUDMunicipis(){

    }
    public static void escollirCRUDPersones(){
        int id ,respostaCRUD;
        String nom, cognom1, cognom2, sexe, DNI;
        Date data_naixement;
        printCRUD();
        respostaCRUD = scan.nextInt();
        scan.nextLine();

        comprovarCRUD(respostaCRUD);
        switch (respostaCRUD){
            case 1:
                System.out.print("Digues el id, nom, cognom1, cognom2, sexe, data_naixement i DNI de la persona\n" +
                        ">   ");
                id = scan.nextInt();
                nom = scan.next();
                cognom1 = scan.next();
                cognom2 = scan.next();
                sexe = scan.next();
                data_naixement = new Date();
                DNI = scan.next();
                scan.nextLine();

                Persones.insert(id,nom,cognom1,cognom2,sexe,data_naixement,DNI);
                break;
                case 2:
                    System.out.print("Digues el id de la persona que vols buscar\n" +
                            ">   ");
                    id = scan.nextInt();
                    Persones.read(id);
                    break;
                case 3:
                    System.out.print("Digues l'id de la persona que vols modificar seguit del nou nom, cognom1 i cognom2\n" +
                            ">   ");
                    id = scan.nextInt();
                    nom = scan.next().trim();
                    cognom1 = scan.next().trim();
                    cognom2 = scan.next().trim();
                    Persones.update(id,nom,cognom1,cognom2);
                    break;
                case 4:
                    System.out.print("Digues el id de la persona que vols eliminar\n" +
                            ">   ");
                    id = scan.nextInt();
                    Persones.delete(id);
                    break;
        }

    }
    public static void escollirCRUDCandidats(){
        int id, candidatura_id,persona_id ,provincia_id, num_ordre;
        String tipus;
        printCRUD();
        int respostaCRUD = scan.nextInt();
        scan.nextLine();

        comprovarCRUD(respostaCRUD);

        switch (respostaCRUD){
            case 1:
                System.out.print("Digues el id, persona_id, provincia_id, num_ordre i tipus del candidat\n" +
                        ">   ");
                id = scan.nextInt();
                candidatura_id = scan.nextInt();
                persona_id = scan.nextInt();
                provincia_id = scan.nextInt();
                num_ordre = scan.nextInt();
                tipus = scan.next().trim();
                scan.nextLine();

                //Candidats.insert(id,candidatura_id,persona_id,provincia_id,num_ordre,tipus);
                break;
            case 2:
                System.out.print("Digues el id del candidat que vols buscar\n" +
                        ">   ");
                id = scan.nextInt();
                //Candidats.read(id);
                break;
            case 3:
                System.out.print("Digues l'id del candidat que vols modificar seguit del nou persona_id, provincia_id, num_ordre i tipus\n" +
                        ">   ");
                id = scan.nextInt();
                provincia_id = scan.nextInt();

                persona_id = scan.nextInt();
                Candidats.update(id ,persona_id, provincia_id);

                break;
            case 4:
                System.out.print("Digues el id del candidat que vols eliminar\n" +
                        ">   ");
                id = scan.nextInt();
                //Candidats.delete(id);
        }
    }
    public static void escollirCRUDCandidatures() throws IOException {
        int id, eleccio_id;
        String codi_candidatura, nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulario_nacional;
        printCRUD();
        int respostaCRUD = scan.nextInt();
        scan.nextLine();
        comprovarCRUD(respostaCRUD);

        switch (respostaCRUD){
            case 1:
                System.out.print("Digues el id, eleccio_id, codi_candidatura, nom_curt, nom_llarg, codi_acumulacio_provincia, codi_acumulacio_ca i codi_acumulario_nacional de la candidatura\n" +
                        ">   ");
                id = scan.nextInt();
                eleccio_id = scan.nextInt();
                codi_candidatura = scan.next();
                nom_curt = scan.next();
                nom_llarg = scan.next();
                codi_acumulacio_provincia = scan.next();
                codi_acumulacio_ca = scan.next();
                codi_acumulario_nacional = scan.next();
                scan.nextLine();

                Candidatures.insert(id,eleccio_id,codi_candidatura,nom_curt,nom_llarg,codi_acumulacio_provincia,codi_acumulacio_ca,codi_acumulario_nacional);
                break;
            case 2:
                System.out.print("Digues el id de la candidatura que vols buscar\n" +
                        ">   ");
                id = scan.nextInt();
                Candidatures.read(id);
                break;
            case 3:
                System.out.print("Digues l'id de la candidatura que vols modificar seguit del nou nom_curt i nom_llarg\n" +
                        ">   ");
                id = scan.nextInt();
                nom_curt = scan.next().trim();
                nom_llarg = scan.next().trim();
                Candidatures.update(id,nom_curt,nom_llarg);
                break;
            case 4:
                System.out.print("Digues el id de la candidatura que vols eliminar\n" +
                        ">   ");
                id = scan.nextInt();
                Candidatures.delete(id);
        }
    }
}


