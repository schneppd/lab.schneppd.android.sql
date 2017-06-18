package lab.schneppd.sqltest;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by schneppd on 6/18/17.
 * read sql files in asset and convert them in query strings
 */

public class SqliteParser {

    // used to read table creation, view creation, index
    static public List<String> GetSimpleSqlCommand(Context currentContext, String ressourcePath){
        List<String> res = new ArrayList<>();

        try{
            final InputStream file = currentContext.getResources().getAssets().open(ressourcePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            String currentCommand = "";
            while(line != null){
                Log.d("Line read: ", line);
                //ignore comments lines
                if(!line.startsWith("--") && line != ""){
                    // ; alone marks the end of the command
                    if(line.equals(";")){
                        res.add(currentCommand);
                        currentCommand = "";
                    }
                    else{
                        currentCommand += line.replace('\t', ' ');
                    }
                }

                line = reader.readLine();
            }
        } catch(IOException p){
            p.printStackTrace();
        }

        return res;
    }
}
