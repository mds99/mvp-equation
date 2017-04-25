package ru.pp.mita.mvpsquareroot;

import com.arellomobile.mvp.MvpView;



public interface EquationView extends MvpView {
    void showA(double A);
    void showB(double B);
    void showC(double C);
    void showResult(String str);
    void goReport(double a, double b,double c,double result);
}

