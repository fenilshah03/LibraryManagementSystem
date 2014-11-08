/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fenil Admin
 */
public class CheckInBookDetail {

    private int loanid, cardno;
    private String bookid, fname, lname, dateout, datedue;

    public String getDatedue() {
        return datedue;
    }

    public void setDatedue(String datedue) {
        this.datedue = datedue;
    }

    public int getLoanid() {
        return loanid;
    }

    public void setLoanid(int loanid) {
        this.loanid = loanid;
    }

    public int getCardno() {
        return cardno;
    }

    public void setCardno(int cardno) {
        this.cardno = cardno;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDateout() {
        return dateout;
    }

    public void setDateout(String dateout) {
        this.dateout = dateout;
    }

}
