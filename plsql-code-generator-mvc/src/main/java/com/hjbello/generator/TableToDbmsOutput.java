package com.hjbello.generator;

public class TableToDbmsOutput {
	private boolean withLoop = true;
	private String variableName = "result";
	private String indexName = "i";
	
	public String convert(String input){
		String str;
		String output= "";
		//reemplazamos
		str= input.replaceAll("SUBTYPE\\s\\S+"," ").replaceAll(" IS "," ").replaceAll("TYPE\\s\\S+"," ");
		str= str.replaceAll(" RECORD","");
		str= str.replaceAll(" TABLE ","").replaceAll("IS RECORD","").replaceAll("TYPE", "");
		str= str.replaceAll("\n", " ").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(";","");
		str= str.replaceAll("\\s+"," ");
		
		String startLoop = indexName + " := " + variableName +".first();\nWHILE "+ variableName +".exists(" + indexName + ") LOOP\n";
		String endLoop =  " dbms_output.put_line('------| '||" + indexName + "||' |------'"+ ");\n" 
				+" dbms_output.put_line('');\n"
				+ indexName + " := " + variableName + ".next(" + indexName + ");"
				+ "\nEND LOOP;";          
		
		
		String preDbmsOutput = " dbms_output.put_line('";
		String concatenate = " '  || ";
		String postDbmsOutput = " );";
		
		
		for (String word: str.split(",")){
			if (!word.trim().equals("")){
				if (withLoop){
				output= output + preDbmsOutput + word.trim().split(" ")[0].trim() + concatenate 
						+ variableName +"(" + indexName + ")" + "."+ word.trim().split(" ")[0].trim()+ postDbmsOutput + "\n";
				} else {
					output= output + preDbmsOutput + word.trim().split(" ")[0].trim() + concatenate 
							+ variableName + "."+ word.trim().split(" ")[0].trim()+ postDbmsOutput + "\n";	
				}
			}
		}
		TransformToTable transformer = new TransformToTable();
		output = transformer.obtainStringTable(output);
		if (withLoop){output = startLoop + output+ endLoop;}
		return output;
	}

	public boolean isWithLoop() {
		return withLoop;
	}

	public void setWithLoop(boolean withLoop) {
		this.withLoop = withLoop;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

}
