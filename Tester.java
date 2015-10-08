

import java.util.*;
import java.io.*;
import java.nio.file.*;
public class Tester {

    public static HashMap<String,HashMap<String,String[]>> extract(String dir){
        FileInputStream fin;
        File[] faFiles = new File(dir).listFiles();
        String content="";
        final Classifier<String, String> bayes = new BayesClassifier<String, String>();
        HashMap <String, HashMap<String,String[]>> final_map=new HashMap<>();
        try{
            for(File file: faFiles){
                if(file.isDirectory()){
                    extract(file.getAbsolutePath());
                }
                else if(file.getName().matches("^(.*?)")&& !file.getName().startsWith(".") && !file.isHidden()){
                    content=new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                    String text_content=content.split("<text>")[1].split("</text>")[0]; //extracts tag
                    text_content=text_content.replaceAll("\\<[^>]*>","");
                    
                }
            }
        }
        catch (IOException e){
            System.err.println ("Unable to read from file");
            System.exit(-1);
        }
        return final_map;
    }
    public static void main(String[] args) {
        extract(args[0]);
    }
}
