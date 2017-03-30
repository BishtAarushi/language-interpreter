import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Interpreter {
	public static void main(String[] args) throws Exception {

		try {
			String input = "";
			BufferedReader br  = new BufferedReader(new FileReader(args[0]));
			char[] buffer = new char[100];
			int r = 0;
			while((r=br.read(buffer)) != -1){
				for(int i=0;i<r;i++){
					if("<>[],'+-.".indexOf(buffer[i]) != -1){
						input += buffer[i];
					}
				}
			}
			interpretInput(input);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void interpretInput(String input) throws Exception {

			char[] charArray = input.toCharArray();
			for(int j=0;j<charArray.length;j++){
				switch(charArray[j]) {
					case '>': 
							Location.moveNext();
							break;
					case '<':
							Location.movePrev();
							break;
					case '+':
							Location.increment();
							break;
					case '-':
							Location.decrement();
							break;
					case '.':
							byte[] output = new byte[]{Location.getOutput()};
							System.out.print(new String(output,"UTF-8"));
							break;
					case ',':
							Location.getInput();
							break;
					case '[':
							j = handleLoopOpening(charArray,j);
							break;
					case ']':
							j = handleLoopClosing(charArray,j);
							break;
				}
		}
	}
	
	private static int handleLoopOpening(char[] charArray,int startj) throws Exception{
		int count=0;
		if(Location.getOutput() == 0){
				for(int j=startj+1;j<charArray.length;j++){
					if(charArray[j] == '['){
						count++;
					}
					if(charArray[j] == ']'){
						
						if(count==0){
							return j;
						}
						count--;
					}
				}
			throw new Exception();
	}
		return startj;
	}

	private static int handleLoopClosing(char[] charArray, int endj) throws Exception {
		if(Location.getOutput() != 0){
				int count=0;
				for(int j=endj-1;j>=0;j--){
					if(charArray[j] == ']'){
						count++;
					}
					if(charArray[j] == '['){
						
						if(count==0){
							return j;
						}
						count--;
					}
				}
				throw new Exception();
		}
			
		return endj;
	}
}

