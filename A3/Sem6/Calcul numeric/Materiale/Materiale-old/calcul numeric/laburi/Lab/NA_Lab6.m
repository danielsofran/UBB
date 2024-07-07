%{
  1. Consider the function: f(x) = sin(x) defined on [0,2*pi] and the nodes
  0, pi/2, pi, 3*pi/2, 2*pi.
  
  a) Display the value of the function, the value of the cubic natural spline 
  and the value of the cubic clamped spline function at x = pi/4.
  b) Plot the graphs of the function, the cubic natural spline and the cubic 
  clamped spline functions, in the same figure.
  (Use Matlab function spline)
%}

% Solution:
x = 0:0.1:2*pi;
f = sin(x);

nodes = [0, pi/2, pi, 3*pi/2, 2*pi];
f_nodes = sin(nodes);
x0 = pi/4;
f1 = sin(x0);

% a)
fprintf('The value of f(x0) is: %.4f \n', f1);

val_naturalSpline = spline(nodes, f_nodes, x0);

fprintf('The value of the cubic natural spline function at x0 is: %.4f \n',val_naturalSpline);

val_clampedSpline = spline(nodes, [0 f_nodes 0], x0);

fprintf('The value of the cubic clamped spline function at x0 is: %.4f \n',val_clampedSpline);

% b)
nodes = [0, pi/2, pi, 3*pi/2, 2*pi];
f_nodes = sin(nodes);
xx = 0:0.1:2*pi;
yy = spline(nodes,f_nodes,xx);
plot(x,f)
hold on 

plot(nodes,f_nodes,'o',xx,yy)
hold on

cs = spline(nodes,[0 f_nodes 0]);
xx = linspace(-4,4,101);
plot(nodes,f_nodes,'o',xx,ppval(cs,xx),'-', axis("auto"));

clear()


%{
  2. There are given 5 arbitrary points, using Matlab function ginput. Plot the
  points and the graph of cubic natural spline function that passes through all
  the given points.
%}

% Solution:
axis("square")
[x, y, buttons] = ginput(5);
plot(x,y)
hold on

xx = -1:0.1:1;
yy = spline(x,y,xx);
axis("manual")
plot(x,y,'o',xx,yy)
 

clear()


%{
  3. In the following table there are some data regarding a moving car:
  
     Time (in s)   :  0    3     5     8     13
 Distance (in feet):  0   225   383   623   993     
  Speed (in feet/s): 75    77    80    74    72
  
  Use a clamped cubic spline to predict the position of the car and its speed 
  when the time is t=10s.
  
%}

% Solution:
nodes = [0, 3, 5, 8, 13];
distance = [0, 225, 383, 623, 993];
speed = [75, 77, 80, 74, 72];
t = 10;

prediction_distance = spline(nodes, [0 distance 0], t);

prediction_speed = spline(nodes, [0 speed 0], t);

clear()



































