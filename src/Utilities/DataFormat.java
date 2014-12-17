package Utilities;


public class DataFormat {
   public DataFormat(){
	  
  }
	public  static String convertToHexString(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
		    int halfbyte = (data[i] >>> 4) & 0x0F;
		    int two_halfs = 0;
		    do {
		        if ((0 <= halfbyte) && (halfbyte <= 9))
		            buf.append((char) ('0' + halfbyte));
		        else
		            buf.append((char) ('a' + (halfbyte - 10)));
		            halfbyte = data[i] & 0x0F;
		        } while(two_halfs++ < 1);
		    }
		return buf.toString();
		}
	
	
	/*
	 * long number to bcd byte array e.g. 123 --> (0000) 0001 0010 0011
	 * e.g. 12 ---> 0001 0010
	 */
	public static byte[] DecToBCDArray(long num) {
		int digits = 0;
 
		long temp = num;
		while (temp != 0) {
			digits++;
			temp /= 10;
		}
 
		int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;
		boolean isOdd = digits % 2 != 0;
 
		byte bcd[] = new byte[byteLen];
 
		for (int i = 0; i < digits; i++) {
			byte tmp = (byte) (num % 10);
 
			if (i == digits - 1 && isOdd)
				bcd[i / 2] = tmp;
			else if (i % 2 == 0)
				bcd[i / 2] = tmp;
			else {
				byte foo = (byte) (tmp << 4);
				bcd[i / 2] |= foo;
			}
 
			num /= 10;
		}
 
		for (int i = 0; i < byteLen / 2; i++) {
			byte tmp = bcd[i];
			bcd[i] = bcd[byteLen - i - 1];
			bcd[byteLen - i - 1] = tmp;
		}
 
		return bcd;
	}
 
	public static String BCDtoString(byte bcd) {
		StringBuffer sb = new StringBuffer();
		
		byte high = (byte) (bcd & 0xf0);
		high >>>= (byte) 4;	
		high = (byte) (high & 0x0f);
		byte low = (byte) (bcd & 0x0f);
		
		sb.append(high);
		sb.append(low);
		
		return sb.toString();
	}
	
	public static String BCDtoString(byte[] bcd) {
 
	StringBuffer sb = new StringBuffer();
 
	for (int i = 0; i < bcd.length; i++) {
		sb.append(BCDtoString(bcd[i]));
	}
 
	return sb.toString();
	}
	
	//////////////////////////////////////////////////////////////
	public int GetBytetoInt(int byteValue)
	{
	byte intValue=0;   
	int temp = byteValue % 256;   
	if ( byteValue < 0) {   
	intValue =  (byte)(temp < -128 ? 256 + temp : temp);   
	}   
	else {   
	intValue =  (byte)(temp > 127 ? temp - 256 : temp);   
	}  
	return intValue;
	}
	
	public static int byteArrayToInt(byte[] b) 
	{
	int value = 0;
	for (int i = 0; i < 4; i++) {
	int shift = (4 - 1 - i) * 8;
	value += (b[i] & 0x000000FF) << shift;
	}
	return value;
	}
	
	public static byte[] intToByteArray(int a)
	{
	byte[] ret = new byte[4];
	ret[0] = (byte) (a & 0xFF);   
	ret[1] = (byte) ((a >> 8) & 0xFF);   
	ret[2] = (byte) ((a >> 16) & 0xFF);   
	ret[3] = (byte) ((a >> 24) & 0xFF);
	return ret;
	}
	
	public static String AsciiToBinary(String asciiString){  
	
	byte[] bytes = asciiString.getBytes();  
	StringBuilder binary = new StringBuilder();  
	for (byte b : bytes)  
	{  
	int val = b;  
	for (int i = 0; i < 8; i++)  
	{  
	binary.append((val & 128) == 0 ? 0 : 1);  
	val <<= 1;  
	}  
	// binary.append(' ');  
	}  
	return binary.toString();  
	}  
}