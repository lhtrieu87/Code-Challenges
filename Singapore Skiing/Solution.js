var fs = require('fs')
fs.readFile('input1.txt', 'utf8', function (err, data) {
  if(err) {
    return console.log(err);
  }
  console.log(new Date());
  processData(data);
  console.log(new Date());
});

function solve(map) {
  function findPeaks(map) {
    var peaks = [];
    for(var row = 0; row < map.length; row++) {
      for(var col = 0; col < map[row].length; col++) {
        var cell = map[row][col];
        if((row === 0 || map[row - 1][col] < cell) && (col === 0 || map[row][col - 1] < cell) && (row >= map.length - 1 || map[row + 1][col] < cell) && (col >= map[row].length - 1 || map[row][col + 1] < cell)) {
          peaks.push({
            row: row,
            col: col
          });
        }
      }
    }

    return peaks;
  }

  var peaks = findPeaks(map);
  var mem = [];
  var best = {
    steps: 1,
    highness: 0
  };

  for(var i = 0; i < peaks.length; i++) {
    var peak = peaks[i];
    var result = search(map, peak.row, peak.col);
    best = maxPath(best, result);
  }

  function maxPath(path1, path2) {
    if(path1.steps > path2.steps) return path1;
    else if(path1.steps === path2.steps && path1.highness > path2.highness) return path1;
    return path2;
  }

  function search(map, row, col) {
    var best = {
      steps: 1,
      highness: 0
    };

    if(mem[row] && mem[row][col]) return mem[row][col];

    function computeCurrentBestUsingChildBest(best, subRow, subCol) {
      if(subRow >= 0 && subRow < map.length && subCol >= 0 && subCol < map[row].length && map[subRow][subCol] < map[row][col]) {
        var result = search(map, subRow, subCol);
        best = maxPath(best, {
          steps: 1 + result.steps,
          highness: map[row][col] - map[subRow][subCol] + result.highness
        });
      }
      return best;
    }

    best = computeCurrentBestUsingChildBest(best, row - 1, col);
    best = maxPath(best, computeCurrentBestUsingChildBest(best, row, col - 1));
    best = maxPath(best, computeCurrentBestUsingChildBest(best, row + 1, col));
    best = maxPath(best, computeCurrentBestUsingChildBest(best, row, col + 1));
    
    if(!mem[row]) mem[row] = [];
    mem[row][col] = best;

    return best;
  }

  return best;
}

function processData(input) {
  var lines = input.split('\n');

  var tokens = lines.shift().split(' ');
  var row = parseInt(tokens[0]);
  var col = parseInt(tokens[1]);

  var singapore = [];
  for(var i = 0; i < row; i++) {
    singapore[i] = [];
    tokens = lines.shift().split(' ');
    for(var j = 0; j < col; j++) {
      singapore[i][j] = parseInt(tokens[j]);
    }
  }

  var result = solve(singapore);
  console.log(result.steps + '' + result.highness);
}
