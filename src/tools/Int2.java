package tools;

import java.math.BigInteger;


public class Int2 implements Comparable<Int2> {
		private BigInteger v;
		public Int2(){
			v = new BigInteger("0");
		}
		public Int2(Integer i){
			v = new BigInteger(i+"");
		}
		public Int2(Long l){
			v = new BigInteger(l+"");
		}
		public Int2(Int2 I){
			v = new BigInteger(I+"");
		}
		public Int2(BigInteger b){
			v = new BigInteger(b+"");
		}
		public Int2(String  s){
			v = new BigInteger(s+"");
		}
		//operations, add, minus, mul, divde, mod, gcd
		public Int2 add(Int2 i){
			Int2 x = new Int2(this.v.add(i.v));
			return x;
		}
		public Int2 add(Integer i){
			return add(new Int2(i));
		}
		public Int2 add(Long l){
			return add(new Int2(l));
		}
		public Int2 add(BigInteger b){
			return add(new Int2(b));
		}
		
		public Int2 minus(Int2 i){
			Int2 x = new Int2(this.v.subtract(i.v));
			return x;
		}
		public Int2 minus(Integer i){
			return minus(new Int2(i));
		}
		public Int2 minus(Long l){
			return minus(new Int2(l));
		}
		public Int2 minus(BigInteger b){
			return minus(new Int2(b));
		}
		
		public Int2 multiply(Int2 i){
			Int2 x = new Int2(this.v.multiply(i.v));
			return x;
		}
		public Int2 multiply(Integer i){
			return multiply(new Int2(i));
		}
		public Int2 multiply(Long l){
			return multiply(new Int2(l));
		}
		public Int2 multiply(BigInteger b){
			return multiply(new Int2(b));
		}
		
		public Int2 divide(Int2 i){
			Int2 x = new Int2(this.v.divide(i.v));
			return x;
		}
		public Int2 divide(Integer i){
			return divide(new Int2(i));
		}
		public Int2 divide(Long l){
			return divide(new Int2(l));
		}
		public Int2 divide(BigInteger b){
			return divide(new Int2(b));
		}
		public Int2 mod(Int2 i){
			Int2 x = new Int2(this.v.mod(i.v));
			return x;
		}
		public Int2 mod(Integer i){
			return mod(new Int2(i));
		}
		public Int2 mod(Long l){
			return mod(new Int2(l));
		}
		public Int2 mod(BigInteger b){
			return mod(new Int2(b));
		}
		public Int2 gcd(Int2 i){
			Int2 x = new Int2(this.v.gcd(i.v));
			return x;
		}
		public Int2 gcd(Integer i){
			return gcd(new Int2(i));
		}
		public Int2 gcd(Long l){
			return gcd(new Int2(l));
		}
		public Int2 gcd(BigInteger b){
			return gcd(new Int2(b));
		}
		//end of operations
		int intValue(){
			return v.intValue();
		}
		long longValue(){
			return v.longValue();
		}
		
		public String toBinaryString(){
			return v.toString(2);
		}
		public String toString(){
			return v.toString();
		}
		public int compareTo(Int2 arg0) {
			return this.v.compareTo(arg0.v);
		}
		public int hashCode(){
			return v.hashCode();
		}
}
