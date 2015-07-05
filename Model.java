import java.util.Observable;
import java.util.Vector;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.*;

public class Model extends Observable{	
	public static int NUM_LETTERS = 5;
	public static final String[] LEVELS = {
		"Any Difficulty", "Easy", "Medium", "Hard"};
	private static Vector<String> easy = new Vector<String>();
	private static Vector<String> medium = new Vector<String>();
	private static Vector<String> hard = new Vector<String>();
	public String word = "CARDS";
	public boolean random = true;
	public int level = 0;
	public boolean single = false;
	public int player = 1;
	public int tries1 = 0;
	public String[] singleCol = {"GUESS","EXACT MATCH","PARTIAL MATCH"};
	public Vector<String> singleData = new Vector<String>();
	public Object[] rowData = {"ini",0,0};
	public boolean[] letterGuessed = {false,false,false,false,false,false,false,false,false,false,
									  false,false,false,false,false,false,false,false,false,false,
									  false,false,false,false,false,false};
	
	
	
	// the data in the model, just a counter
	private int counter;	
	
	Model(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("words.txt"));
			String line;
			while((line = br.readLine()) != null){
				switch(line.split(" ")[1]){
					case "0":
						easy.add(line.split(" ")[0]);
						break;
					case "1":
						medium.add(line.split(" ")[0]);
						break;
					case "2":
						hard.add(line.split(" ")[0]);
						break;
				}
			}
			start();
		}catch(Exception e){}
	}
	
	public int getPartial(){
		return 5;
	}
	
	public int getCounterValue() {
		return counter;
	}
	
	public void incrementCounter() {
		counter++;
		System.out.println("Model: increment counter to " + counter);
		setChanged();
		notifyObservers();
	} 	
	
	public void setLevel(int lvl){
		this.level = lvl;
	}
	
	public boolean setWord(String word){
		if (easy.contains(word)||medium.contains(word)||hard.contains(word)){
			this.word = word;
			return true;
		}
		else return false;
	}
	
	public boolean alreadyGuessed(String word){
		return singleData.contains(word);
	}
	
	public boolean guess(int player,String guess){
		tries1++;
		singleData.add(guess);
		rowData[0] = guess;
		rowData[1] = 0;
		rowData[2] = 0;
		
		char[] guessChar = guess.toCharArray();
		char[] wordChar = this.word.toCharArray();
		
		for(int i = 0; i < 5; i++){
			if (guessChar[i] == wordChar[i]){
				rowData[1] = (int)rowData[1] + 1;
				wordChar[i] = '.';
			}
		}
		
		for (int i = 0; i < 5; i++){		
			for (int j = 0; j < 5; j++){
				if (guessChar[i] == wordChar[j]){
					rowData[2] = (int)rowData[2] + 1;
					wordChar[j] = '.';
					break;
				}
			}
		}
		
		
		for (char c : guess.toCharArray()){
			if (!letterGuessed[(int)c -65]){
				letterGuessed[(int)c - 65] = true;
			}
		}
		
		setChanged();
		notifyObservers();
		if (guess.equals(this.word))return true;
		return false;
	}
	
	public boolean inDictionary(String guess){
		return easy.contains(guess)||medium.contains(guess)||hard.contains(guess);
	}
	
	public void start(){
		
		Random r = new Random();
		int index = r.nextInt(2);
		if (!random) index = level;
		switch (index){
			case 0:
				this.word = easy.get(r.nextInt(easy.size() - 1));
				break;
			case 1:
				this.word = medium.get(r.nextInt(medium.size() - 1));
				break;
			case 2:
				this.word = hard.get(r.nextInt(hard.size() - 1));
				break;
		}
		System.out.println(this.word);
		tries1 = 0;
		singleData = new Vector<String>();
		rowData[0] = "ini";
		rowData[1] = 0;
		rowData[2] = 0;
		for (int i = 0; i< 26;i++){
				letterGuessed[i] = false;
		}
		setChanged();
		notifyObservers();
	}
}
