package com.bank.model;

public class Account extends Customer{
	private String name;
	private long accountNumber;
	private String panCard;
	private double amount;
	private double openingBalance;
	private double closingBalance;
	private boolean status;
	private String userId1;

	
	public Account() {
		// TODO Auto-generated constructor stubString userId1,
	}
	
	
	
	public Account(String userId1,String name, String panCard, double openingBalance) {
		this.userId1=userId1;
		this.name = name;
		//this.accountNumber = accountNumber;
		this.panCard = panCard;
		this.openingBalance = openingBalance;
		
	}
	

	public String getUserId1() {
		return userId1;
	}

	public Account(String name, long accountNumber, String panCard, double openingBalance, String userId1) {
		super();
		this.name = name;
		this.accountNumber = accountNumber;
		this.panCard = panCard;
		this.openingBalance = openingBalance;
		this.userId1 = userId1;
	}



	public Account(long accountNumber, double openingBalance) {
		super();
		this.accountNumber = accountNumber;
		this.openingBalance = openingBalance;
	}



	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

//	@Override
//	public String toString() {
//		return "Account [name=" + name + ", accountNumber=" + accountNumber + ", panCard=" + panCard + ", amount="
//				+ amount + ", openingBalance=" + openingBalance + ", closingBalance=" + closingBalance + ", status="
//				+ status + ", userId1=" + userId1 + "]";
//	}
	
	@Override
	public String toString() {
		return "Account [name=" + name + ", accountNumber=" + accountNumber + ", panCard=" + panCard + ", openingBalance=" + openingBalance + ", userId1=" + userId1 + "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(closingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(openingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((panCard == null) ? 0 : panCard.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((userId1 == null) ? 0 : userId1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(closingBalance) != Double.doubleToLongBits(other.closingBalance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(openingBalance) != Double.doubleToLongBits(other.openingBalance))
			return false;
		if (panCard == null) {
			if (other.panCard != null)
				return false;
		} else if (!panCard.equals(other.panCard))
			return false;
		if (status != other.status)
			return false;
		if (userId1 == null) {
			if (other.userId1 != null)
				return false;
		} else if (!userId1.equals(other.userId1))
			return false;
		return true;
	}

	
	

	

	
	
}
