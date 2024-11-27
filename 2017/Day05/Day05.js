function jumpListStepCounter(list){
	var i = 0,
  steps = 0;
  offsets = list.split(/\n/g);
  
  while(i < offsets.length){
  	steps++;
  	offsets[i]++;
  	i += offsets[i] - 1;
  }
  return steps;
}

function strangeJumpListStepCounter(list){
	var i = 0,
  steps = 0;
  offsets = list.split(/\n/g);
  
  while(i < offsets.length){
  	steps++;
    offsets[i] >= 3 ? (offsets[i]--, i += offsets[i] + 1) : (offsets[i]++, i += offsets[i] - 1);
  }
  return steps;
}

var input = `
`;

console.log(jumpListStepCounter(input));

console.log(strangeJumpListStepCounter(input));
