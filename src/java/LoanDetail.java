import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fenil Admin
 */
public class LoanDetail {

    private int loanid;
    private float fineamt;
    private int paid;
    private Date due, in;

    public int getLoanid() {
        return loanid;
    }

    public void setLoanid(int loanid) {
        this.loanid = loanid;
    }

    public float getFineamt() {
        return fineamt;
    }

    public void setFineamt(float fineamt) {
        this.fineamt = fineamt;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getIn() {
        return in;
    }

    public void setIn(Date in) {
        this.in = in;
    }

}
