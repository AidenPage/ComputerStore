package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.Notebook;
import computerstore.com.computerstore.respository.Repository;

public interface NotebookService{
    void addNotebook(Context context, Notebook notebook);

    void updateNotebook(Context context, Notebook notebook);

}
