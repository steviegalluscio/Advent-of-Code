var input = document.getElementById("input").textContent;

//--- Part One ---
function leastAndGreatestChecksum(spreadsheet){
	var i = 0, max = 0, min = 0, result = 0,
	row = spreadsheet.split(/\n/g),
	values = [],
	length = row.length;
  
	for (i = 0; i < length; i++){
		values = row[i].split("	");
		max = Math.max.apply(Math, values);
		min = Math.min.apply(Math, values);
		result += max - min;
	}   
 	return result;  
}

console.log(leastAndGreatestChecksum(input));


//--- Part Two ---
function divisibleValuesChecksum(spreadsheet){
	var i = 0, j = 0, k = 0, result = 0,
	row = spreadsheet.split(/\n/g),
	items = [],
	length = row.length;
  
	for (i = 0; i < length; i++){
		items = row[i].split("	");
		loop2:
		for (j = 0; j < items.length; j++){
			for (k = 0; k < items.length; k++){
				if(items[j] !== items[k] && items[j] % items[k] == 0){
					result += items[j] / items[k];
					break loop2;
				}
			}
		} 
	}
	return result;  
}

console.log(divisibleValuesChecksum(input));