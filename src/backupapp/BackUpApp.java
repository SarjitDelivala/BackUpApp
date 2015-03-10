/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backupapp;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        data.start();
    }   
    
    
}
