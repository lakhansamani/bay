

import java.util.*;
public class RunnableExample {

    public static void main(String[] args) {

        /*
         * Create a new classifier instance. The context features are
         * Strings and the context will be classified with a String according
         * to the featureset of the context.
         */
        final Classifier<String, String> bayes =
                new BayesClassifier<String, String>();
        
        
        
        /*Addition
            args[0] takes input of RCV path
        */

        //BLearn(args[0]);
        //BClass(args[0]);

        /*
         * The classifier can learn from classifications that are handed over
         * to the learn methods. Imagin a tokenized text as follows. The tokens
         * are the text's features. The category of the text will either be
         * positive or negative.
         */
        
        
        /*final String[] positiveText = "I love sunny days".split("\\s");
        bayes.learn("positive", Arrays.asList(positiveText));

        final String[] negativeText = "I hate rain".split("\\s");
        bayes.learn("negative", Arrays.asList(negativeText));*/

        /*
         * Now that the classifier has "learned" two classifications, it will
         * be able to classify similar sentences. The classify method returns
         * a Classification Object, that contains the given featureset,
         * classification probability and resulting category.
         */
        
        /*final String[] unknownText1 = "today is a sunny day".split("\\s");
        final String[] unknownText2 = "there will be rain".split("\\s");

        System.out.println( // will output "positive"
                bayes.classify(Arrays.asList(unknownText1)).getCategory());
        System.out.println( // will output "negative"
                bayes.classify(Arrays.asList(unknownText2)).getCategory());*/

        /*
         * The BayesClassifier extends the abstract Classifier and provides
         * detailed classification results that can be retrieved by calling
         * the classifyDetailed Method.
         *
         * The classification with the highest probability is the resulting
         * classification. The returned List will look like this.
         * [
         *   Classification [
         *     category=negative,
         *     probability=0.0078125,
         *     featureset=[today, is, a, sunny, day]
         *   ],
         *   Classification [
         *     category=positive,
         *     probability=0.0234375,
         *     featureset=[today, is, a, sunny, day]
         *   ]
         * ]
         */
        //((BayesClassifier<String, String>) bayes).classifyDetailed(
                //Arrays.asList(unknownText1));

        /*
         * Please note, that this particular classifier implementation will
         * "forget" learned classifications after a few learning sessions. The
         * number of learning sessions it will record can be set as follows:
         */
        bayes.setMemoryCapacity(500); // remember the last 500 learned classifications
    }
    
    
    // public static HashMap <String,HashMap<String,String[]>> BLearn(String sDir){
        

    //     FileInputStream fin;
    //     File[] faFiles = new File(sDir).listFiles();
    //     String content="";
    //     final Classifier<String, String> bayes = new BayesClassifier<String, String>();
    //     HashMap <String, HashMap<String,String[]>> final_map=new HashMap<>();
    //     try{
    //         for(File file: faFiles){
    //             if(file.isDirectory()){
    //                 BLearn(file.getAbsolutePath());
    //             }
    //             else if(file.getName().matches("^(.*?)")&& !file.getName().startsWith(".") && !file.isHidden()){
    //                 content=new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
    //                 //final String[] Text = content.split("\\s+");
    //                 String[] Class=Extract("class",content);
    //                 final
    //                 String[] Info=Extract("text",content);
    //                 final String[] Text = t1.split("\\s+");

    //                 //bayes.learn("positive", Arrays.asList(Text));
    //             }
    //         }
    //     }
    //     catch (IOException e){
    //         System.err.println ("Unable to read from file");
    //         System.exit(-1);
    //     }
    //     return final_map;
    // }

    
    
    // public static void BClass(String sDir){
        
    //     FileInputStream fin;
    //     File[] faFiles = new File(sDir).listFiles();
    //     String content="";
    //     final Classifier<String, String> bayes = new BayesClassifier<String, String>();
        
    //     try{
    //         for(File file: faFiles){
    //             if(file.isDirectory()){
    //                 printFnames(file.getAbsolutePath());
    //             }
    //             else if(file.getName().matches("^(.*?)")&&!file.getName().equals(".DS_Store")){
    //                 content=new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
    //                 final String[] Text = content.split("\\s+");
    //                 System.out.println(bayes.classify(Arrays.asList(Text)).getCategory());
    //             }
    //         }
    //     }
    //     catch (IOException e){
    //         System.err.println ("Unable to read from file");
    //         System.exit(-1);
    //     }
    // }
    
    
    
    
    // public static String[] Extract(String tag,String input){
        
    //     try{
    //         String content="";
        
    //         if(tag.equals("text")){
    //             for(String word: input){
    //                 if(word.equals("<"+tag+">"))
    //                     break;
    //             for(String word: input){
                            
                        
    //                 }
    //             }
    //         }
    //         else if(tag.equals("class")){
                
    //         }
    //     }
    //     catch (Exception e){
    //         System.err.println ("Unable to read from file");
    //         System.exit(-1);
    //     }
    // }

}
