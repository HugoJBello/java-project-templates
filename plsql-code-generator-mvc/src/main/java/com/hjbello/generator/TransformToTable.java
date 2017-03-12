package com.hjbello.generator;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformToTable {
	private String emptyCellMark = " ";
	private boolean withEndOfLineMark = true;
	private String endOflineMark = ";";
	private String commentedLineMark = "--";
	private String changeOfColumnMark = " ";
	private boolean withLeftAling = true;

	private int numberInitialSpaces =0;
	private int numberOfColumns=1;
	private int numberOfRows =1;
	private ArrayList<ArrayList<String>> outputList = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> inputList = new ArrayList<ArrayList<String>>();

	public void obtainInputList (String input){
		float meanSpaces = 0;
		String[]  linesInPutRaw;
		linesInPutRaw = input.split("\n");
		numberOfRows = linesInPutRaw.length;
		Pattern p3 = Pattern.compile("^\\s+");
		for (String lineRead:  linesInPutRaw){
			Matcher m = p3.matcher(lineRead); 
			if (m.find()) {  
				String spaces = m.group(0).replace(" ", "-");
				meanSpaces= meanSpaces+ spaces.length();
			}
			if (! lineRead.replaceAll("\\s+", "").equals("")){
				lineRead=lineRead.trim();
				if (withEndOfLineMark){
					lineRead= lineRead.replaceAll("\\s"+ endOflineMark+"$",  endOflineMark );
				}
				ArrayList<String> line = new ArrayList<String>();
				inputList.add(line);
				if (changeOfColumnMark.equals(" ")){
					for (String word : lineRead.replaceAll("\\s+", " ").split(" ")){
						line.add(word);
					}
				} else {
					for (String word : lineRead.split(changeOfColumnMark)){
						line.add(word);
					}	
				}
			} else {
				ArrayList<String> line = new ArrayList<String>();
				line.add(emptyCellMark);
				inputList.add(line);;
			}
		}
		meanSpaces= meanSpaces/numberOfRows;
		double f =   Math.round(meanSpaces * 1d) / 1d;
		numberInitialSpaces =  (int) Math.ceil(f);
	}

	public void calculateNumCols(){
		int maxNumWords = 1;
		for (ArrayList<String> line: inputList){
			if (line.size()> maxNumWords){
				maxNumWords = line.size();
			}
		}
		numberOfColumns=maxNumWords;
	}
	
	public void calculateNum(){
		int maxNumWords = 1;
		for (ArrayList<String> line: inputList){
			if (line.size()> maxNumWords){
				maxNumWords = line.size();
			}
		}
		numberOfColumns=maxNumWords;
	}
	
	public void prepareInputList(){
		numberOfRows = inputList.size();
		for (ArrayList<String> line : inputList){
			if (withEndOfLineMark){
				line.set(line.size()-1, line.get(line.size()-1).replaceAll(endOflineMark, "") );
			}
			while (line.size()< numberOfColumns){
				line.add(emptyCellMark);
			}
		}
	}
	public void addEndOfLine(){
		if (withEndOfLineMark){
			int max = 1;
			for (ArrayList<String> line: outputList){
				if (line.get(line.size()-2).length()>max){max= line.get(line.size()-2).length();}

			}
			max = max -1;
			for (ArrayList<String> line: outputList){
				String s = line.get(line.size()-1).replaceAll(";", "");
				line.set(line.size()-1, s);
				boolean Empty = false;
				for (String word: line){
					Empty = Empty | !word.trim().equals(emptyCellMark.trim());
				} 
				if (!line.get(0).contains(commentedLineMark) & Empty){
					line.set(line.size()-1, line.get(line.size()-1)+endOflineMark);
				}
			}
		}
	}

	public void obtainOutputList(){
		//Now we prepare the output list for later work. We also take care of the last endOfLine characters.
		prepareInputList();
		for (int i=0; i < inputList.size(); i++){ 
			ArrayList<String> lineOut = new ArrayList<String>();			
			outputList.add(lineOut);
		}


		//We will process each word from the input one by one.
		for (int wordNumber=0; wordNumber < numberOfColumns;){
			// here we add the current word into the ouput. 
			// We also calculate the max of the lengths of the elements of the 
			// each column
			int biggestLength = 1;
			for (int i=0; i<numberOfRows; i++){
				ArrayList<String> line=inputList.get(i);
				String CurrentWord = line.get(wordNumber);
				ArrayList<String> lineOut = outputList.get(i);
				lineOut.add(CurrentWord);
				if (CurrentWord.length() > biggestLength){
					biggestLength = CurrentWord.length(); 	}			

			}
			//we  now  add spaces to the output
			for (int i=0; i<numberOfRows; i++){
				String word =inputList.get(i).get(wordNumber); 
				int numberOfSpacesToAdd =1;
				if ((wordNumber==numberOfColumns-1)& withEndOfLineMark){
					numberOfSpacesToAdd = biggestLength - word.length();
				} else {
					numberOfSpacesToAdd = biggestLength - word.length() + 1;
				}
				String spaces = new String(new char[numberOfSpacesToAdd]).replace("\0", " ");
				outputList.get(i).set(wordNumber,word+spaces);
				//outputList.get(i).add(spaces);
			}
			wordNumber = wordNumber+1;
		}
	}

	public String obtainStringTable(String input){
		obtainInputList(input);
		calculateNumCols();
		obtainOutputList();
		//System.out.println(inputList.toString());
		//System.out.println(outputList.toString());
		addEndOfLine();
		//System.out.println(outputList.toString());
		String output = "";
		for (ArrayList<String> line: outputList){
			if (withLeftAling){
				String initialSpaces = new String(new char[numberInitialSpaces]).replace("\0", " ");
				output= output + initialSpaces;
			}
			String s = "";

			for (String word: line){
				s = s + word;
			}
			output = output + s+ "\n";
		}
		return output;
	}

	public String getEmptyCellMark() {
		return emptyCellMark;
	}

	public void setEmptyCellMark(String emptyCellMark) {
		this.emptyCellMark = emptyCellMark;
	}

	public boolean isWithEndOfLineMark() {
		return withEndOfLineMark;
	}

	public void setWithEndOfLineMark(boolean withEndOfLineMark) {
		this.withEndOfLineMark = withEndOfLineMark;
	}

	public String getEndOflineMark() {
		return endOflineMark;
	}

	public void setEndOflineMark(String endOflineMark) {
		this.endOflineMark = endOflineMark;
	}

	public String getCommentedLineMark() {
		return commentedLineMark;
	}

	public void setCommentedLineMark(String commentedLineMark) {
		this.commentedLineMark = commentedLineMark;
	}

	public String getChangeOfColumnMark() {
		return changeOfColumnMark;
	}

	public void setChangeOfColumnMark(String changeOfColumnMark) {
		this.changeOfColumnMark = changeOfColumnMark;
	}

	public boolean isWithLeftAling() {
		return withLeftAling;
	}

	public void setWithLeftAling(boolean withLeftAling) {
		this.withLeftAling = withLeftAling;
	}
}
