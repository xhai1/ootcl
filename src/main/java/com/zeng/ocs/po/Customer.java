package  com.zeng.ocs.po;


public class Customer {
    @Override
	public String toString()
	{
		return "Customer [id=" + id + ", cusid=" + cusid + ", cusname=" + cusname + ", telephone=" + telephone + "]";
	}

	private Integer id;

    private String cusid;

    private String cusname;

    private String telephone;
    
    private String lastmodify;
    
    public String getLastmodify() {
		return lastmodify;
	}

	public void setLastmodify(String lastmodify) {
		this.lastmodify = lastmodify;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid == null ? null : cusid.trim();
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname == null ? null : cusname.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }
}