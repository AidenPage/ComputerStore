package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.Notebook;
import computerstore.com.computerstore.respository.Repository;

public interface NotebookAPI {

    Notebook createNotebook(Notebook notebook) throws IOException;

    Notebook updateNotebook(Notebook notebook) throws IOException;
}
