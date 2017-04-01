

function generateFromObject (input) {
	var output = input + " blaObject";
	return output;
}

function generateFromType (input) {
	var output = input + " blaType";
	return output;
}


function generateDbmsOuput (){
	var input = document.getElementById('inputText').value;
	var fromObject = document.getElementById('codeOriginObject').checked;
	var fromType = document.getElementById('codeOriginType').checked; 
	var withLoop = document.getElementById('withLoop').value; 
	var indexName = document.getElementById('indexName').value; 
	var varName = document.getElementById('variableName').value; 
    
	var output = input + " bla";
	
	if (fromObject) {
		output = generateFromObject(input);
	} 
	
	if (fromType){
		output = generateFromType(input);
	}
	
	document.getElementById('outputText').innerHTML = output;

}