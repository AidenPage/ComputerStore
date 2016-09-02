package computerstore.com.computerstore.services.components;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.Memory;
import computerstore.com.computerstore.respository.Repository;

public interface MemoryService{
    void addMemory(Context context, Memory memory);

    void updateMemory(Context context, Memory memory);

}
