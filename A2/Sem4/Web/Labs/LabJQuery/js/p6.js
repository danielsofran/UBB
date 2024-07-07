const N = 3;
let unused = [];
for(let i = 0; i < N * N; i++) { unused.push(i);}
const dx = [0, 0, 1, -1];
const dy = [1, -1, 0, 0];
const down = 0, up = 1, right = 2, left = 3;

let empty = $('#'+(N*N-1).toString());

class Point{
  constructor(x, y){
    this.x = x;
    this.y = y;
  }
}

function getNeighbour(currentIndex, direction){
  let point = getPosition(currentIndex);
  let target = new Point(point.x + dx[direction], point.y + dy[direction]);
  let neighbours = getNeighbours(currentIndex);
  for(let i = 0; i < neighbours.length; i++){
    if(neighbours[i].x === target.x && neighbours[i].y === target.y)
    {
      let index = target.x * N + target.y;
      return $('#'+index.toString());
    }
  }
  return null;
}

function getNeighbours(index){
  let point = getPosition(index);
  let neighbours = [];
  if(point.x > 0) neighbours.push(new Point(point.x - 1, point.y));
  if(point.x < N - 1) neighbours.push(new Point(point.x + 1, point.y));
  if(point.y > 0) neighbours.push(new Point(point.x, point.y - 1));
  if(point.y < N - 1) neighbours.push(new Point(point.x, point.y + 1));
  return neighbours;
}

function getPosition(index){
  let i = parseInt(index);
  let y = i % N;
  let x = Math.floor(i / N);
  return new Point(x, y);
}

function getRandomValue(){
  let index = Math.floor(Math.random() * unused.length);
  let value = unused[index];
  unused.splice(index, 1);
  return value;
}



function Container(n){
  let container = $("<div></div>");
  container.addClass('container');
  container.css('grid-template-columns', `repeat(${n}, 1fr)`);
  return container;
}

function Box(index){
  let box = $("<div></div>");
  box.addClass('box');
  box.attr('id', index);
  return box;
}

const container = Container(N);
for(let i = 0; i < N * N; i++){
  let box = Box(i);
  let value = getRandomValue();
  if(value === 0) {
    box.text(' ');
    empty = box;
  }
  else box.text(value);
  container.append(box);
}

$('body').on('keydown', function(e){
  let key = e.keyCode;
  let emptyIndex = parseInt(empty.attr('id'));
  let neighbour = null;
  switch(key){
    case 37:
      neighbour = getNeighbour(emptyIndex, up);
      break;
    case 38:
      neighbour = getNeighbour(emptyIndex, left);
      break;
    case 39:
      neighbour = getNeighbour(emptyIndex, down);
      break;
    case 40:
      neighbour = getNeighbour(emptyIndex, right);
      break;
  }
  if(neighbour !== null){
    console.log("neighbour: " + neighbour.text());
    empty.text(neighbour.text());
    neighbour.text(' ');
    empty = neighbour;
  }
});

const root = $('#root');
root.append(container);
