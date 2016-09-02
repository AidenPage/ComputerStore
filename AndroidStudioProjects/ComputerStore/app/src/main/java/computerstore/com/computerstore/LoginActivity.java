package computerstore.com.computerstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Aidem on 2016/09/02.
 */
public class LoginActivity extends AppCompatActivity {

    private Intent intent;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addListenerOnButton();
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

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.bntRegister);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, AccountRegisterActivity.class);
                startActivity(intent);

            }

        });

    }

}
