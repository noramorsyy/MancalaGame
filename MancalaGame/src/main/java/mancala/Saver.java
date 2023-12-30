package mancala;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saver{
    public static void saveObject(final Serializable objectSaved, final String fName){
        final Path saverFile = Paths.get("assets", fName);
        try{
            final FileOutputStream fos = new FileOutputStream(saverFile.toFile());
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objectSaved);
            oos.close();
        } catch(IOException e){
            return;
        }
    }

    public static Serializable loadObject(final String fName){
        Serializable objSaved = null;
        final Path saverFile = Paths.get("assets", fName);
        try{
            final FileInputStream fis = new FileInputStream(saverFile.toFile());
            final ObjectInputStream ois = new ObjectInputStream(fis);
            try{
                objSaved = (Serializable) ois.readObject();
            } catch(ClassNotFoundException e){
                ois.close();
                return null;
            }
            final boolean isObjectValid = objSaved instanceof UserProfile || objSaved instanceof MancalaGame;
            if(!isObjectValid){
                objSaved = null;
            }

            ois.close();
            return objSaved;
        } catch(IOException e){
            return null;
        }
    }
}