package com.thanhpdc.server;

import com.thanhpdc.dto.ArmorDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ArmorInterface extends Remote{
    
    boolean createArmor(ArmorDTO dto) throws RemoteException;
    
    ArmorDTO findByArmorID(String id) throws RemoteException;
    
    List<ArmorDTO> findAllArmor() throws RemoteException;
    
    boolean removeArmor(String id) throws RemoteException;
    
    boolean updateArmor(ArmorDTO dto) throws RemoteException;
        
}
