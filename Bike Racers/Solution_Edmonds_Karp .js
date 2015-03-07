/*
 * Queue.js
 * Creates a new queue. A queue is a first-in-first-out (FIFO) data structure -
 * items are added to the end of the queue and removed from the front.
 */
function Queue() {

  // initialise the queue and offset
  var queue = [];
  var offset = 0;
  var size = 0;

  // Returns true if the queue is empty, and false otherwise.
  this.isEmpty = function () {
    return(size == 0);
  }

  /* Enqueues the specified item. The parameter is:
   *
   * item - the item to enqueue
   */
  this.enqueue = function (item) {
    queue.push(item);
    ++size;
  }

  /* Dequeues an item and returns it. If the queue is empty, the value
   * 'undefined' is returned.
   */
  this.dequeue = function () {

    // if the queue is empty, return immediately
    if(this.isEmpty()) return undefined;

    // store the item at the front of the queue
    var item = queue[offset];

    // increment the offset and remove the free space if necessary
    if(++offset == queue.length) {
      offset = 0;
    }

    --size;

    // return the dequeued item
    return item;

  }

  /* Returns the item at the front of the queue (without dequeuing it). If the
   * queue is empty then undefined is returned.
   */
  this.peek = function () {
    return(this.isEmpty() ? undefined : queue[offset]);
  }

}


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

  this.maxFlow = function maxFlow(source, sink) {
    while(true) {
      // The parent table is used to backtrack and rebuild the found augmenting path.
      // parent[vertex A] = the parent node of A
      var parent = {};
      // minFlow[A] = the flow possibly passes through the path to the node A from the source.
      var minFlow = {};

      var queue = new Queue();
      queue.enqueue(source);

      LOOP:
        while(!queue.isEmpty()) {
          var current = queue.dequeue();
          var edges = getEdges(current);
          for(var i = 0; i < edges.length; i++) {
            var edge = edges[i];
            var next = edge.destination;
            var residual = edge.capacity - flow[edge];
            if(residual > 0 && !parent[next] && next != source) {
              parent[next] = edge;
              minFlow[next] = Math.min(minFlow[current] || Number.MAX_VALUE - 1, residual);

              if(next === sink) {
                while(parent[next]) {
                  var edge = parent[next];
                  flow[edge] += minFlow[next];
                  flow[edge.redge] -= minFlow[next];
                  next = parent[next].source;
                }

                break LOOP;
              } else {
                queue.enqueue(next);
              }
            }
          }
        }

      if(!parent[sink]) {
        return getEdges(source).reduce(function (acc, edge) {
          return acc + flow[edge];
        }, 0);
      }
    }
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

var profiler = require('v8-profiler');
var fs = require('fs')
var fileName = 'data11.txt';
fs.readFile(fileName, 'utf8', function (err, data) {
  if(err) {
    return console.log(err);
  }

  profiler.startProfiling('Profile 1');
  
  console.log(new Date());
  processData(data);
  console.log(new Date());
  
  profiler.stopProfiling('Profile 1');
  profiler.takeSnapshot('Post Snapshot');
});
