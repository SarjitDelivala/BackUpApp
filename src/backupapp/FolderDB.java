/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backupapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author SARJIT
 */
public class FolderDB {
    private HashMap<String,String> folders;
    private File f;
    FileInputStream fileRead;
    ObjectInputStream readFolders;
    ObjectOutputStream writeFolders;
    
    public void display()
    {
        Set<String> src = folders.keySet();
        
        for (Iterator<String> iterator = src.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            System.out.println(next);
        }
    }
    
    public FolderDB() {
        folders = new HashMap();
        
        
        f = new File("D:\\YourFolderbackup.sarjit");
        
        if(f.exists())
        {
            try 
            {
                if(f.length() > 0){
                    readFolders = new ObjectInputStream(new FileInputStream(f));
                    folders = (HashMap<String,String>) readFolders.readObject();
                }
            }
            catch (FileNotFoundException ex) {
                
            } catch (IOException ex) {
                
            } catch (ClassNotFoundException ex) {
                
            }
        }
    }
    
    
    public void start()    
    {
        if(folders.isEmpty())   return;
        
        for(String src : folders.keySet())
        {
            backUpFolder(src, folders.get(src));
        }
    }
    
    public void backUpFolder(String src,String dest)
    {
        File srcFile = new File(src);
        File destFile;
        if( dest.substring( dest.lastIndexOf("\\") + 1 ).equals( src.substring(src.lastIndexOf("\\") + 1) ) )
        {
            destFile = new File(dest);
        }
        else{
            destFile = new File(dest + "\\" + srcFile.getName());
        }
        
        dest = destFile.getAbsolutePath() + "\\";
        
        if(srcFile.isDirectory())
        {
            if(!destFile.exists()){
                destFile.mkdir();
            }
            File srcFiles[] = srcFile.listFiles();
            File destFiles[];
            
            destFiles = new File[srcFiles.length];
            for(int i = 0; i < srcFiles.length; i++)
            {
                
                destFiles[i] = new File(dest + srcFiles[i].getName());
                //System.out.println("Parent : " + destFiles[i].getParentFile().getName());
                //System.out.println("Source : " + srcFiles[i].getName());
                
                if(destFiles[i].getParentFile().getName().equals(srcFiles[i].getName()))
                    destFiles[i] = new File(dest);
                    
                //System.out.println(destFiles[i]);
            }
            
            
            for(int i = 0; i < srcFiles.length; i++)
            {
                if(srcFiles[i].isHidden())   continue;
                //System.out.println(srcFiles[i]);
                if(srcFiles[i].isDirectory())
                {
                    //System.out.println(destFiles[i].exists() + " dest " + destFiles[i]);
                    
                    if(destFiles[i].exists() == false){
                        destFiles[i].mkdir();
                        //System.out.println("Directory Created...");
                    }
                    destFiles[i].setLastModified(srcFiles[i].lastModified());
                    backUpFolder(srcFiles[i] + "", destFiles[i] + "");
                }
                
                else
                {
                    
                    try 
                    {
                        if(!destFiles[i].exists())
                        {
                            destFiles[i].createNewFile();
                            //System.out.println("File Created : " + destFiles[i]);
                        }   
                        
                        
                            FileInputStream inStream = new FileInputStream(srcFiles[i]);
                            FileOutputStream outStream = new FileOutputStream(destFiles[i]);

                            BufferedInputStream read = new BufferedInputStream(inStream);
                            BufferedOutputStream write = new BufferedOutputStream(outStream);

                            byte data[] = new byte[65535];
                            int count;
                            //System.out.println("Receiving file");

                            while((count = read.read(data)) > 0)
                            {
                                write.write(data, 0, count);

                                write.flush();

                            }
                            
                            //System.out.println("File Backed up : " + destFiles[i]);
                        
                        
                    }
                    catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                    
                    
                }
                
                //System.out.println("I : " + i + "\t dest = " + dest);
            }
            
        }
    }
    
}
