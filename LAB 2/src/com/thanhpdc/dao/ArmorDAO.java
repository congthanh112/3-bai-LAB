
package com.thanhpdc.dao;

import com.thanhpdc.dto.ArmorDTO;
import com.thanhpdc.util.checkValue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ArmorDAO {

    public static void checkFile(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
            }
        }
    }

    public static ArrayList<ArmorDTO> readFile(String fileName) {
        checkFile(fileName);
        ArrayList<ArmorDTO> list = new ArrayList<>();
        FileReader f = null;
        BufferedReader bf = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
       
        try {
            f = new FileReader(fileName);
            bf = new BufferedReader(f);
            while (bf.ready()) {
                String s = bf.readLine();
                String[] arr = s.split(";");
                //boolean checkInput;
                boolean check = checkValue.checkArmorId(arr[0]) && checkValue.checkClassification(arr[1])
                        && checkValue.checkDescription(arr[3]) && checkValue.checkDefense(arr[5]);
                int checkDU=0;
                for(int i=0;i<list.size();i++){
                    if(arr[0].equals(list.get(i).getArmorId())){
                        ++checkDU;
                        list.remove(i);
                    }
                }
                if (arr.length == 6 && check && checkDU==0) {
                    ArmorDTO armor = new ArmorDTO(arr[0], arr[1], arr[2], arr[3], sdf.parse(arr[4]), Integer.parseInt(arr[5]));
                    list.add(armor);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (bf != null) {
                    bf.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static void writeFile(String filename, List<ArmorDTO> list) {
        checkFile(filename);
        if (list == null ) {
            return;
        }
        PrintWriter w = null;
        try {
            w = new PrintWriter(filename);
            for (ArmorDTO armorDTO : list) {
                String s = armorDTO.toString();
                w.println(s);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
