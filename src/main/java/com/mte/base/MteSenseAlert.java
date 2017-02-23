package com.mte.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.security.Credentials;

public class MteSenseAlert implements Alert {
	private final Alert alert;

	public MteSenseAlert(Alert alert) {
		this.alert = alert;
	}


	@Override
	public void dismiss() {
		alert.dismiss();
	}

	@Override
	public void accept() {
		alert.accept();
	}

	@Override
	public String getText() {
		return alert.getText();
	}

	@Override
	public void sendKeys(String keysToSend) {
		alert.sendKeys(keysToSend);
	}

	@Override
	public void authenticateUsing(Credentials credentials) {
		alert.authenticateUsing(credentials);
	}

	@Override
	public void setCredentials(Credentials arg0) {
		// TODO Auto-generated method stub
	
	}
}