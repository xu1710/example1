package com.tengxianzx.xu;

public class Traver
{
    private int id;
	private String lid;
	private String lchengji;
	private String name,major,lpassw,lsheng,lcollege,ssheng;

    public Traver(String name, String lid, String college, String major,
				  String sheng, int Id, String passw, String lcj)
	{
        this.name = name;
        this.id = Id;
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

    public int getid()
	{
        return id;
    }

	public String getmajor()
	{
		return major;
	}

	public String getlsheng()
	{
        return lsheng;
    }

	public String getlid()
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

	public String getlchengji()
	{
		return lchengji;
	}
}
