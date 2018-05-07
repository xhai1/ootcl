package com.zeng.ocs.po;

import java.util.List;

public class MoneyStandardModel {

	 private List<MoneyStandard> moneyStandard;

	 


		public List<MoneyStandard> getMoneyStandard() {
		return moneyStandard;
	}

	public void setMoneyStandard(List<MoneyStandard> moneyStandard) {
		this.moneyStandard = moneyStandard;
	}

		public MoneyStandardModel(List<MoneyStandard> moneyStandard) {
	        super();
	        this.moneyStandard = moneyStandard;
	    }

	    public MoneyStandardModel() {
	        super();
	    }
}
