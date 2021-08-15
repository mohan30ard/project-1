package com.bank.model;

public class Transaction {

	private long accountNumber;
	private double amount;
	private double openingBalance1;
	private double closingBalance;
	private String userId2;
	private int transId;
	private String transType;
	private String date;
	//private String updatedBalance;

	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
	}




	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Transaction(long accountNumber, double openingBalance1) {
		super();
		this.accountNumber = accountNumber;
		this.openingBalance1 = openingBalance1;
	}




	public Transaction(double amount,double closingBalance ,String transType) {
		super();
		this.amount = amount;
		this.transType = transType;
		this.closingBalance = closingBalance;
	}




	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", amount=" + amount + ", openingBalance1="
				+ openingBalance1 + ", closingBalance=" + closingBalance + ", transId=" + transId + ", transType="
				+ transType + ", date=" + date + "]";
	}



	public Transaction(long accountNumber, double amount, double openingBalance1, double closingBalance, 
			String transType) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.openingBalance1 = openingBalance1;
		this.closingBalance = closingBalance;
		//this.transId = transId;
		this.transType = transType;
	}



	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getOpeningBalance1() {
		return openingBalance1;
	}

	public void setOpeningBalance1(double openingBalance1) {
		this.openingBalance1 = openingBalance1;
	}

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getUserId2() {
		return userId2;
	}

	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(closingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(openingBalance1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + transId;
		result = prime * result + ((transType == null) ? 0 : transType.hashCode());
		result = prime * result + ((userId2 == null) ? 0 : userId2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(closingBalance) != Double.doubleToLongBits(other.closingBalance))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(openingBalance1) != Double.doubleToLongBits(other.openingBalance1))
			return false;
		if (transId != other.transId)
			return false;
		if (transType == null) {
			if (other.transType != null)
				return false;
		} else if (!transType.equals(other.transType))
			return false;
		if (userId2 == null) {
			if (other.userId2 != null)
				return false;
		} else if (!userId2.equals(other.userId2))
			return false;
		return true;
	}
	
	
	
}
