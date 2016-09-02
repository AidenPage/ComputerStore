package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import org.junit.Test;
import computerstore.com.computerstore.factories.components.NotebookFactory;
import computerstore.com.computerstore.domain.components.Notebook;
import junit.framework.Assert;

public class NotebookTest {

    private NotebookFactory factory;

    @Test
    public void testNotebookCreation() throws Exception {
        Notebook notebook = factory.createNotebook("A555LN-XX299H",50,"Asus A555LN-XX299H",11999.00);
        Assert.assertEquals(notebook.getDescription(),"Asus A555LN-XX299H");
        Assert.assertEquals(notebook.getProductNumber(),"A555LN-XX299H");
    }

    @Test
    public void testNotebookUpdate() throws Exception {
        Notebook notebook = factory.createNotebook("A555LN-XX299H",50,"Asus A555LN-XX299H",11999.00);
        Assert.assertEquals(notebook.getDescription(),"Asus A555LN-XX299H");
        Assert.assertEquals(notebook.getProductNumber(),"A555LN-XX299H");

        // Updated Description

        Notebook updateNotebook = new Notebook.Builder()
                .Notebook(notebook)
                .price(13500.00)
                .build();

        Assert.assertEquals(updateNotebook.getPrice(),13500.00);
        Assert.assertEquals(notebook.getProductNumber(),updateNotebook.getProductNumber());




    }
}
