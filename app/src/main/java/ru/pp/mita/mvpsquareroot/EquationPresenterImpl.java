package ru.pp.mita.mvpsquareroot;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;



@InjectViewState
public class EquationPresenterImpl extends MvpPresenter<EquationView> implements EquationPresenter{
    double doubleA,doubleB,doubleC = 0;
    double Result = 0;
    String strResultOutput = "N/A";
    double doubleX1,doubleX2 = 0;
    String LOG_TAG = "mimimita";

    @Override
    public void setData(double A, double B, double C) {
        doubleA = A;
        doubleB = B;
        doubleC = C;
    }

    @Override
    public void equationSolve() {
        strResultOutput = getResult (doubleA, doubleB, doubleC);
        getViewState().showResult("Hello world \n"+ strResultOutput);
    }

    @Override
    public void cleanUI() {
        doubleA = 0;
        doubleB = 0;
        doubleC = 0;
        Result = 0;

        getViewState().showA(doubleA);
        getViewState().showB(doubleB);
        getViewState().showC(doubleC);
        getViewState().showResult("");
    }

    @Override
    public void wannaReport() {
        Log.d(LOG_TAG,"Reporting from presenter");
    }

    public double Discr(double A, double B, double C) {
        return Math.pow(B,2)-4*A*C;
    }

    public String getResult (double A, double B, double C) {
        double sqrD,dscrD = 0;
        String Res = "N/A N/A";
        dscrD = Discr(A,B,C);
        Log.d(LOG_TAG,"A:"+A+" B:"+B+" C:"+C+" discr:"+dscrD);
        if (dscrD == 0.0) {
            sqrD = Math.sqrt(dscrD);
            Log.d(LOG_TAG,"Discr=0, sqrD:"+sqrD);
            doubleX1 = -B/2*A;
            doubleX2 = doubleX1;
            Res = "Roots are double;\n X1=" + doubleX1 + ", X2="+doubleX2;
        }
        if (dscrD <0) {
            doubleX1 = doubleX2 = 0;
            Log.d(LOG_TAG,"sqrD is NaN");
            Res = "Discriminant < 0;\nRoots are complex.";
        }
        if (dscrD > 0) {
            sqrD = Math.sqrt(Discr(A,B,C));
            Log.d(LOG_TAG,"sqrD:"+sqrD);
            doubleX1 = (-B + sqrD)/2*A;
            doubleX2 = (-B - sqrD)/2*A;
            Res = "Discriminant = " + sqrD + ",\nX1 = " + doubleX1 + ", \nX2=" + doubleX2;
        }

        return Res;
    }
}

