package com.java.softsec.dao;

import java.util.List;

import com.java.softsec.models.AccountSummary;

public interface AccountSummaryDAO {

	List<AccountSummary> getTransact(int fromAccount);
}
