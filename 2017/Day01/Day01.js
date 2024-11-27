var input = "";

//--- Part One ---
function inverseCaptcha(sequence){
	var i = 0,
	length = sequence.length,
	result = 0;
  
	for (i = 0; i < length; i++){
		if (sequence.charAt(i) == sequence.charAt(i+1)){
			result += 1*sequence.charAt(i);
		}
	} 
	if (sequence.charAt(length-1) == sequence.charAt(0)){
		result += 1*sequence.charAt(length-1);
	}
	return result;  
}

console.log(inverseCaptcha(input));


//--- Part Two ---
function circularInverseCaptcha(sequence){
	var i = 0,
  	length = sequence.length,
  	halfLength = length/2,
  	result = 0;
  
  for (i = 0; i < length; i++){
  	if (sequence.charAt(i) == sequence.charAt((i+halfLength)%length)){
			result += 1*sequence.charAt(i);
  	}
  }
  
 	return result;  
}

console.log(circularInverseCaptcha(input));
