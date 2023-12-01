package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "client" );
        Socket s;
        BufferedReader tastiera;
        DataOutputStream out;
        BufferedReader in;
        ArrayList<String> listaNomi = new ArrayList();       
        String receive = "";
        try{

            s = new Socket("localhost",3000);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new DataOutputStream(s.getOutputStream());

            System.out.println("server pronto");

            receive = in.readLine();
            String[] nomi = receive.split(",");

            for(int i =0; i> nomi.length ; i++){

                listaNomi.add(nomi[i]);

            }
            

            Thread t = new clientMultiThread(out,listaNomi);
            t.start();




            while(true){

                receive = in.readLine();
                String[] messaggi = receive.split("||");
                String nomeUtente = messaggi[0];
                String testo = messaggi[1];
                

                if(nomeUtente.equals("b")){
                    System.out.println("\tmessaggio broadcast");
                    System.out.println(testo);

                } else if(listaNomi.contains(nomeUtente)){
                    System.out.println("\t" + nomeUtente);
                    System.out.println(testo);

                }else if(nomeUtente.equals("*")){

                    listaNomi.add(testo);

                }
                
                 else {
                    System.out.println("problema di ricezione");
                }



            }

           
        }catch(Exception e){

            System.out.println( "errore" + e.getMessage());

        }
        










    }
}
