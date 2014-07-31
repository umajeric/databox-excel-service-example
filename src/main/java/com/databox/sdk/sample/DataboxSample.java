package com.databox.sdk.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.databox.sdk.DataSink;
import com.databox.sdk.ResponseWrapper;
import com.databox.sdk.impl.DataboxCustomConnection;
import com.databox.sdk.impl.DataboxSink;

/**
 *
 * @author Uros Majeric
 *
 */
public class DataboxSample {
	private static final Logger logger = LoggerFactory.getLogger(DataboxSample.class);

	public static void main(String[] args) throws Exception {

		if (args.length < 2) {
			logger.error("Access Token and Source Token have to be provided.");
			return;
		}
		String accessToken = args[0];
		String sourceToken = args[1];

		if (accessToken == null || accessToken.isEmpty() || sourceToken == null || sourceToken.isEmpty()) {
			logger.error("Access Token and Source Token must not be empty.");
			return;
		}
		DataSink<DataboxCustomConnection> sink = new DataboxSink(accessToken);

		DataboxCustomConnection connection = new DataboxCustomConnection(sourceToken);
		XSLDailyDataProvider xlsxDataProvider = new XSLDailyDataProvider("cycling.xlsx");
		connection.addDataProvider(xlsxDataProvider);

		// DefaultDataProvider dataProvider = new DefaultDataProvider();
		// Calendar c = new GregorianCalendar();
		// dataProvider.addKPI(new KPI.Builder().setKey("visits_this_month").setValue(234D).setDate(c.getTime()).build());
		// c.add(Calendar.DAY_OF_MONTH, -1);
		// dataProvider.addKPI(new KPI.Builder().setKey("visits_this_month").setValue(431D).build());
		// connection.addDataProvider(dataProvider);

		ResponseWrapper response = sink.push(connection);
		if (!response.isSucceeded()) {
			logger.error(response.getMessage());

			/* try to get more descriptive error message */
			String logs = sink.getLogs(connection);
			if (logs != null) {
				logger.error(logs);
			} else {
				logger.error("No logs can be found on server, please check access token and source token.");
			}
		} else {
			logger.info(response.getMessage());
		}
	}
}
