import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Location {
	static byte[] byteArray = new byte[10];
	public static ArrayList<Byte> location = new ArrayList<>(10);
	static {
		for(int i=0;i<10;i++){
			location.add((byte)0);
		}
	}
	public static int ptr = 0;
	
	public static void moveNext() {
		
		ptr+=1;
		//System.out.println("Location increased and is:" + ptr);
		if(ptr==location.size()){
			location.add((byte) 0);
		}
	}
	
	public static void movePrev() throws Exception {
		
		ptr-=1;
		//System.out.println(ptr);
		if(ptr<0){
			throw new Exception();
		}
	}
	public static void increment() {
		location.set(ptr, (byte) (location.get(ptr)+1));
	}
	public static void decrement() {
		location.set(ptr, (byte) (location.get(ptr)-1));
	}
	public static Byte getOutput(){
		return location.get(ptr);
	}
	public static void getInput() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		location.add((byte)br.read());
	}
}
