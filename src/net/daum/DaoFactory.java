package net.daum;

public class DaoFactory {

	public UserDao getUserDao() {
		return new UserDao(getConnctionMaker());
	}

	private ConnectionMaker getConnctionMaker() {
		return new DConnectionMaker();
	}

}
