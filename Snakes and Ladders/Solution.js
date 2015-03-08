var Heap = (function () {
  function swap(array, size, i, j) {
    if(i >= 0 && j >= 0 && i < size && j < size && i != j) {
      var temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }

  function leftChild(i, size) {
    if(i < 0) return -1;
    var l = i * 2 + 1;
    return l >= size ? -1 : l;
  }

  function rightChild(i, size) {
    if(i < 0) return -1;
    var r = i * 2 + 2;
    return r >= size ? -1 : r;
  }

  function parent(i, size) {
    if(i >= size || i <= 0) return -1;
    return Math.floor((i - 1) / 2);
  }

  function bubbleDown(compareFunc, array, size, i) {
    if(i >= 0 && i < size) {
      var j = i;

      var l = leftChild(i, size);
      if(l >= 0 && compareFunc(array[l], array[j]) > 0) {
        j = l;
      }

      var r = rightChild(i, size);
      if(r >= 0 && compareFunc(array[r], array[j]) > 0) {
        j = r;
      }

      if(j != i) {
        swap(array, size, i, j);
        bubbleDown(compareFunc, array, size, j);
      }
    }
  }

  function bubbleUp(compareFunc, array, size, i) {
    var p = parent(i, size);
    if(p >= 0 && compareFunc(array[p], array[i]) < 0) {
      swap(array, size, p, i);
      bubbleUp(compareFunc, array, size, p);
    }
  }

  function Heap(compareFunc) {
    var array = [];
    var size = 0;

    var bubbleUpLocal = bubbleUp.bind(null, compareFunc);
    var bubbleDownLocal = bubbleDown.bind(null, compareFunc);

    this.clear = function () {
      size = 0;
      array = [];
    }

    this.isEmpty = function () {
      return size === 0;
    }

    this.getSize = function () {
      return size;
    }

    this.insert = function (value) {
      array[size] = value;
      size++;
      bubbleUpLocal(array, size, size - 1);
    }

    this.pop = function () {
      if(this.isEmpty()) return null;

      var max = array[0];

      swap(array, size, 0, size - 1);
      size--;
      bubbleDownLocal(array, size, 0);

      return max;
    }

    this.peek = function () {
      if(this.isEmpty()) return null;

      return array[0];
    }

    this.build = function (seedArray) {
      array = seedArray;
      size = seedArray.length;

      for(var k = parent(size - 1); k >= 0; k--) {
        bubbleDownLocal(array, size, k);
      }
    }

    this.toString = function () {
      var strs = [];
      array.forEach(function (n) {
        strs.push(n);
      });

      return strs.join(', ');
    }
  }

  Heap.sort = function (initialArray, compareFunc) {
    var bubbleDownLocal = bubbleDown.bind(null, compareFunc);

    var heap = new Heap(compareFunc);
    heap.build(initialArray);
    (function heapSort(heap, count) {
      if(count > 0) {
        swap(initialArray, count, 0, count - 1);
        bubbleDownLocal(initialArray, count - 1, 0);

        heapSort(heap, count - 1);
      }
    }(heap, initialArray.length));

    initialArray.reverse();

    return initialArray;
  };

  return Heap;
})();

function processData(input) {
  var lines = input.split('\n');
  var T = parseInt(lines.shift());

  for(var i = 0; i < T; i++) {
    var split = lines.shift().split(',');
    var noLadders = parseInt(split[0]);
    var noSnakes = parseInt(split[1]);

    function parseCoords(string) {
      return string.split(' ').map(function (l) {
        var split = l.split(',');
        return {
          start: parseInt(split[0]),
          end: parseInt(split[1])
        }
      });
    }

    var ladders = parseCoords(lines.shift());
    var snakes = parseCoords(lines.shift());

    function search(ladders, snakes) {
      function find(pos, objs) {
        for(var i = 0; i < objs.length; i++) {
          if(objs[i].start === pos)
            return objs[i];
        }
      }

      function curry(objs) {
        return function (pos) {
          return find(pos, objs);
        }
      }

      var findLadder = curry(ladders);
      var findSnake = curry(snakes);

      var goal = 100;

      var queue = new Heap(function (a, b) {
        if(a.noTurns > b.noTurns) return -1;
        else if(a.noTurns < b.noTurns) return 1;
        else if(a.pos > b.pos) return 1;
        else if(a.pos < b.pos) return -1;
        else return 0;
      });

      queue.insert({
        noTurns: 0,
        pos: 1
      });

      var open = [];
      for(var i = 1; i <= 100; i++) {
        open[i] = 1000;
      }
      open[1] = 0;

      while(!queue.isEmpty()) {
        var current = queue.pop();

        if(current.pos === goal) {
          return current.noTurns;
        }

        for(var i = 1; i <= 6; i++) {
          var nextPos = current.pos + i;
          var nextTurn = current.noTurns + 1;

          var ladder = findLadder(nextPos);
          var snake = findSnake(nextPos);

          if(ladder && nextTurn < open[ladder.end]) {
            queue.insert({
              noTurns: nextTurn,
              pos: ladder.end
            });

            open[ladder.end] = nextTurn;
          } else if(snake && nextTurn < open[snake.end]) {
            queue.insert({
              noTurns: nextTurn,
              pos: snake.end
            });

            open[snake.end] = nextTurn;
          } else if(nextPos <= goal && nextTurn < open[nextPos]) {
            queue.insert({
              noTurns: nextTurn,
              pos: nextPos
            });

            open[nextPos] = nextTurn;
          }
        }
      }
    }

    console.log(search(ladders, snakes));
  }
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
