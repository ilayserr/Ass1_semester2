/*
Assignment number : 1.3
File Name : Bits.java
Name : Ilay Serr
Email : ilay92@gmail.com
 */

public class Bits {

	/**
	 * Given an 8-byte long composed of the bytes B_1, B_2, ... , B_8, return the long with byte order reversed:
	 * B_8, B_7, ..., B_1
	 * The implementation of this method shouldn't use any function calls.
	 * @param a the number to reverse
	 * @return
	 */
	public static long byteReverse(long a) {
		long result = 0;
        long msbHelper;
		for (int i = 0; i < 8; i++) {
			result >>>= 8;
		    msbHelper = a << (i * 8);
		    msbHelper &= 0xff00000000000000L;
		    result |= msbHelper;
		}
		return result;
	}
	
	/**
	 * Given a 32-bit integer composed of 32 bits: b_31,b_30,...b_1,b_0,  return the integer whose bit representation is
	 * b_{31-n},b_{30-n},...,b_1,b_0,b_31,...,b_{31-n+1}. 
	 * The implementation of this method shouldn't use any control structures (if-then, loops) or function calls.
	 * @param a the integer that we are rotating left (ROLing)
	 * @param n the number of bits to rotate.
	 * @return the ROL of a
	 */
	public static int rol(int a, int n) {
		int b = a >>> (32 - n);
		a = a << n;
		return (a | b);
	}

	/**
	 * Given two 32-bit integers a_31,...,a_0 and b_31,...,b_0, return the 64-bit long that contains their bits interleaved:
	 * a_31,b_31,a_30,b_30,...,a_0,b_0.
	 * The implementation of this method shouldn't use any function calls.
	 * @param a
	 * @param b
	 * @return
	 */
	public static long interleave(int a, int b) {
		long result = 0;
		int c = 1;
		for (int i = 0; i < 32; i++) {
			long d = b & c;
			long e = a & c;
			d <<= i;
			e <<= (i + 1);
			result = (result + e + d);
			c <<= 1;
		}
		return result;
	}
	
	/**
	 * Pack several values into a compressed 32-bit representation. 
	 * The packed representation should contain
	 * <table>
	 * <tr><th>bits</th>	<th>value</th></tr>
	 * <tr><td>31</td>		<td>1 if b1 is true, 0 otherwise</td></tr>
	 * <tr><td>30-23</td>	<td>the value of the byte a</td></tr>
	 * <tr><td>22</td>		<td>1 if b2 is true, 0 otherwise</td></tr>
	 * <tr><td>21-6</td>	<td>the value of the char c</td></tr>
	 * <tr><td>5-0</td>		<td>the constant binary value 101101</td></tr>
	 * </table>
	 * The implementation of this method shouldn't use any control structures (if-then, loops) or function calls
	 * (you may use the conditional operator "?:").
	 * @param a
	 * @param b1
	 * @param b2
	 * @param c
	 * @return
	 */
	public static int packStruct(byte a, boolean b1, boolean b2, char c) {
		int result = b1 ? 1 : 0;
		result <<= 8;
		result |= (a & 0xff);
		result <<= 1;
		result += b2 ? 1 : 0;
		result <<= 16;
		result |= (c & 0xffff);
		result <<= 6;
		result |= (0b101101);
		return result;
	}
	
	/**
	 * Given a packed struct (with the same format as {@link #packStruct(byte, boolean, boolean, char)}, update
	 * its byte value (bits 23-30) to the new value a.
	 * The implementation of this method shouldn't use any control structures (if-then, loops) or function calls.
	 * @param struct
	 * @param a
	 * @return
	 */
	public static int updateStruct(int struct, byte a) {
		int temp = struct >>> 31;
		struct <<= 9;
		struct >>>= 9;
		temp <<= 8;
		temp += (a & 0xff);
		temp <<= 23;
		return (temp | struct);
	}
}
