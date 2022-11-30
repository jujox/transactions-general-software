package com.codetest.transactions.domain.status;

import com.codetest.transactions.domain.dataobjects.Channel;

public class GetTransactionStatusFactory {
	public static GetTransactionStatus getTransactionStatus(Channel channel) {
		if (channel == Channel.ATM) {
			return new GetTransactionStatusForAtm();
		}
		if (channel == Channel.CLIENT) {
			return new GetTransactionStatusForClient();
		}
		if (channel == Channel.INTERNAL) {
			return new GetTransactionStatusForInternal();
		}
		return null;
	}
}
