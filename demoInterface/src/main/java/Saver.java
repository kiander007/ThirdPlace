import Frame.CompanyTweet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saver implements Serializable {

    private String saveFileFolder;

    public Saver() {
        this.saveFileFolder = "savefiles/";
    }

    public void saveGraph(String fileName, List<CompanyTweet> compList){

        boolean success = false;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveFileFolder+fileName));
            objectOutputStream.writeObject(compList);
            objectOutputStream.close();
            success = true;
        }catch (FileNotFoundException e){
            //path has not been found
            //try creating directory
            //if not existent before try saving again
            //else display an error
            if(createSaveFileDir())
                saveGraph(fileName,compList);
            else
                System.out.println("[path was not found: "+e.getMessage()+" ]");
        }        catch (IOException e){
            System.out.println("[saving has failed: " + e.getMessage()+ " ]");
        }
        if(success){
            System.out.println("[The game has successfully been saved under "+fileName+"]");
        }
    }

    public List<CompanyTweet> loadGraph(String fileName){
        boolean success = false;
        List<CompanyTweet> result;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(saveFileFolder+fileName));
            result = (List<CompanyTweet>) objectInputStream.readObject();
            success = true;
        }  catch (ClassNotFoundException e) {
            //the class is not recognised
            System.out.println("[save file is corrupt: " + e.getMessage()+"]");
            result =  new ArrayList<>();
        } catch (FileNotFoundException e) {
            //the path has not been found
            //try creating a dir
            //the dir is always empty, so display file not found message.
            createSaveFileDir();
            System.out.println("[file was not found: "+e.getMessage()+" ]");
            result =  new ArrayList<>();
        }catch (EOFException e){
            //if someone tempered with the file
            System.out.println("[save file is corrupt: " + e.getMessage()+"]");
            result =  new ArrayList<>();
        } catch (IOException e) {
            //some general error occurred
            System.out.println("[error loading game: " + e.getMessage()+"]");
            result =  new ArrayList<>();
            e.printStackTrace();
        }
        if(success) {
            System.out.println("[" + fileName + " was successfully loaded]");

        }else{
            System.out.println("[failed loading this file. please select another]");
        }
        return result;
    }

    private boolean createSaveFileDir(){
        File saveDir = new File(saveFileFolder);
        boolean result = false;
        if(!saveDir.exists()){
            System.out.println("[creating new save file directory]");

            try{
                saveDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                System.out.println("we do not have permission to create the directory: "+ se.getMessage());
            }
            if(result) {
                System.out.println("[directory created]");
            }
        }
        return result;
    }
}
