package mtechproject.samples;

public class AccountInfo {

	String Username;
	String Locked;

	public AccountInfo() {

	}

	public AccountInfo(String Username, String Locked) {

		this.Username = Username;
		this.Locked = Locked;

	}

	public String getUsername() {

		return Username;

	}

	public void setUsername(String Username) {

		this.Username = Username;

	}

	public String getLocked() {

		return Locked;

	}

	public void setLocked(String Locked) {

		this.Locked = Locked;

	}

}

