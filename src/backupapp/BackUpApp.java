/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backupapp;

/**
 *
 * @author SARJIT
 */
public class BackUpApp {

    /**
     * @param args the command line arguments
     */
    static FolderDB data;
            
    
    public static void main(String[] args) {
        
        data = new FolderDB();
        //data.display();
        data.start();
    }   
    
    
}
