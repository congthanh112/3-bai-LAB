
package com.thanhpdc.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArmorDTO implements Serializable{
    String ArmorId;
    String Classification;
    String Description;
    String Status;
    java.util.Date TimeOfCreate;
    int Defense;
   

    public ArmorDTO(String ArmorId, String Classification, String Description, String Status, Date TimeOfCreate, int Defense) {
        this.ArmorId = ArmorId;
        this.Classification = Classification;
        this.Description = Description;
        this.Status = Status;
        this.TimeOfCreate = TimeOfCreate;
        this.Defense = Defense;
        //this.isDelete = false;
    }

    public String getArmorId() {
        return ArmorId;
    }

    public void setArmorId(String ArmorId) {
        this.ArmorId = ArmorId;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String Classification) {
        this.Classification = Classification;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Date getTimeOfCreate() {
        return TimeOfCreate;
    }

    public void setTimeOfCreate(Date TimeOfCreate) {
        this.TimeOfCreate = TimeOfCreate;
    }

    public int getDefense() {
        return Defense;
    }

    public void setDefense(int Defense) {
        this.Defense = Defense;
    }

    

    @Override
    public String toString() {       
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return  ArmorId + ";" + Classification + ";" + Description + ";" + Status + ";" + sdf.format(TimeOfCreate) + ";" + Defense;
    }
    
    
    
}
