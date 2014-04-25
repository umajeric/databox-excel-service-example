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
		DataSink<DataboxCustomConnection> sink = new DataboxSink();

		if (args.length < 2) {
			logger.error("API Key and APP ID have to be provided.");
			return;
		}
		String apiKey = args[0];
		String appId = args[1];

		if (apiKey == null || apiKey.isEmpty() || apiKey == null || apiKey.isEmpty()) {
			logger.error("API Key and APP ID must not be empty.");
			return;
		}
		DataboxCustomConnection connection = new DataboxCustomConnection(apiKey, appId);
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
			}
		} else {
			logger.info(response.getMessage());
		}
	}
}
