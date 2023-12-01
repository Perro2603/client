package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class clientMultiThread extends Thread{
 
    Scanner tastiera = new Scanner(System.in);
    DataOutputStream out = null;
    ArrayList<String> listaNome;

    public clientMultiThread( DataOutputStream out, ArrayList<String> listaNome ){

        this.out = out;
        this.listaNome = listaNome;
        
    }

    public void run(){



            try {

                System.out.println("inserisci il tuo nome");
                String outString = tastiera.nextLine();
                out.writeBytes(outString+"\n");
                

            while(true){
                
            
                
                
                outString = "";
                
                System.out.println("visualizza utenti, per i messaggi brodcast inserisci broadcast, esc per chiudere\n");

                for(int i = 0; i< listaNome.size() ;i++){
                    System.out.println(listaNome.get(i) + "\n");
        
                }

            
                    
                System.out.println("inserisci destinatario\n");                    
                outString = tastiera.nextLine();

                if (outString.equals("esc")) {
                    
                    out.writeBytes("#\n");
                    break;

                }else if(listaNome.size() > 1){
                    
                    outString = outString + "||";
                    System.out.println("inserisci messaggio\n");
                    outString = outString + tastiera.nextLine();
                    out.writeBytes(outString+"\n");

                }
                else{

                    System.out.println("errore, sei l'unico utente");

                }
                    

            }

        this.interrupt();

           
        } catch (IOException e) {
                
            e.printStackTrace();
        }




        






    }


}