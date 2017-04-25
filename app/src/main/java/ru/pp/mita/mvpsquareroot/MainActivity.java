package ru.pp.mita.mvpsquareroot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements EquationView {
    String LOG_TAG = "mimimita";
    TextView txtA,txtB,txtC,txtResult;
    Button btnSolve,btnClear,btnReport;

    @InjectPresenter
    public EquationPresenterImpl presenter; //link to presenter impl

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG,"Hello");

        txtA = (TextView) findViewById(R.id.valA);
        txtB = (TextView) findViewById(R.id.valB);
        txtC = (TextView) findViewById(R.id.valC);
        txtResult = (TextView) findViewById(R.id.txtResult);

        btnSolve = (Button) findViewById(R.id.btnSolve);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnReport = (Button) findViewById(R.id.btnReport);


        btnSolve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    presenter.setData(Double.valueOf(txtA.getText().toString()),
                            Double.valueOf(txtB.getText().toString()),
                            Double.valueOf(txtC.getText().toString())
                    );
                    presenter.equationSolve();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Inputs should be double", Toast.LENGTH_SHORT).show();
                    presenter.cleanUI();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.cleanUI();
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.wannaReport();
            }
        });

    }

    @Override
    public void showA(double A) {
        txtA.setText(""+A);
    }

    @Override
    public void showB(double B) {
        txtB.setText(""+B);
    }

    @Override
    public void showC(double C) {
        txtC.setText(""+C);
    }

    @Override
    public void showResult(String str) {
        txtResult.setText(str);
    }

    @Override
    public void goReport(double a, double b, double c, double result) {
        Log.d(LOG_TAG,"Reporting in");
        txtResult.setText("Reported in");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuoptions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.d(LOG_TAG,"Menu");
        switch (item.getItemId()) {
            case R.id.menuReset:
                presenter.cleanUI();
                return true;
            case R.id.menuReport:
                presenter.wannaReport();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
