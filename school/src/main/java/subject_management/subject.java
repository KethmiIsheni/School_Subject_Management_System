package subject_management;

public class subject {
	protected int subID;
	protected String name;
	protected String credit;
	
	
	public subject() {
		
	}

	public subject(int subID , String name,String credit ) {
		super();
		this.subID = subID;
		this.name = name;
		this.credit = credit;
		
	}

	public subject(String name,String credit) {
		super();
		this.name = name;
		this.credit = credit;
		
		
	}

	public int getSubID() {
		return subID;
	}

	public void setSubID(int subID) {
		this.subID = subID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

}
