/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fenil Admin
 */
public class FineDetail {

    private int loanid, paid, carno, branch_id;
    private float fine;
    private String tile, date_out, date_in, date_due;

    public int getCarno() {
        return carno;
    }

    public void setCarno(int carno) {
        this.carno = carno;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getDate_out() {
        return date_out;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }

    public String getDate_in() {
        return date_in;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    public String getDate_due() {
        return date_due;
    }

    public void setDate_due(String date_due) {
        this.date_due = date_due;
    }

    public int getLoanid() {
        return loanid;
    }

    public void setLoanid(int loanid) {
        this.loanid = loanid;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

}
