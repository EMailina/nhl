package com.league.nhl.league.enums;

public enum Owner {
	ERIK("Šteniak"), 
	ZUZKA("Lásočiatko");

	private String nickName;

	Owner(String nickname) {
		this.nickName = nickname;
	}

	public String getNickName() {
		return nickName;
	}

}
