package com.example.moodTracker;

public class model {
    String q1, q2, q3;
    String btn_ans1, btn_ans2, btn_ans3, btn_ans4;
    String btn_txt;
    String id;

    model(){

    }

    /*pub lic model(String q1, String q2, String q3, String btn_ans1, String btn_ans2, String btn_ans3, String btn_ans4, String btn_txt, String id) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.btn_ans1 = btn_ans1;
        this.btn_ans2 = btn_ans2;
        this.btn_ans3 = btn_ans3;
        this.btn_ans4 = btn_ans4;
        this.btn_txt = btn_txt;
        this.id = id;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getBtn_ans1() {
        return btn_ans1;
    }

    public void setBtn_ans1(String btn_ans1) {
        this.btn_ans1 = btn_ans1;
    }

    public String getBtn_ans2() {
        return btn_ans2;
    }

    public void setBtn_ans2(String btn_ans2) {
        this.btn_ans2 = btn_ans2;
    }

    public String getBtn_ans3() {
        return btn_ans3;
    }

    public void setBtn_ans3(String btn_ans3) {
        this.btn_ans3 = btn_ans3;
    }

    public String getBtn_ans4() {
        return btn_ans4;
    }

    public void setBtn_ans4(String btn_ans4) {
        this.btn_ans4 = btn_ans4;
    }

    public String getBtn_txt() {
        return btn_txt;
    }

    public void setBtn_txt(String btn_txt) {
        this.btn_txt = btn_txt;
    }
}