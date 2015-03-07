function Position(row, col) {
  this.row = row;
  this.col = col;

  this.toString = function () {
    return '(' + this.row + ' : ' + this.col + ')';
  }
}

function Edge(source, destination, capacity) {
  this.source = source;
  this.destination = destination;
  this.capacity = capacity;
  this.sqrtDistance = (source.row - destination.row) * (source.row - destination.row) + (source.col - destination.col) * (source.col - destination.col)

  this.toString = function () {
    return this.source + ' --> ' + this.destination;
  }
}

function NetworkFlow() {
  var flow = {};
  var adj = {};

  function getEdges(v) {
    return adj[v];
  };

  this.addEdge = function (edge) {
    var redge = new Edge(edge.destination, edge.source, 0);
    edge.redge = redge;
    redge.redge = edge;

    if(!adj[edge.source]) adj[edge.source] = [];
    adj[edge.source].push(edge);

    if(!adj[redge.source]) adj[redge.source] = [];
    adj[redge.source].push(redge);

    flow[edge] = 0;
    flow[redge] = 0;
  }

  this.addEdges = function (edges) {
    for(var i = 0; i < edges.length; i++) {
      this.addEdge(edges[i]);
    }
  }

  function findPath(source, destination, path, visited) {
    if(source === destination) {
      return path;
    }
    var edges = getEdges(source);
    for(var i = 0; i < edges.length; i++) {
      var edge = edges[i];
      var residual = edge.capacity - flow[edge];
      if(residual > 0 && visited.indexOf(edge.destination) == -1 && path.indexOf(edge) == -1 && path.indexOf(edge.redge) == -1) {
        var newPath = path.slice();
        newPath.push(edge);
        var newVisited = visited.slice();
        newVisited.push(edge.destination);
        var result = findPath(edge.destination, destination, newPath, newVisited);
        if(result) return result;
      }
    }

    return undefined;
  }

  this.maxFlow = function maxFlow(source, sink) {
    var path = findPath(source, sink, [], [source]);
    while(path && path.length > 0) {
      var residuals = path.map(function (edge) {
        return edge.capacity - flow[edge];
      });

      var minFlow = residuals.reduce(function (acc, r) {
        return Math.min(acc, r);
      });

      path.forEach(function (edge) {
        flow[edge] += minFlow;
        flow[edge.redge] -= minFlow;
      });

      path = findPath(source, sink, [], [source]);
    }

    return getEdges(source).reduce(function (acc, edge) {
      return acc + flow[edge];
    }, 0);
  }
}

function solve(racers, bikes, K) {
  var edges = [];
  for(var i = 0; i < racers.length; i++) {
    var racer = racers[i];
    for(var j = 0; j < bikes.length; j++) {
      var bike = bikes[j];
      edges.push(new Edge(racer, bike, 1));
    }
  }

  edges.sort(function (e1, e2) {
    return e1.sqrtDistance - e2.sqrtDistance;
  });

  var source = new Position(-1, 0);
  var sink = new Position(0, -1);

  var minTime;
  var left = 0,
    right = edges.length - 1;
  while(left < right) {
    var mid = Math.floor(left + (right - left) / 2);
    var g = new NetworkFlow();

    for(var i = 0; i < racers.length; i++) {
      g.addEdge(new Edge(source, racers[i], 1));
    }

    for(var i = 0; i < bikes.length; i++) {
      g.addEdge(new Edge(bikes[i], sink, 1));
    }

    g.addEdges(edges.slice(0, mid + 1));
    var result = g.maxFlow(source, sink);
    if(result < K) left = mid + 1;
    else if(result >= K) {
      minTime = edges[mid].sqrtDistance;
      right = mid;
    }
  }

  return minTime;
}

function processData(input) {
  var lines = input.split('\n');
  var tokens = lines.shift().split(' ');
  var numRacers = parseInt(tokens[0]);
  var numBikes = parseInt(tokens[1]);
  var K = parseInt(tokens[2]);

  var racers = [];
  for(var i = 0; i < numRacers; i++) {
    tokens = lines.shift().split(' ');
    racers[i] = new Position(parseInt(tokens[0]), parseInt(tokens[1]));
  }

  var bikes = [];
  for(var i = 0; i < numBikes; i++) {
    tokens = lines.shift().split(' ');
    bikes[i] = new Position(parseInt(tokens[0]), parseInt(tokens[1]));
  }
  console.log(solve(racers, bikes, K));
}

process.stdin.resume();
process.stdin.setEncoding("ascii");
_input = "";
process.stdin.on("data", function (input) {
  _input += input;
});


process.on('SIGINT', function () {
  processData(_input);
  process.exit();
});
