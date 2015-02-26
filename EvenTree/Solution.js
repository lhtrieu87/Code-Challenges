function processData(input) {
    var lines = input.split('\n');
    var tokens = lines.shift().split(' ');
    var numVertices = parseInt(tokens[0]);
    var numEdges = parseInt(tokens[1]);

    var adj = [];
    for (var i = 0; i < numEdges; i++) {
        tokens = lines.shift().split(' ');
        var a = parseInt(tokens[0]);
        var b = parseInt(tokens[1]);

        adj[a] = adj[a] || [];
        adj[b] = adj[b] || [];

        adj[a].push(b);
        adj[b].push(a);
    }

    var visited = [];
    var counts = [];
    var result = 0;
    function dfs(node, counts) {
        visited[node] = true;
        var count = 1;
        for (var i = 0; i < adj[node].length; i++) {
            var next = adj[node][i];
            if (!visited[next]) {
                dfs(next, counts);
                count += counts[next];
            }
        }
        if(count % 2 === 0)
          result += 1;
        counts[node] = count;
    };

    dfs(1, counts);

    console.log(result - 1);
}

// var fs = require('fs')
// fs.readFile('input0.txt', 'utf8', function(err, data) {
//     if (err) {
//         return console.log(err);
//     }
//     console.log(new Date());
//     processData(data);
//     console.log(new Date());
// });

process.stdin.resume();
process.stdin.setEncoding("ascii");
_input = "";
process.stdin.on("data", function(input) {
    _input += input;
});

process.stdin.on("end", function() {
    processData(_input);
});
