

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
                    //extracts from <text></text>
                    String text_content=content.split("<text>")[1].split("</text>")[0];
                    //removes <p></p> tag
                    text_content=text_content.replaceAll("\\<[^>]*>","");
                    //adds text to hashmap
                    HashMap<String,String[]> inner_map=new HashMap();
                    
                    
                    String[] temp=text_content.split("\\s+");
                    String[] rem = new String[]{"of","and","the"};
                    List<String> templ=new ArrayList<String>(Arrays.asList(temp));
                    List<String> reml=new ArrayList<String>(Arrays.asList(rem));
                    templ.removeAll(reml);
                    String[] fina=new String[templ.size()];
                    templ.toArray(fina);
                    
                    
                    inner_map.put("text",fina);
                    
                    //TODO: extracts class tag
                    
                    final_map.put(file.getAbsolutePath(),inner_map);
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
        HashMap<String,HashMap<String,String[]>> map=extract(args[0]);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("---------File name:"+entry.getKey() + "--------");
            HashMap<String,String[]> inner_map=(HashMap)entry.getValue();
            for(Map.Entry inner_entry: inner_map.entrySet()){
                StringBuilder builder = new StringBuilder();
                String[] arr=(String[])inner_entry.getValue();
                for(String s : arr) {
                    builder.append(s);
                }
                System.out.println(builder.toString());
            }
        }
    }
    
    
}
