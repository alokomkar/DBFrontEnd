package mtechproject.samples;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class AccountInfoService extends Service<ObservableList<AccountInfo>> implements Runnable {

	@Override
	protected Task createTask() {

		return new AccountInfoTask();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

