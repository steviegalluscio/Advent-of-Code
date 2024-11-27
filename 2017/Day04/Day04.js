function countValidPassphrases(passphraseList){
	var passphrases = passphraseList.split(/\n/g),
  i = passphrases.length,
  result = 0;

  while(i--){
  	var words = passphrases[i].split(" ");
    var set = new Set(words);
    if(words.length == set.size) result += 1;
  }
  return result;
}

function countNonAnagrams(passphraseList){
	var passphrases = passphraseList.split(/\n/g),
  i = passphrases.length,
  result = 0;

  while(i--){
  	var words = passphrases[i].split(" ");
    var j = words.length;
    while(j--){
    	words[j] = words[j].split("").sort().join("");
    }
    var set = new Set(words);
    if(words.length == set.size) result += 1;
  }
  return result;
}

var input = `
`;

console.log(countValidPassphrases(input));

console.log(countNonAnagrams(input));
