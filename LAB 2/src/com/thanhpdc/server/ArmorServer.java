package com.thanhpdc.server;

import com.thanhpdc.dao.ArmorDAO;
import com.thanhpdc.dto.ArmorDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ArmorServer extends UnicastRemoteObject implements ArmorInterface {
    
    private static final String FILENAME = "ArmorData.txt";
    
    public ArmorServer() throws RemoteException {
    }

    @Override
    public boolean createArmor(ArmorDTO dto) {
        List<ArmorDTO> listArmor = ArmorDAO.readFile(FILENAME);
        listArmor.add(dto);
        ArmorDAO.writeFile(FILENAME, listArmor);
        return true;
    }

    @Override
    public ArmorDTO findByArmorID(String id) {
        ArmorDTO armor = null;
        List<ArmorDTO> listArmor = ArmorDAO.readFile(FILENAME);
        for (ArmorDTO armorDTO : listArmor) {
            if (armorDTO.getArmorId().equals(id)) {
                armor = new ArmorDTO(armorDTO.getArmorId(), armorDTO.getClassification(),
                        armorDTO.getDescription(), armorDTO.getStatus(), armorDTO.getTimeOfCreate(), armorDTO.getDefense());
            }
        }
        return armor;
    }

    @Override
    public List<ArmorDTO> findAllArmor() {
        List<ArmorDTO> listArmor = new ArrayList<>();
        listArmor = ArmorDAO.readFile(FILENAME);
        return listArmor;
    }

    @Override
    public boolean removeArmor(String id) {
        List<ArmorDTO> listArmor = ArmorDAO.readFile(FILENAME);  
        for (ArmorDTO armorDTO : listArmor) {
            if (armorDTO.getArmorId().equals(id)) {           
                listArmor.remove(armorDTO);
                    ArmorDAO.writeFile(FILENAME, listArmor);
                    return true;
            }
        }         
        return false;
    }

    @Override
    public boolean updateArmor(ArmorDTO dto) {
        List<ArmorDTO> listArmor = ArmorDAO.readFile(FILENAME);
        for (ArmorDTO armorDTO : listArmor) {
            if (armorDTO.getArmorId().equals(dto.getArmorId())) {
                armorDTO.setClassification(dto.getClassification());
                armorDTO.setDescription(dto.getDescription());
                armorDTO.setStatus(dto.getStatus());
                armorDTO.setTimeOfCreate(dto.getTimeOfCreate());
                armorDTO.setDefense(dto.getDefense());
            }
        }
        ArmorDAO.writeFile(FILENAME, listArmor);
        return true;
    }

    

}
