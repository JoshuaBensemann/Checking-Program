import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class CheckingSetup {
	public static double getRandomValue() {
		Random doubleGenerator = new Random();
		DecimalFormat df = new DecimalFormat("#.##");    
		
		double randomValue = 10 + (999.99 - 10) * doubleGenerator.nextDouble();     
		randomValue = Double.valueOf(df.format(randomValue));

		return randomValue;
		
	}
	
	public static String valueToString(double value) {
		String converted_s = WordConverter.convert((int) value);
		String cents_s = String.format("%.2f", value%1);
		cents_s = (cents_s + "/100").substring(2);
		
		if (cents_s.charAt(0) == '0') {
			cents_s = cents_s.substring(1);
		}
		
		return (converted_s + " and " + cents_s).replaceAll("  ", " ");
	}

	public static void main(String[] args) {
	/*
		DataRecorder logger = null;
		
		try {
			logger = new DataRecorder("test.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < 5; i++) {
			long system_start = System.currentTimeMillis();
			double value = getRandomValue();
			double inputDouble = getInputValue();
			long system_end = System.currentTimeMillis();
			
			boolean result = value == inputDouble;
			System.out.println("Trial Result:" + result);
			String[] data = {Double. toString(value), Double. toString(inputDouble), Boolean.toString(result), Long.toString(system_end-system_start)};
			logger.recordEvent(data);
		}
		
		logger.stopRecording();
	*/	
	}

}
