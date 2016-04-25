package tools;

import java.math.BigInteger;

public class Int extends BigInteger {
	private static final long serialVersionUID = 1L;
	//constructures
	public Int(){
		super("0");
	}
	public Int(Integer i){
		super(i+"");
	}
	public Int(Long l){
		super(l+"");
	}
	public Int(Int I){
		super(I+"");
	}
	public Int(BigInteger b){
		super(b+"");
	}
	public Int(String  s){
		super(s+"");
	}
	public Int(String s, int base){
		this(getInt(s,base));
	}
	private static Int getInt(String s,int base){
		Int power = new Int(1);
		Int value = new Int(0);
		int N = s.length();
		for(int i=N-1;i>=0;i--){
			char ci = s.charAt(i);
			value = value.add(new Int(ci+"").multiply(power));
			power = power.multiply(base);
		}
		return value;
	}
	//operations
	public Int add(Integer i){
		return add(new Int(i));
	}
	public Int add(Long l){
		return add(new Int(l));
	}
	public Int add(BigInteger b){
		return new Int(super.add(b));
	}
	public Int minus(Integer i){
		return minus(new Int(i));
	}
	public Int minus(Long l){
		return minus(new Int(l));
	}
	public Int minus(BigInteger b){
		return new Int(subtract(b));
	}
	public Int multiply(Integer i){
		return multiply(new Int(i));
	}
	public Int multiply(Long l){
		return multiply(new Int(l));
	}
	public Int multiply(BigInteger b){
		return new Int(super.multiply(b));
	}
	public Int divide(Integer i){
		return divide(new Int(i));
	}
	public Int divide(Long l){
		return divide(new Int(l));
	}
	public Int divide(BigInteger b){
		return new Int(super.divide(b));
	}
	public Int mod(Integer i){
		return mod(new Int(i));
	}
	public Int mod(Long l){
		return mod(new Int(l));
	}
	public Int mod(BigInteger b){
		return new Int(super.mod(b));
	}
	public Int gcd(Integer i){
		return gcd(new Int(i));
	}
	public Int gcd(Long l){
		return gcd(new Int(l));
	}
	public Int gcd(BigInteger b){
		return new Int(super.gcd(b));
	}
	public String toBinaryString(){
		return toString(2);
	}
}
