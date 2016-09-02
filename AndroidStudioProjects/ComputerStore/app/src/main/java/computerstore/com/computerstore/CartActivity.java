package computerstore.com.computerstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Aidem on 2016/09/02.
 */
public class CartActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.startmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.login:
                intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                break;
            case R.id.cart:
                intent = new Intent(this, CartActivity.class);
                this.startActivity(intent);
                break;
            case R.id.action_settings:
                intent = new Intent(this, AccountRegisterActivity.class);
                this.startActivity(intent);
                break;
            case R.id.components:
                intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
