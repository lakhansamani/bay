

import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Tester {

    public static HashMap<String,HashMap<String,String[]>> extract(String dir) throws Exception{
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
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(new File(file.getAbsolutePath()));

                    NodeList text_node = doc.getElementsByTagName("text");
                    String text_content=text_node.item(0).getTextContent();
                    // //adds text to hashmap
                    HashMap<String,String[]> inner_map=new HashMap();
                    
                    // //Removing stop words
                    String[] temp=text_content.split("\\s+");
                    String[] rem = new String[]{"of","and","the"};
                    List<String> templ=new ArrayList<String>(Arrays.asList(temp));
                    List<String> reml=new ArrayList<String>(Arrays.asList(rem));
                    templ.removeAll(reml);
                    String[] final_text=new String[templ.size()];
                    templ.toArray(final_text);
                    
                    
                    inner_map.put("text",final_text);
                    
                    
                    
                    doc.getDocumentElement().normalize();
                    NodeList class_list = doc.getElementsByTagName("codes");
                    String class_list_string="";
                    for (int t = 0; t < class_list.getLength(); t++) {

                        Node class_item = class_list.item(t);
                        Element element = (Element) class_item;
                        if(element.getAttribute("class").equals("bip:topics:1.0")){
                            NodeList child = element.getChildNodes();
                            for(int x=0;x<child.getLength();x++){
                                Node cNode = child.item(x);
                                if(cNode.getNodeName().equals("code")){
                                    Element el = (Element) cNode;
                                    class_list_string+=el.getAttribute("code")+" ";
                                }
                            }
                        }            
                    }
                    inner_map.put("class",class_list_string.split("\\s+"));

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
    public static void main(String[] args)  throws Exception{
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
