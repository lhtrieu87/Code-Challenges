function processData(input) {
  input = input.split('\n');
  var split = input.shift().split(' ');
  var N = parseInt(split[0]);
  var I = parseInt(split[1]);
  var pairs = [];
  for(var i = 0; i < I; i++) {
    split = input.shift().split(' ');
    pairs.push([parseInt(split[0]), parseInt(split[1])]);
  }

  function nC2(n) {
    return n * (n - 1) / 2;
  };

  function solve(N, pairs) {
    var nations = [];
    var map = [];

    for(var i = 0; i < pairs.length; i++) {
      var pair = pairs[i];

      if(map[pair[0]] && !map[pair[1]]) {
        map[pair[0]].push(pair[1]);
        map[pair[1]] = map[pair[0]];
      } else if(map[pair[1]] && !map[pair[0]]) {
        map[pair[1]].push(pair[0])
        map[pair[0]] = map[pair[1]]
      } else if(!map[pair[0]] && !map[pair[1]]) {
        var nation = [pair[0], pair[1]];
        map[pair[0]] = nation;
        map[pair[1]] = nation;
        nations.push(nation);
      } else if(map[pair[1]] != map[pair[0]]) {
        var deleteNation = map[pair[1]];
        map[pair[1]].forEach(function (p) {
          map[pair[0]].push(p);
          map[p] = map[pair[0]]
        });

        deleteNation.length = 0;
      }
    }


    var result = nC2(N);
    for(var i = 0; i < nations.length; i++) {
      result -= nC2(nations[i].length);
    }

    return result;
  }

  console.log(solve(N, pairs));
}

process.stdin.resume();
process.stdin.setEncoding("ascii");
_input = "";
process.stdin.on("data", function (input) {
  _input += input;
});

process.stdin.on("end", function () {
  processData(_input);
});
