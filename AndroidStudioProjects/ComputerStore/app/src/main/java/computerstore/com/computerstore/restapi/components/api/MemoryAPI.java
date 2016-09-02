package computerstore.com.computerstore.restapi.components.api;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.Memory;
import computerstore.com.computerstore.respository.Repository;

public interface MemoryAPI {
    Memory createMemory(Memory memory) throws IOException;

    Memory updateMemory(Memory memory) throws IOException;

}
