function [ vals ] = HermiteMaiMultePuncte( x, f, fd, points )
    vals = zeros(size(points));
    for i = 1 : length(points)
       vals(i) = Hermite(x, f, fd, points(i)); 
    end
end