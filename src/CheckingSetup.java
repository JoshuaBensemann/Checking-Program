import java.text.DecimalFormat;
import java.util.Random;

public class CheckingSetup {
	public static double getRandomValue() {
		Random doubleGenerator = new Random();
		DecimalFormat df = new DecimalFormat("#.##");    
		
		double randomValue = 100 + (999.99 - 100) * doubleGenerator.nextDouble();     
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

	}

}
