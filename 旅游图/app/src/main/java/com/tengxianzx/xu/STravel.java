package com.tengxianzx.xu;

import org.litepal.crud.DataSupport;

public class STravel extends DataSupport {

    private int id,sid,schengji;
    private String sname,ssheng,sdaxue,smajor,spassword;
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsheng() {
        return ssheng;
    }

    public void setSsheng(String ssheng) {
        this.ssheng = ssheng;
    }

    public String getSdaxue() {
        return sdaxue;
    }

    public void setSdaxue(String sdaxue) {
        this.sdaxue = sdaxue;
    }

    public String getSmajor() {
        return smajor;
    }

    public void setSmajor(String smajor) {
        this.smajor = smajor;
    }

	public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
	
	public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }
	
	public int getSchengji() {
        return schengji;
    }

    public void setSchengji(int sid) {
        this.schengji = sid;
    }
}

