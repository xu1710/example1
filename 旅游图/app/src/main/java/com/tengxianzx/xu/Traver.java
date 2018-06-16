package com.tengxianzx.xu;

public class Traver
{
    private int Sid,lid,lchengji;
	private String name,major,lpassw,lsheng,lcollege,ssheng;

    public Traver(String name, int Id, String college, String major,
				  String sheng, int lid, String passw, int lcj)
	{
        this.name = name;
        this.Sid = Id;
		this.major = major;
		this.lsheng = sheng;
		this.lid = lid;
		this.lpassw = passw;
		this.lcollege = college;
		this.lchengji = lcj;
    }
	public Traver(String ssheng)
	{
		this.ssheng = ssheng;
	}

    public String getName()
	{
        return name;
    }

    public int getSid()
	{
        return Sid;
    }

	public String getmajor()
	{
		return major;
	}

	public String getlsheng()
	{
        return lsheng;
    }

	public int getlid()
	{
        return lid;
    }

	public String getlpassw()
	{
        return lpassw;
    }

	public String getlcollege()
	{
		return lcollege;
	}
	
	public String getssheng()
	{
		return ssheng;
	}
	
	public int getlchengji()
	{
		return lchengji;
	}
}
