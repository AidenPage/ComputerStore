package computerstore.com.computerstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnSpinnerItemSelection();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.startmenu, menu);
        return true;
    }


    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinnerComputerList);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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
